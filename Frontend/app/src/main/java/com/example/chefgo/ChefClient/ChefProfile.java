package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chefgo.R;

import java.util.List;

public class ChefProfile extends AppCompatActivity {
    ListView chefMenu;
    ListView chefReviews;
    RatingBar chefRating;
    TextView chefName;

    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_profile);

        chefMenu = findViewById(R.id.menuList);
        chefReviews = findViewById(R.id.chefReviewList);
        chefRating = findViewById(R.id.chefRating);
        chefName = findViewById(R.id.chefNameText);

        chefMenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });

        chefReviews.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });
    }


}
