package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.CustomerOrderHistoryActivity;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class ChefActiveMealsActivity extends AppCompatActivity {

    private TextView title;
    private Button refreshButton;
    private UsersDomain user;
    private ListView listView;

    private String jsonResponse, URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/active";
    String allergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_active_meals);
        user = getIntent().getParcelableExtra("User");

        title = findViewById(R.id.title);
        listView = findViewById(R.id.listView);

        getActiveMeals();
        refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActiveMeals();
            }
        });
    }

    private void getActiveMeals(){

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            final ArrayList<String> arrayList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject order = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String oid = order.getString("oid");
                                String price = order.getString("price");
                                String dish = order.getString("dish");
                                //String chef = order.getString("chef");
                                JSONObject customer = order.getJSONObject("customer");
                                String customerName = customer.getString("name");
                                //String date = order.getString("date");
                                getUserAllergies(customer.getString("username"));

                                jsonResponse += (oid + "\n");
                                jsonResponse += ("Dish: " + dish + "\n");
                                jsonResponse += ("Price: " + price + "\n");
                                jsonResponse += ("Customer name: " + customerName + "\n");

                                if (allergy != null)
                                    jsonResponse += ("Allergies: " + allergy);
                                else
                                    jsonResponse += ("Allergies: none");
                                arrayList.add(jsonResponse);
                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter(ChefActiveMealsActivity.this, android.R.layout.simple_list_item_1, arrayList);
                            listView.setAdapter(arrayAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //launch new intent to accept or decline meal request
                                    Intent i = new Intent(ChefActiveMealsActivity.this, ChefHandleMealActivity.class);
                                    i.putExtra("User", user);
                                    i.putExtra("JSON_RESPONSE", arrayList.get(position));
                                    try {
                                        i.putExtra("JSON_OBJECT", response.get(position).toString());
                                    } catch(JSONException e){
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(),
                                                "Error: " + e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                    startActivity(i);
                                }
                            });
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    private void getUserAllergies(String uid){

        String allergyURL = "http://coms-309-sb-3.misc.iastate.edu:8080/allergies/" + uid;

        JsonArrayRequest req = new JsonArrayRequest(allergyURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject allergies = (JSONObject) response.get(i);
                                /*
                                jsonResponse = "";
                                jsonResponse += allergies.getString("allergy");
                                arrayList.add(jsonResponse);
                                */
                                allergy = allergies.getString("allergy");
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

}
