package com.example.chefgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerOrderMealActivity extends AppCompatActivity {

    EditText inputDish;
    Button confirmDish;
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_meal);

        inputDish = findViewById(R.id.inputDish);
        confirmDish = findViewById(R.id.confirmDish);

        confirmDish.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String meal = inputDish.getText().toString();
                Toast.makeText(CustomerOrderMealActivity.this, "Order confirmed.", Toast.LENGTH_LONG).show();
                postJSONObjectRequest(meal);
            }
        });
    }

    private void postJSONObjectRequest(String meal){

        Map<String, String> map = new HashMap<>();
        map.put("oid", "12");
        map.put("price", "5");
        map.put("dish", meal);
        map.put("chef", "TBD");
        map.put("customer", "Carter");
        map.put("date", "2019-09-28T23:16:12.32Z");
        map.put("review", null);

        JSONObject orderObject = new JSONObject(map);
        //JSONObject reviewObject = new JSONObject();

        /*
        try {

            reviewObject.put("rid", 4);
            reviewObject.put("rating", 5.0);
            reviewObject.put("reviewer", "Joe");
            reviewObject.put("reviewee", "Karthik");
            reviewObject.put("date", "06-16-1999");
            reviewObject.put("description", "Allan is beautiful");
            JSONArray reviewArray = new JSONArray();
            reviewArray.put(reviewObject);

            orderObject.put("oid", "4");
            orderObject.put("price", "5");
            //orderObject.put("review", reviewArray);
            orderObject.put("dish", meal);
            orderObject.put("chef", "Carter");
            orderObject.put("customer", "Allan");
            orderObject.put("date", "06-16-1999");
        } catch (JSONException e){

        }
        */

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, URL, orderObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CustomerOrderMealActivity.this, "Order placed successfully", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
