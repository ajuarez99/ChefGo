package com.example.chefgo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class CustomerReviewOrder extends AppCompatActivity {

    private String selectedOrder;
    private TextView reviewOrderTitle, orderDescriptionTitle, orderDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review_order);
        selectedOrder = getIntent().getStringExtra("order");

        reviewOrderTitle = findViewById(R.id.reviewOrderTitle);

        orderDescriptionTitle = findViewById(R.id.orderDescriptionTitle);
        orderDescriptionTitle.setPaintFlags(orderDescriptionTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        orderDescription = findViewById(R.id.orderDescription);
        orderDescription.setText(selectedOrder);
    }
}
