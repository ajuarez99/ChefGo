package com.example.chefgo.LoginorRegistrationActivity;
/**
 * @author SB_3
 *
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.chefgo.AdminClient.AdminActivity;
import com.example.chefgo.ChefClient.ChefMainActivity;
import com.example.chefgo.CustomerMainActivity;
import com.example.chefgo.Geocoding.CustomerGeoCode;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class ValidationRegister {

   public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    /**
     * validates email to make sure user used some sort of valid email
     * @param email  typed email of user
     * @return true if valid email or false
     */

    public static boolean validateEmail(String email){
            return email.trim().matches(emailPattern);
        }

    /**
     *
     * @param password string of password field
     * @param confirmPassword string of confirmPassword field
     * @return true if they match, false otherwise
     */
        public static boolean validatePassword(String password, String confirmPassword){
            if (password != null) {

                return password.equals(confirmPassword) ;
            }
            else{
                return false;
            }
        }

        public static boolean validateAddress(final Context ctx, String address, String state){
            CustomerGeoCode geoCode = new CustomerGeoCode();

            return geoCode.getLocationFromAddress(ctx, address + ", "+state) != null;
        }

}
