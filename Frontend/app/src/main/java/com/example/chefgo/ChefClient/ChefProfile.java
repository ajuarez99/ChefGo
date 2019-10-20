package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
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

public class ChefProfile extends AppCompatActivity {
    ListView chefMenu;
    ListView chefReviews;
    RatingBar chefRating;
    TextView chefName;

    private String jsonResponse;

    private UsersDomain user;

    private String REVIEWS_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/reviewee/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_profile);

        //Find necessary views
        chefMenu = findViewById(R.id.menuList);
        chefReviews = findViewById(R.id.chefReviewList);
        chefRating = findViewById(R.id.chefRating);
        chefName = findViewById(R.id.chefNameText);

        //Get user info from last page
        user =  getIntent().getParcelableExtra("User");

        //Set url to include the chef username
        REVIEWS_URL += user.getUsername();

        chefMenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });

        chefReviews.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });

        //Set name
        String name = user.getfName() + " " + user.getlName();
        chefName.setText(name);

        //Set rating
        chefRating.setRating(user.getRating().floatValue());

        //Set reviews
        makeReviewsJSONArrayReq();
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
                            ArrayAdapter arrayAdapter = new ArrayAdapter(ChefProfile.this, android.R.layout.simple_list_item_1, arrayList);
                            chefReviews.setAdapter(arrayAdapter);
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
