package com.example.chefgo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerProfileActivity extends AppCompatActivity {

    Button testButton;
    TextView responseView;
    private String jsonResponse;

    private TextView txtResponse;
    //private String URL = "http://10.0.2.2:8082/orderHistory";
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users";
    private String jsonObjectTag = "jobj_req", tag_json_arry = "jarray_req";
    String tag_string_req ="string_req";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        testButton = findViewById(R.id.testButton);
        txtResponse = findViewById(R.id.responseView);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJSONArrayReq();
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

                                String id = person.getString("id");
                                String email = person.getString("email");
                                String phone = person.getString("phone");
                                String cook = person.getString("cook");
                                String firstName = person.getString("firstName");
                                //JSONObject phone = person.getJSONObject("review");
                                //String home = person.getString("cook");
                                //String mobile = person.getString("firstName");

                                jsonResponse += "id: " + id + "\n\n";
                                jsonResponse += "email: " + email + "\n\n";
                                jsonResponse += "phone: " + phone + "\n\n";
                                jsonResponse += "cook: " + cook + "\n\n";
                                jsonResponse += "firstName: " + firstName + "\n\n";
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



    }



