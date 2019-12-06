package com.example.chefgo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;
/**
 * @author SB_3
 *
 */

public class CustomerOrderMealActivity extends AppCompatActivity {

    private EditText inputDish, inputPrice;
    private Button confirmButton;
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";
    private UsersDomain user = new UsersDomain();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_meal);

        inputDish = findViewById(R.id.inputAllergy);
        inputPrice = findViewById(R.id.inputPrice);
        confirmButton = findViewById(R.id.confirmAllergy);
        user = getIntent().getParcelableExtra("User");
        confirmButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String meal = inputDish.getText().toString();
                String price = inputPrice.getText().toString();
                Toast.makeText(CustomerOrderMealActivity.this, "Order confirmed.", Toast.LENGTH_LONG).show();
                postJSONObjectRequest(meal, price);
                inputDish.getText().clear();
                inputPrice.getText().clear();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void postJSONObjectRequest(String meal, String price){

        String date = Instant.now().toString();

        Map<String, String> customerMap = new HashMap<>();
        customerMap.put("username", user.getUsername());
        customerMap.put("email", user.getEmail());
        customerMap.put("name", user.getName());
        customerMap.put("password", user.getPassword());
        customerMap.put("userType", user.getUserType().toString());
        customerMap.put("rating", user.getRating().toString());
        customerMap.put("address", user.getAddress());
        customerMap.put("state", user.getState());
        customerMap.put("zip", user.getZip().toString());
        JSONObject customerObject = new JSONObject(customerMap);

        Map<String, String> orderMap = new HashMap<>();
        orderMap.put("isActive", "1");
        orderMap.put("oid", Integer.toString(Integer.MAX_VALUE));
        orderMap.put("price", price);
        orderMap.put("dish", meal);
        orderMap.put("chef", null);
        orderMap.put("customer", null);
        orderMap.put("date", date);
        orderMap.put("review", null);
        JSONObject orderObject = new JSONObject(orderMap);
        try {
            orderObject.put("customer", customerObject);
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

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
