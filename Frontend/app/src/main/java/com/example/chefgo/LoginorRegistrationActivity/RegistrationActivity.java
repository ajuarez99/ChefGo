package com.example.chefgo.LoginorRegistrationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import com.example.chefgo.R;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    private Button submit;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private EditText fname;
    private EditText lname;
    private EditText address;
    private EditText zipcode;
    private Spinner state;
    private  Spinner userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submit = findViewById(R.id.submit);
        username = findViewById(R.id.username);
        password = findViewById((R.id.password));
        confirmPassword = findViewById(R.id.cPassword);
        email= findViewById(R.id.email);
        fname =findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        address = findViewById(R.id.address);
        zipcode = findViewById(R.id.zipcode);
        state = findViewById(R.id.stateSpinner);
        userType = findViewById(R.id.userTypeSpinner);
        fillStateSpinner();
        fillUserTypeSpinner();

    }
   private void fillStateSpinner(){
       List<String>  UserTypes = new ArrayList<String>();

       UserTypes.add("Customer");
       UserTypes.add("Chef");
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, UserTypes);

       // Drop down layout style - list view with radio button
       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // attaching data adapter to spinner
       userType.setAdapter(dataAdapter);
    }
    private void fillUserTypeSpinner(){
    List<String> states = new ArrayList<String>();

    states.add("Alabama");
    states.add("Alaska");
    states.add("Arizona");
    states.add("Arkansas");
    states.add("California");
    states.add("Colorado");
    states.add("Connecticut");
    states.add("Delaware");
    states.add("Florida");
    states.add("Georgia");
    states.add("Hawaii");
    states.add("Idaho");
    states.add("Illinois");
    states.add("Indiana");
    states.add("Iowa");
    states.add("Kansas");
    states.add("Kentucky");
    states.add("Louisiana");
    states.add("Maine");
    states.add("Maryland");
    states.add("Massachusetts");
    states.add("Michigan");
    states.add("Minnesota");
    states.add("Mississippi");
    states.add("Missouri");
    states.add("Montana");
    states.add("Nebraska");
    states.add("Nevada");
    states.add("New Hampshire");
    states.add("New Jersey");
    states.add("New Mexico");
    states.add("New York");
    states.add("North Carolina");
    states.add("North Dakota");
    states.add("Ohio");
    states.add("Oklahoma");
    states.add("Oregon");
    states.add("Pennsylvania");
    states.add("Rhode Island");
    states.add("South Carolina");
    states.add("South Dakota");
    states.add("Tennessee");
    states.add("Texas");
    states.add("Utah");
    states.add("Vermont");
    states.add("Virginia");
    states.add("Washington");
    states.add("West Virginia");
    states.add("Wisconsin");
    states.add("Wyoming");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        userType.setAdapter(dataAdapter);
    }
    }
}
