package com.example.chefgo.AdminClient;
/**
 * @author SB_3
 *
 */

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.Adapters.UsersAdapter;
import com.example.chefgo.AdminClient.AdminRequests.UsersRequests;
import com.example.chefgo.app.AppController;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chefgo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class UsersActivity extends AppCompatActivity {


    private ListView listView;
    private Button accept;

    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users", jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);


        UsersRequests.getJSONArrayRequest(this,URL,jsonResponse,listView);
        listView = findViewById(R.id.users);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });
    }




}
