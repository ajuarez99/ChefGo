package com.example.chefgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button customerProfileButton;
    Button customerOrderHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerProfileButton = (Button) findViewById(R.id.buttonNext);
        customerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cpButton = new Intent(MainActivity.this, CustomerProfileActivity.class);
                startActivity(cpButton);
            }
        });

        customerOrderHistory = (Button) findViewById(R.id.buttonCustomerOrderHistory);
        customerOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cpoh = new Intent(MainActivity.this, CustomerOrderHistoryActivity.class);
                startActivity(cpoh);
            }
        });
    }
}
