package com.example.chefgo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.AdminClient.UsersAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_chef_profile);

        //Find views
        nameView = findViewById(R.id.nameView);
        ratingBar = findViewById(R.id.ratingBar);
        menuList = findViewById(R.id.menuList);
        reviewsList = findViewById(R.id.reviewsList);


    }


}
