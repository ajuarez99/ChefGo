package com.example.chefgo;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.StringRequest;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;


import static com.example.chefgo.app.AppController.TAG;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private String URL = "http://10.0.2.2:8082/user";
    private String jsonObjectTag = "jobj_req", tag_json_arry = "jarray_req";
    String tag_string_req ="string_req";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        login.setEnabled(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);
                String request = URL + "/";
                request += username.getText().toString() +"/" + password.getText().toString();
                makeStringReq(request);
            }
        });

    }



    private void makeStringReq(final String request) {

        StringRequest strReq = new StringRequest(Request.Method.GET, request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                if(response.equals("Success")){
                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "WRONG", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


}
