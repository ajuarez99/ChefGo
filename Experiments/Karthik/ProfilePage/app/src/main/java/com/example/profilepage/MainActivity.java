package com.example.profilepage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity  {


    ImageView profilePic;
    Button profile;
    private static final int GET_FROM_GALLERY = 3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.Create);
        profile = findViewById(R.id.profile);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.Name);
                EditText addr = findViewById(R.id.Address);
                EditText email = findViewById(R.id.Email);
                EditText number = findViewById(R.id.phoneNumber);

                String userInfo = name.getText().toString() + "\n" + addr.getText().toString() +
                        "\n" + email.getText().toString() + "\n" + number.getText().toString() + "\n";

                Log.d("User is:", userInfo);

            }
        });




        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == GET_FROM_GALLERY && resultCode == RESULT_OK && intent != null) {
            Uri selectedImage = intent.getData();
            profilePic = findViewById(R.id.profilePic);
            profilePic.setImageURI(selectedImage);
        }
    }

}
