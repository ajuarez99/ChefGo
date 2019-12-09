package com.example.chefgo.CustomerClient.CustomerRequests;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerMealAcceptedRequests {

    public static void makeJSONArrayReq(final Context c, final String URL, final TextView mealText){
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
                                String jsonResponse = "";

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
                            Toast.makeText(c,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

}
