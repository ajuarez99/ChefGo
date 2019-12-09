package com.example.chefgo.CustomerClient;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.CustomerMealAcceptedRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
/**
 * @author SB_3
 *
 */

public class CustomerMealAccepted extends AppCompatActivity {

    private UsersDomain user;

    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/recent/";
    private String jsonResponse;

    private TextView mealText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_meal_accepted);

        user = getIntent().getParcelableExtra("User");

        mealText = findViewById(R.id.mealBox);

        URL += user.getUsername();
        CustomerMealAcceptedRequests.makeJSONArrayReq(getApplicationContext(), URL, mealText);
    }

}
