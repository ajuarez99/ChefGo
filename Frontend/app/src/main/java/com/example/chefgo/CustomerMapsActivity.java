package com.example.chefgo;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.AdminClient.UsersAdapter;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.Geocoding.CustomerGeoCode;
import com.example.chefgo.app.AppController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CustomerGeoCode geocode;
    private UsersDomain user;
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users/chefs/";
    private String jsonResponse;
    private List<UsersDomain> chefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_maps);
        chefs = new ArrayList<UsersDomain>();
        user = getIntent().getParcelableExtra("User");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        URL += user.getZip();
        getJSONArrayRequest(mapFragment, this);




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocode = new CustomerGeoCode();
        LatLng currentUserLocation = geocode.getLocationFromAddress(this, user.getAddress()+ ", " +user.getState() );

        mMap.addMarker(new MarkerOptions().position(currentUserLocation).title("Marker in home").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentUserLocation,17));

        for(int i =0;i<chefs.size();i++){
            LatLng chefHomeLocation = geocode.getLocationFromAddress(this, chefs.get(i).getAddress() + ", " + chefs.get(i).getState());
            mMap.addMarker((new MarkerOptions().position(chefHomeLocation).title(""+chefs.get(i).getName())));
        }
    }

    private void getJSONArrayRequest(final SupportMapFragment map , final OnMapReadyCallback ready){

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                UsersDomain chef = new UsersDomain();
                                JSONObject order = (JSONObject) response.get(i);


                                 chef.setUsername(order.getString("username"));
                                 chef.setName(order.getString("name"));
                                 chef.setAddress((order.getString("address")));
                                 chef.setState(order.getString("state"));



                                chefs.add(chef);

                            }
                            map.getMapAsync(ready);

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
