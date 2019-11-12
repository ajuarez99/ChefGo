package com.example.chefgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.chefgo.DomainObjects.UsersDomain;

public class CustomerMainActivity extends AppCompatActivity {
    Button customerProfileButton;
    Button customerOrderHistoryButton;
    Button customerOrderMeal;
    Button acceptedMealButton;

    private UsersDomain  user;


    Button activeMealsButton, allergiesButton;

    /**
     * view of the customer to interact with the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (UsersDomain) getIntent().getParcelableExtra("User");
        customerProfileButton = findViewById(R.id.buttonCustomerProfile);
        customerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerProfileIntent = new Intent(CustomerMainActivity.this, CustomerProfileActivity.class);
                customerProfileIntent.putExtra("User", user);
                startActivity(customerProfileIntent);
            }
        });

        customerOrderHistoryButton = findViewById(R.id.buttonCustomerOrderHistory);
        customerOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderHistoryIntent = new Intent(CustomerMainActivity.this, CustomerOrderHistoryActivity.class);
                orderHistoryIntent.putExtra("User", user);
                startActivity(orderHistoryIntent);
            }
        });

        customerOrderMeal = findViewById(R.id.buttonCustomerOrderMeal);
        customerOrderMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent orderMealIntent = new Intent(CustomerMainActivity.this, CustomerOrderMealActivity.class);
                orderMealIntent.putExtra("User", user);
                startActivity(orderMealIntent);
            }
        });

        activeMealsButton = findViewById(R.id.activeMealsButton);
        activeMealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activeMealsIntent = new Intent(CustomerMainActivity.this, ViewMealRequests.class);
                startActivity(activeMealsIntent);
            }
        });

        acceptedMealButton = findViewById(R.id.accpetedMealButton);
        acceptedMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accepted = new Intent(CustomerMainActivity.this, CustomerMealAccepted.class);
                accepted.putExtra("User", user);
                startActivity(accepted);
            }
        });
        allergiesButton = findViewById(R.id.allergies);
        allergiesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent allergiesIntent = new Intent(CustomerMainActivity.this, AllergiesActivity.class);
                allergiesIntent.putExtra("User", user);
                startActivity(allergiesIntent);
            }
        });

    }
}
