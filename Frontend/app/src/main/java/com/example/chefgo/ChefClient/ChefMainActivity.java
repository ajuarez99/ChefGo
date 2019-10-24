package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

public class ChefMainActivity extends AppCompatActivity {
    Button profileButton;
    private UsersDomain user;
    Button activeMealsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_main);
        user =  getIntent().getParcelableExtra("User");

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(ChefMainActivity.this, ChefProfile.class);
                profile.putExtra("User", user);
                startActivity(profile);
                activeMealsButton = findViewById(R.id.buttonActiveMeals);
                activeMealsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent activeMealsIntent = new Intent(ChefMainActivity.this, ChefActiveMealsActivity.class);
                        activeMealsIntent.putExtra("User", user);
                        startActivity(activeMealsIntent);
                    }
                });
            }
        });
    }
}