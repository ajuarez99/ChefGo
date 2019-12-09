package com.example.chefgo.ChefClient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                Intent chefProfile = new Intent(AddChefMenu.this, ChefProfile.class);
                chefProfile.putExtra("User", user);
                startActivity(chefProfile);
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

        if (title.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a title bitch.", Toast.LENGTH_SHORT).show();
            return;
        } else if (apps.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter an app. N/A for none.", Toast.LENGTH_SHORT).show();
            return;
        } else if (entree.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter an entree. N/A for none.", Toast.LENGTH_SHORT).show();
            return;
        } else if (dessert.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a dessert. N/A for none.", Toast.LENGTH_SHORT).show();
            return;
        } else if (desc.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a description.", Toast.LENGTH_SHORT).show();
            return;
        } else if (cost.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a price.", Toast.LENGTH_SHORT).show();
            return;
        } else {



            //Construct the user object for the menu object
            Map<String, String> userMap =theUser.toJSON();

            JSONObject userObject = new JSONObject(userMap);

            //construct the menu object to be posted
            Map<String, String> menuMap = new HashMap<>();
            menuMap.put("title", title);
            menuMap.put("appetizer", apps);
            menuMap.put("entree", entree);
            menuMap.put("dessert", dessert);
            menuMap.put("description", desc);
            menuMap.put("cost", cost);
            JSONObject menuObject = new JSONObject(menuMap);

            try {
                menuObject.put("chef", userObject);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, menuObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            AppController.getInstance().addToRequestQueue(jsonRequest);
        }
    }



}
