package com.example.chefgo;

import android.accounts.AccountManager;
import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.chefgo.ChefClient.ChefMainActivity;
import com.example.chefgo.DomainObjects.UsersDomain;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();


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
                    user.setfName(response.getString("fName"));
                    user.setLname(response.getString("lName"));
                    user.setAddress(response.getString("address"));
                    user.setState(response.getString("state"));
                    user.setZip(response.getInt("zip"));
                    user.setPassword(response.getString("password"));
                    user.setRating(response.getDouble("rating"));
                    user.setUserType(response.getInt("userType"));

                if(user.getPassword().equals(password.getText().toString())) {
                    //if user is a customer
                    if(user.getUserType() == 1) {
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
