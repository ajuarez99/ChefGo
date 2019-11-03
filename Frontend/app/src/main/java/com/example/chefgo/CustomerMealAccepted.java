package com.example.chefgo;

import android.os.Bundle;
import android.util.Log;
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

import static com.example.chefgo.app.AppController.TAG;
/**
 * @author SB_3
 *
 */

public class CustomerMealAccepted extends AppCompatActivity {

    private UsersDomain user;

    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/recent/";
    private String jsonResponse;

    private TextView mealText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_meal_accepted);

        user = getIntent().getParcelableExtra("User");

        mealText = findViewById(R.id.mealBox);

        URL += user.getUsername();
        makeJSONArrayReq();

    }


    private void makeJSONArrayReq() {


        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response){
                        Log.d(TAG, response.toString());

                        try {
                            String chef = new String();
                            String ret = new String();
                            for(int i = 0; i < response.length(); i++){
                                JSONObject order = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String oid = order.getString("oid");
                                String dish = order.getString("dish");
                                String date = order.getString("date");
                                chef = order.getString("chef");

                                //If we want all of this info
                                jsonResponse += ("Order id: " + oid + "\n");
                                jsonResponse += ("Dish: " + dish + "\n");
                                jsonResponse += ("Date: " + date + "\n");
                                jsonResponse += ("Chef: " + chef + "\n");
                                ret += jsonResponse;
                            }

                            //Check if there is a chef
                            if(chef == null){
                                mealText.setText("Your meal has not been accepted");
                            }else{//Meal accepted by: chefName
                                JSONObject chefJson = new JSONObject(chef);
                                String chefName = chefJson.getString("name");
                                mealText.setText("Your meal has been accepted! \n\n Chef: " + chefName);
                            }

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
        AppController.getInstance().addToRequestQueue(req);
    }

}
