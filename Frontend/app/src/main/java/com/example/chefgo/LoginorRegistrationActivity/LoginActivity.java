package com.example.chefgo.LoginorRegistrationActivity;

import android.accounts.AccountManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.auth0.android.Auth0;
import com.auth0.android.Auth0Exception;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.VoidCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.example.chefgo.AdminClient.AdminActivity;
import com.example.chefgo.ChefClient.ChefMainActivity;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.MainActivity;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;



import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/user";
   //private String URL = "http://10.0.2.2:8080/user";
    private String jsonObjectTag = "jobj_req", tag_json_arry = "jarray_req";
    String tag_string_req ="string_req";
    private UsersDomain user = new UsersDomain();
    EditText username;
    EditText password;
    private Button register;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();


        //region login
        login = findViewById(R.id.login);
        login.setEnabled(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);
                String request = URL + "/";
                request += username.getText().toString();
                makeJSONObjectReq(request);
            }
        });
        //endregion

        //region register
        register = findViewById(R.id.registration);
        register.setEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent register = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(register);
        }
        });
        //endregion



    }




    private void makeJSONObjectReq(final String request) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                request, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {

                    user.setUsername(response.getString("username"));
                    user.setEmail(response.getString("email"));
                    user.setName(response.getString("name"));
                    user.setAddress(response.getString("address"));
                    user.setState(response.getString("state"));
                    user.setZip(response.getInt("zip"));
                    user.setPassword(response.getString("password"));
                    user.setRating(response.getDouble("rating"));
                    user.setUserType(response.getInt("userType"));


                    if(user.getPassword().equals(password.getText().toString())) {
                        //if user is an admin
                        if(user.getUserType() == 0 ){
                            Intent admin = new Intent(LoginActivity.this, AdminActivity.class);
                            admin.putExtra("User", user);
                            startActivity(admin);
                        }
                        //if user is a customer
                        else if(user.getUserType() == 1) {
                            Intent customer = new Intent(LoginActivity.this, MainActivity.class);
                            customer.putExtra("User", user);
                            startActivity(customer);
                        }
                        // if user is a chef
                        else if(user.getUserType() == 2){
                            Intent chef = new Intent(LoginActivity.this, ChefMainActivity.class);
                            chef.putExtra("User", user);
                            startActivity(chef);
                        }
                    }

                    else{
                    Toast.makeText(getApplicationContext(),
                            "Wrong Password: ",
                            Toast.LENGTH_LONG).show();
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Wrong password", Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


}