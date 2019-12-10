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
import com.android.volley.toolbox.StringRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static com.example.chefgo.app.AppController.TAG;
/**
 * @author SB_3
 *
 */

public class CustomerProfileActivity extends AppCompatActivity {

    private TextView nameView;
    private EditText nameInput;
    private Button postNameButton, profilePicButton;
    private RatingBar ratingBar;
    private ImageView profilePic;
    private UsersDomain user;
    private static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assignComponents();
        user = getIntent().getParcelableExtra("User");
        if (user.getName() != null)
            nameView.setText(user.getName());

        if(user.getRating() != null) {
            ratingBar.setRating(user.getRating().floatValue());
        }
        else{
            ratingBar.setRating(0);
        }
        setListeners();
    }

    private void assignComponents(){
        setContentView(R.layout.activity_customer_profile);
        nameInput = findViewById(R.id.nameInput);
        postNameButton = findViewById(R.id.postNameButton);
        nameView = findViewById(R.id.nameText);
        ratingBar = findViewById(R.id.ratingBar);
        profilePicButton = findViewById(R.id.setProfPic);
        profilePic = findViewById(R.id.profilePic);
    }

    private void setListeners(){
        postNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        profilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });
    }

    private void updateUser() {

        String[] newName = nameInput.getText().toString().split(" ");
        String fName = newName[0];
        String lName = newName[1];

        String update_user_url = "http://coms-309-sb-3.misc.iastate.edu:8080//user/";
        update_user_url += (user.getUsername() + "/");
        update_user_url += (fName + "/");
        update_user_url += (lName + "/");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, update_user_url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);

        nameInput.setText(null);
        nameView.setText(fName + " " + lName);
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



