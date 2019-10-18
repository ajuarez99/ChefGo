package com.example.chefgo.ChefClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

public class ChefMainActivity extends AppCompatActivity {

    Button activeMealsButton;

    private UsersDomain  user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_main);
        user = (UsersDomain) getIntent().getParcelableExtra("User");

        activeMealsButton = findViewById(R.id.buttonActiveMeals);
        activeMealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent activeMealsIntent = new Intent(ChefMainActivity.this, ChefActiveMealsActivity.class);
                activeMealsIntent.putExtra("User", user);
                startActivity(activeMealsIntent);
            }
        });
    }
}
