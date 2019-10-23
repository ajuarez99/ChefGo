package com.example.chefgo.LoginorRegistrationActivity;

import android.widget.Toast;

public class ValidationRegister {

   public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        public static boolean validateEmail(String email){
            return email.trim().matches(emailPattern);
        }

        public static boolean validatePassword(String password, String confirmPassword){
            return password.equals(confirmPassword) && password != null;
        }
}
