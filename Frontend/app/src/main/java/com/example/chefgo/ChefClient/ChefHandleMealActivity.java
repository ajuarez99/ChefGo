package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chefgo.R;

public class ChefHandleMealActivity extends AppCompatActivity {

    String itemDescription;
    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_handle_meal);
        itemDescription = getIntent().getStringExtra("JSON_RESPONSE");
        description = findViewById(R.id.textView);
    }
}
