package com.example.chefgo.CustomerClient;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;
/**
 * @author SB_3
 *
 */

public class CustomerProfileActivity extends AppCompatActivity {

    Button refreshButton;
    TextView responseView;
    TextView nameView;
    EditText nameInput;
    Button postNameButton;
    Button profilePicButton;
    RatingBar ratingBar;
    ImageView profilePic;
    String username;
    String rate;
    private String jsonResponse;

    private TextView txtResponse;
    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users";
    //private String URL = "http://10.0.2.2:8080/user";

    private String jsonObjectTag = "jobj_req", tag_json_arry = "jarray_req";
    String tag_string_req = "string_req";
    String FName;

    private UsersDomain user ;

    private static final int GET_FROM_GALLERY = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_profile);
        refreshButton = findViewById(R.id.testButton);
        txtResponse = findViewById(R.id.responseView);
        nameInput = findViewById(R.id.nameInput);
        postNameButton = findViewById(R.id.postNameButton);
        nameView = findViewById(R.id.nameText);



        user =  getIntent().getParcelableExtra("User");

        ratingBar = findViewById(R.id.ratingBar);
        profilePicButton = findViewById(R.id.setProfPic);
        profilePic = findViewById(R.id.profilePic);
        String name = user.getName();
        nameView.setText(name);
        if(user.getRating() != null) {
            ratingBar.setRating(user.getRating().floatValue());
        }
        else{
            ratingBar.setRating(0);
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
                rate = Float.toString(v);
            }
        });

        postNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUser(nameView.getText().toString(), rate);
                FName = nameInput.getText().toString();
                makeJSONArrayReq();
                nameView.setText(FName);
                nameInput.setText(null);
            }
        });
/*        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJSONArrayReq();
                nameView.setText(FName);
                nameInput.setText(null);
            }
        });*/

        profilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });
    }


    private void makeJSONArrayReq() {


        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response.get(i);

                                String firstName = person.getString("name");
                                double rating = person.getDouble("rating");
                                username = person.getString("username");
                                if(username.equals(user.getUsername())){
                                    FName = firstName;
                                }

                                jsonResponse += "firstName: " + firstName + "\n\n";
                                nameView.setText(firstName);
                                ratingBar.setRating((float) rating);

                            }

                            txtResponse.setText(jsonResponse);
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

    private void postUser(final String name, final String rating) {

        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        Map<String, String> params = new HashMap();
        params.put("username", user.getUsername());
        params.put("email", user.getEmail());
        params.put("name", name);
        params.put("password", user.getPassword());
        params.put("userType", user.getUserType().toString());
        params.put("rating", rating);
        params.put("address", user.getAddress());
        params.put("state", user.getState());
        params.put("zip", user.getZip().toString());

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "POSTED", Toast.LENGTH_SHORT);
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("THIS IS THE ERROR: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jsonRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == GET_FROM_GALLERY && resultCode == RESULT_OK && intent != null) {
            Uri selectedImage = intent.getData();
            profilePic = findViewById(R.id.profilePic);
            profilePic.setImageURI(selectedImage);
        }
    }
}



