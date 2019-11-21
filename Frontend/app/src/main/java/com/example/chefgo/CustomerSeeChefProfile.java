package com.example.chefgo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerSeeChefProfile extends AppCompatActivity {

    //Page components
    TextView nameView;
    RatingBar ratingBar;
    ListView menuList;
    ListView reviewsList;

    //Private
    private String jsonResponse;
    private String URL = "";
    private UsersDomain user;
    private String REVIEWS_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/reviewee/";
    private String MENU_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/menus/chef/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_chef_profile);

        //Find views
        nameView = findViewById(R.id.nameView);
        ratingBar = findViewById(R.id.ratingBar);
        menuList = findViewById(R.id.menuList);
        reviewsList = findViewById(R.id.reviewsList);

        //Get username from last page
        user = getIntent().getParcelableExtra("user");

        //Include username in url
        REVIEWS_URL += user.getUsername();
        MENU_URL += user.getUsername();

        //Set fields
        nameView.setText(user.getName());
        ratingBar.setRating(user.getRating().floatValue());

        //Make requests
        makeReviewsJSONArrayReq();
        makeMenuJSONArrayReq();
    }

    private void makeReviewsJSONArrayReq() {


        JsonArrayRequest req = new JsonArrayRequest(REVIEWS_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject review = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String reviewer = review.getString("reviewer");
                                String rating = review.getString("rating");
                                String description = review.getString("description");
                                String date = review.getString("date");

                                jsonResponse += ("Reviewer: " + reviewer + "\n");
                                jsonResponse += ("Rating: " + rating + "\n");
                                jsonResponse += ("Chef: " + description + "\n");
                                jsonResponse += ("Date: " + date + "\n");
                                arrayList.add(jsonResponse);

                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerSeeChefProfile.this, android.R.layout.simple_list_item_1, arrayList);
                            reviewsList.setAdapter(arrayAdapter);
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    private void makeMenuJSONArrayReq() {


        JsonArrayRequest req = new JsonArrayRequest(MENU_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject menu = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String title = menu.getString("title");
                                String appetizers = menu.getString("appetizer");
                                String entree = menu.getString("entree");
                                String desert = menu.getString("dessert");
                                String cost = menu.getString("cost");
                                String desc = menu.getString("description");

                                jsonResponse += ("Title: " + title + "\n");
                                jsonResponse += ("Appetizers: " + appetizers + "\n");
                                jsonResponse += ("Entree: " + entree + "\n");
                                jsonResponse += ("Dessert: " + desert + "\n");
                                jsonResponse += ("Cost: " + cost + "\n");
                                jsonResponse += ("Description: " + desc + "\n");
                                arrayList.add(jsonResponse);

                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter(CustomerSeeChefProfile.this, android.R.layout.simple_list_item_1, arrayList);
                            menuList.setAdapter(arrayAdapter);
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }



}
