package com.example.chefgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button customerProfileButton;
    Button customerOrderHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerProfileButton = findViewById(R.id.buttonNext);
        customerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerProfileIntent = new Intent(MainActivity.this, CustomerProfileActivity.class);
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

    }
}
