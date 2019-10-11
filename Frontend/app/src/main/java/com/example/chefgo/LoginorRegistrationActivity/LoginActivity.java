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
    private Auth0 auth0;
    public static final String EXTRA_CLEAR_CREDENTIALS = "com.auth0.CLEAR_CREDENTIALS";
    public static final String EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();
        auth0 = new Auth0(this);
        auth0.setOIDCConformant(true);

        //region login
        login = findViewById(R.id.login);
        login.setEnabled(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            login();
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


        //Check if the activity was launched to log the user out
        if (getIntent().getBooleanExtra(EXTRA_CLEAR_CREDENTIALS, false)) {
            logout();
        }










//        username = findViewById(R.id.username);
//        password = findViewById(R.id.password);
//        String request = URL + "/";
//        request += username.getText().toString();
//        makeJSONObjectReq(request);
    }
    private void login() {
        WebAuthProvider.login(auth0)
                .withScheme("demo")
                .withAudience(String.format("https://%s/userinfo", getString(R.string.com_auth0_domain)))
                .start(this, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull final Dialog dialog) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(@NonNull final Credentials credentials) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(EXTRA_ACCESS_TOKEN, credentials.getAccessToken());
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
    }

    private void logout() {
        WebAuthProvider.logout(auth0)
                .withScheme("demo")
                .start(this, new VoidCallback() {
                    @Override
                    public void onSuccess(Void payload) {

                    }

                    @Override
                    public void onFailure(Auth0Exception error) {
                        //Log out canceled, keep the user logged in
                        showNextActivity();
                    }
                });
    }

    private void showNextActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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
                    Intent inten = new Intent(LoginActivity.this, MainActivity.class);
                    inten.putExtra("User", user);
                    startActivity(inten);
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
