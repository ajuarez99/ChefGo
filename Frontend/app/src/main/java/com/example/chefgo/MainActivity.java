package com.example.chefgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.DomainObjects.UsersDomain;

public class MainActivity extends AppCompatActivity {
    Button customerProfileButton;
    Button customerOrderHistoryButton;
    Button customerOrderMeal;
    private UsersDomain  user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (UsersDomain) getIntent().getParcelableExtra("User");
        customerProfileButton = findViewById(R.id.buttonCustomerProfile);
        customerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerProfileIntent = new Intent(MainActivity.this, CustomerProfileActivity.class);
                customerProfileIntent.putExtra("User", user);
                startActivity(customerProfileIntent);
            }
        });

        customerOrderHistoryButton = findViewById(R.id.buttonCustomerOrderHistory);
        customerOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderHistoryIntent = new Intent(MainActivity.this, CustomerOrderHistoryActivity.class);
                startActivity(orderHistoryIntent);
            }
        });

        customerOrderMeal = findViewById(R.id.buttonCustomerOrderMeal);
        customerOrderMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent orderMealIntent = new Intent(MainActivity.this, CustomerOrderMealActivity.class);
                startActivity(orderMealIntent);
            }
        });
    }
}
