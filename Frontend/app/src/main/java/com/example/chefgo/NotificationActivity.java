package com.example.chefgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chefgo.LoginorRegistrationActivity.LoginActivity;
import com.example.chefgo.LoginorRegistrationActivity.RegistrationActivity;
import com.example.chefgo.app.AppController;

public class NotificationActivity extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not);

        button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.sendNotification(getApplicationContext());
            }
        });



    }


}
