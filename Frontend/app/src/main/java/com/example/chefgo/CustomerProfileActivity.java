package com.example.chefgo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.chefgo.app.AppController;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerProfileActivity extends AppCompatActivity {

    Button testButton = findViewById(R.id.testButton);
    TextView responseView = findViewById(R.id.responseView);
    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users";
    private String jsonObjectTag = "jobj_req", tag_json_arry = "jarray_req";
    String tag_string_req ="string_req";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeStringReq();
            }
        });
    }


    private void makeStringReq() {

        StringRequest strReq = new StringRequest(Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                responseView.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


}
