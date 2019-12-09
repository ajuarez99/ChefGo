package com.example.chefgo.CustomerClient;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.CustomerOrderHistoryRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

/**
 * @author SB_3
 */

public class CustomerOrderHistoryActivity extends AppCompatActivity {

    private TextView description;
    private ListView listView;
    UsersDomain user;
    private String  URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_history);
        user = new UsersDomain();
        user = getIntent().getParcelableExtra("User");
        listView = findViewById(R.id.listview);
        description = findViewById(R.id.orderHistoryDescription);

        CustomerOrderHistoryRequests.getJSONArrayRequest(this.getApplicationContext(), URL, description, listView, user);
    }

}

