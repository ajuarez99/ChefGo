package com.example.chefgo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class OrderHistoryAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private String jsonResponse;
    private UsersDomain user = new UsersDomain();

    public OrderHistoryAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     *
     * @return size of list
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     *
     * @param position
     * @return object at position
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     *
     * @param position
     * @return null
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_order_history, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.list_itm_string);
        listItemText.setText(list.get(position));
        String orderToCheck = list.get(position);
        int active = orderToCheck.indexOf("Active");
        active += 8;
        String isActive = "";
        while(orderToCheck.charAt(active) != '\n'){
            isActive += orderToCheck.charAt(active);
            active++;
        }

        Button reviewsBtn = (Button)view.findViewById(R.id.review_btn);
        if(isActive.equalsIgnoreCase("yes")) reviewsBtn.setText("Chat");
        Button profileBtn = (Button)view.findViewById(R.id.chefProfile_btn);

        final View vi = view;
        //Do stuff with review button
        reviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = list.get(position);
                String oid = "";
                int i = 10;
                while(order.charAt(i) != '\n'){
                    oid += order.charAt(i);
                    i++;
                }
                String url = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/"+ oid +"/chef";
                JsonArrayRequest req = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d(TAG, response.toString());
                                String username = "";
                                String name = "";
                                String rating = "";
                                try {
                                    jsonResponse = "";
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject person = (JSONObject) response.get(i);
                                        user.setUsername(person.getString("username"));
                                        user.setEmail(person.getString("email"));
                                        user.setName(person.getString("name"));
                                        user.setAddress(person.getString("address"));
                                        user.setState(person.getString("state"));
                                        user.setZip(person.getInt("zip"));
                                        user.setPassword(person.getString("password"));
                                        user.setRating(person.getDouble("rating"));
                                        user.setUserType(person.getInt("userType"));
                                    }

                                } catch (JSONException e) {
                                    VolleyLog.d(TAG, "Error: " + e.getMessage());
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(req);

                Intent reviewIntent = new Intent(vi.getContext(), CustomerReviewOrder.class);
                reviewIntent.putExtra("order", order);
                reviewIntent.putExtra("User", user);
                reviewIntent.putExtra("oid", getOrderID(order));
                context.startActivity(reviewIntent);
            }
        });


        //Do stuff with profile button
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = list.get(position);
                String oid = "";
                int i = 10;
                while(order.charAt(i) != '\n'){
                    oid += order.charAt(i);
                    i++;
                }
                String url = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/"+ oid +"/chef";
                JsonArrayRequest req = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d(TAG, response.toString());
                                String username = "";
                                String name = "";
                                String rating = "";
                                try {
                                    jsonResponse = "";
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject person = (JSONObject) response.get(i);
                                        user.setUsername(person.getString("username"));
                                        user.setEmail(person.getString("email"));
                                        user.setName(person.getString("name"));
                                        user.setAddress(person.getString("address"));
                                        user.setState(person.getString("state"));
                                        user.setZip(person.getInt("zip"));
                                        user.setPassword(person.getString("password"));
                                        user.setRating(person.getDouble("rating"));
                                        user.setUserType(person.getInt("userType"));
                                    }

                                } catch (JSONException e) {
                                    VolleyLog.d(TAG, "Error: " + e.getMessage());
                                }
                                Intent i = new Intent(vi.getContext(), CustomerSeeChefProfile.class);
                                i.putExtra("user", user);
                                context.startActivity(i);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(req);
            }
        });

        return view;
    }

    private int getOrderID(String selectedOrder){
        String[] orderDescription = selectedOrder.split("\n");
        return Integer.parseInt(orderDescription[0].replaceAll("[\\D]", ""));
    }
}
