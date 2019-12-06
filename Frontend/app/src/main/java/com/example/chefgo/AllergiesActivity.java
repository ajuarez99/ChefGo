package com.example.chefgo;
/**
 * @author SB_3
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;

public class AllergiesActivity extends AppCompatActivity {

    private UsersDomain user = new UsersDomain();
    private EditText allergy;
    private Button confirm;
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/allergies";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
        allergy = findViewById(R.id.inputAllergy);
        confirm = findViewById(R.id.confirmAllergy);
        user = getIntent().getParcelableExtra("User");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJSONObjectRequest();
            }
        });


    }
    private void postJSONObjectRequest(){

        JSONObject allergyObject = new JSONObject();

        JSONObject customer = new JSONObject(user.toJSON());
        try {
            allergyObject.put("allergy", allergy.getText().toString());
            allergyObject.put("user", customer);
        }catch (JSONException e){
            e.printStackTrace();
        }







        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, URL, allergyObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(AllergiesActivity.this, "Allergy placed successfully", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                @Override
                 public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AllergiesActivity.this, "Allergy placed successfully", Toast.LENGTH_LONG).show();
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
        }) ;
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
