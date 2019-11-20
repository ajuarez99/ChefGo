package com.example.chefgo;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author SB_3
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerOrderHistoryActivity extends AppCompatActivity {

    TextView description;
    ListView listView;
    UsersDomain user;
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory", jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_history);
        user = new UsersDomain();
        user = getIntent().getParcelableExtra("User");
        listView = findViewById(R.id.listview);
        description = findViewById(R.id.orderHistoryDescription);

        getJSONArrayRequest();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedOrder = parent.getItemAtPosition(position).toString();
                Intent customerReviewOrderIntent = new Intent(CustomerOrderHistoryActivity.this, CustomerReviewOrder.class);
                customerReviewOrderIntent.putExtra("order", selectedOrder);
                customerReviewOrderIntent.putExtra("User", user);
                customerReviewOrderIntent.putExtra("oid", getOrderID(selectedOrder));
                startActivity(customerReviewOrderIntent);
            }
        });
    }

    private void getJSONArrayRequest(){

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();

                            for (int i = response.length()-1; i >= 0; i--) {

                                JSONObject order = (JSONObject) response.get(i);
                                jsonResponse = "";

                                int oid = order.getInt("oid");
                                String price = order.getString("price");
                                String dish = order.getString("dish");
                                String date = order.getString("date");
                                String customerUsername = order.getJSONObject("customer").getString("username");
                                String active = order.getInt("isActive") == 1 ? "Yes" : "No";

                                String chefName;
                                if (order.has("chef") && order.isNull("chef")){
                                    chefName = "TBD";
                                }
                                else {
                                    chefName = order.getJSONObject("chef").getString("name");
                                }

                                if (customerUsername.equals(user.getUsername())) {
                                    jsonResponse += ("Order id: " + oid + "\n");
                                    jsonResponse += ("Dish: " + dish + "\n");
                                    jsonResponse += ("Chef: " + chefName + "\n");
                                    jsonResponse += ("Price: " + price + "\n");
                                    jsonResponse += ("Date: " + date + "\n");
                                    jsonResponse += ("Active: " + active + "\n");
                                    arrayList.add(jsonResponse);
                                }

                            }
                            if (arrayList.isEmpty()){
                                description.setText("You have no order history.");
                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerOrderHistoryActivity.this, android.R.layout.simple_list_item_1, arrayList);
                            listView.setAdapter(arrayAdapter);

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

    private int getOrderID(String selectedOrder){
        String[] orderDescription = selectedOrder.split("\n");
        return Integer.parseInt(orderDescription[0].replaceAll("[\\D]", ""));
    }
}
