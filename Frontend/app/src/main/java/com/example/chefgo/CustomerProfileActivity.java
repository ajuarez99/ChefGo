package com.example.chefgo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.app.AppController;

import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerProfileActivity extends AppCompatActivity {


    TextView responseView = findViewById(R.id.responseView);
    private String URL = "coms-309-sb-3.misc.iastate.edu:8080/users";
    private String jsonObjectTag = "jobj_req", tag_json_arry = "jarray_req";


    public void makeJsonObjReq() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        responseView.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, jsonObjectTag);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        //makeJsonObjReq();
    }

}
