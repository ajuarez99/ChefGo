package com.example.chefgo.LoginorRegistrationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
    private Button submit;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private EditText fname;
    private EditText lname;
    private EditText address;
    private EditText zipcode;
    private Spinner state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submit = findViewById(R.id.submit);
        submit.setEnabled(true);
        username = findViewById(R.id.username);
        password = findViewById((R.id.password));
        confirmPassword = findViewById(R.id.cPassword);
        email= findViewById(R.id.email);
        fname =findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        address = findViewById(R.id.address);
        zipcode = findViewById(R.id.zipcode);
        state = findViewById(R.id.stateSpinner);

        fillStateSpinner();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //    submitRegistration();
            }
        });




    }
//    private void submitRegistration() {
//
//        String emailAddress = email.getText().toString().trim();
//
//        if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
//
//        }
//        else{
//
//        }
//        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
//
//        Map<String, String> params = new HashMap();
//        params.put("username", "jstr");
//        params.put("email", "jstrobe@iastate.edu");
//        params.put("fName", firstName);
//        params.put("lName", "Strobel");
//        params.put("password", "password");
//        params.put("userType", "1");
//        params.put("rating", rating);
//        params.put("address", "Morningside St");
//        params.put("state", "Iowa");
//        params.put("zip", "50014");
////        params.put("allergies","");
//
//        JSONObject parameters = new JSONObject(params);
//
//        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, parameters, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                System.out.println(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(getApplicationContext(), "POSTED", Toast.LENGTH_SHORT);
//                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println("THIS IS THE ERROR: " + error.getMessage());
//            }
//        });
//
//        AppController.getInstance().addToRequestQueue(jsonRequest);
//    }

    private void fillStateSpinner(){
    List<String> states = new ArrayList<String>();

    states.add("Alabama");
    states.add("Alaska");
    states.add("Arizona");
    states.add("Arkansas");
    states.add("California");
    states.add("Colorado");
    states.add("Connecticut");
    states.add("Delaware");
    states.add("Florida");
    states.add("Georgia");
    states.add("Hawaii");
    states.add("Idaho");
    states.add("Illinois");
    states.add("Indiana");
    states.add("Iowa");
    states.add("Kansas");
    states.add("Kentucky");
    states.add("Louisiana");
    states.add("Maine");
    states.add("Maryland");
    states.add("Massachusetts");
    states.add("Michigan");
    states.add("Minnesota");
    states.add("Mississippi");
    states.add("Missouri");
    states.add("Montana");
    states.add("Nebraska");
    states.add("Nevada");
    states.add("New Hampshire");
    states.add("New Jersey");
    states.add("New Mexico");
    states.add("New York");
    states.add("North Carolina");
    states.add("North Dakota");
    states.add("Ohio");
    states.add("Oklahoma");
    states.add("Oregon");
    states.add("Pennsylvania");
    states.add("Rhode Island");
    states.add("South Carolina");
    states.add("South Dakota");
    states.add("Tennessee");
    states.add("Texas");
    states.add("Utah");
    states.add("Vermont");
    states.add("Virginia");
    states.add("Washington");
    states.add("West Virginia");
    states.add("Wisconsin");
    states.add("Wyoming");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        state.setAdapter(dataAdapter);
    }

}
