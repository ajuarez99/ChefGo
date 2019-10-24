package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddChefMenu extends AppCompatActivity {

    private UsersDomain user;

    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/menus";

    private EditText titleText;
    private EditText appsText;
    private EditText entreeText;
    private EditText dessertText;
    private EditText descText;
    private EditText costText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chef_menu);

        //Get the user
        user =  getIntent().getParcelableExtra("User");

        //find views
        titleText = findViewById(R.id.titleText);
        appsText = findViewById(R.id.appsText);
        entreeText = findViewById(R.id.entreeText);
        dessertText = findViewById(R.id.dessertText);
        descText = findViewById(R.id.descText);
        costText = findViewById(R.id.costText);
        submitButton = findViewById(R.id.submitButton);

        //Set onClick listeners
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postMenu(titleText.getText().toString(),
                        appsText.getText().toString(),
                        entreeText.getText().toString(),
                        dessertText.getText().toString(),
                        descText.getText().toString(),
                        costText.getText().toString(),
                        user);
//                Intent chefProfile = new Intent(AddChefMenu.this, ChefProfile.class);
//                chefProfile.putExtra("User", user);
//                startActivity(chefProfile);
            }
        });

    }

    private void postMenu(final String title,
                          final String apps,
                          final String entree,
                          final String dessert,
                          final String desc,
                          final String cost,
                          final UsersDomain theUser) {

        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        //Construct the user object for the menu object
        JSONObject userParams = new JSONObject();
        try {
            userParams.put("username", theUser.getUsername());
            userParams.put("name", theUser.getName());
            userParams.put("password", theUser.getPassword());
            userParams.put("userType", theUser.getUserType().toString());
            userParams.put("rating", theUser.getRating().toString());
            userParams.put("address", theUser.getAddress());
            userParams.put("state", theUser.getState());
            userParams.put("zip", theUser.getZip().toString());
        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

//        JSONObject userJSON = new JSONObject(userParams);
//        String userStr = userJSON.toString();

        //construct the menu object to be posted
        JSONObject params = new JSONObject();
        try {
            params.put("title", title);
            params.put("appetizer", apps);
            params.put("entree", entree);
            params.put("dessert", dessert);
            params.put("description", desc);
            params.put("cost", cost);
            params.put("chef", userParams);
        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

//        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("THIS IS THE ERROR: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jsonRequest);
    }



}
