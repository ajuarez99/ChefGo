package com.example.app309;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] options = {"Rock", "Paper", "Scissors"};
        String[] computerChoices = new String[3];
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        generateGame(options,computerChoices);

        //Left Option Press
        final Button Left = findViewById(R.id.button);
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            TextView choice = Left;
            TextView userScreen = findViewById(R.id.userPick);
            userScreen.setText(choice.toString());

            }
        });
    }
    public void generateGame(String[] options, String[] computerChoices){
        Random rand = new Random();

        for(int i = 0; i<2;i++){
            int choice = rand.nextInt(3);
            computerChoices[i] = options[choice];
        }
        int one = rand.nextInt(3);
        int two = rand.nextInt(3);
        int three = rand.nextInt(3);
        TextView button = findViewById((R.id.button));
        button.setText((options[one]));

        TextView button2 = findViewById((R.id.button2));
        button2.setText((options[two]));

        TextView button3 = findViewById((R.id.option3));
        button3.setText((options[three]));
    }
}
