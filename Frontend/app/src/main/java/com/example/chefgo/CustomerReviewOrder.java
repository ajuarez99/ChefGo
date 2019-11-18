package com.example.chefgo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerReviewOrder extends AppCompatActivity {

    private UsersDomain user;
    private String selectedOrder;
    private TextView reviewOrderTitle, orderDescriptionTitle, orderDescription;
    private EditText reviewBox;
    private Button submitReview;

    private JSONObject order;
    private int oid;
    private String  add_review_url = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/review/";
    private static final String ORDER_HISTORY_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review_order);

        selectedOrder = getIntent().getStringExtra("order");
        user = getIntent().getParcelableExtra("User");
        oid = getIntent().getIntExtra("oid", oid);
        add_review_url += oid;
        setOrder(new VolleyCallBack() {
            @Override
            public void onSuccess() {

                if (order == null){
                    Toast.makeText(getApplicationContext(), "Order not found", Toast.LENGTH_LONG).show();
                    return;
                }
                if (order.has("review") && !order.isNull("review")){
                    try{
                        reviewBox.setText(order.getJSONObject("review").getString("description"));
                        reviewBox.setEnabled(false);
                        reviewBox.setClickable(false);
                        submitReview.setVisibility(View.INVISIBLE);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        reviewOrderTitle = findViewById(R.id.reviewOrderTitle);
        orderDescriptionTitle = findViewById(R.id.orderDescriptionTitle);
        orderDescription = findViewById(R.id.orderDescription);
        reviewBox = findViewById(R.id.reviewBox);
        submitReview = findViewById(R.id.submitReview);

        orderDescriptionTitle.setPaintFlags(orderDescriptionTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        orderDescription.setText(selectedOrder);

        submitReview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                reviewOrder();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void reviewOrder(){

        JSONObject reviewer = null;
        JSONObject reviewee = null;
        String date = Instant.now().toString();
        String description = reviewBox.getText().toString();

        if(order == null){
            Toast.makeText(getApplicationContext(), "Order is null", Toast.LENGTH_LONG).show();
            return;
        }
        else if (order.has("chef") && order.isNull("chef")){
            Toast.makeText(getApplicationContext(), "Chef is null", Toast.LENGTH_LONG).show();
            return;
        }
        else if (order.has("customer") && order.isNull("customer")){
            Toast.makeText(getApplicationContext(), "Customer is null", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            reviewer = order.getJSONObject("customer");
            reviewee = order.getJSONObject("chef");
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        Map<String, String> reviewMap = new HashMap<>();
        reviewMap.put("rid", "0");
        reviewMap.put("rating", "0");
        reviewMap.put("description", description);
        reviewMap.put("date", date);
        JSONObject review = new JSONObject(reviewMap);
        try {
            review.put("reviewer", reviewer);
            review.put("reviewee", reviewee);
            Toast.makeText(getApplicationContext(), review.getJSONObject("reviewer").getString("email"), Toast.LENGTH_LONG).show();
        } catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, add_review_url, review,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CustomerReviewOrder.this, "Review submitted successfully", Toast.LENGTH_LONG).show();
                        reviewBox.setText("");
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

    private void setOrder(final VolleyCallBack callback){

        JsonArrayRequest req = new JsonArrayRequest(ORDER_HISTORY_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            order = null;
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject orderObject = (JSONObject) response.get(i);
                                if (orderObject.getInt( "oid") == oid){
                                    order = orderObject;
                                    callback.onSuccess();
                                }
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
                        error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
}
