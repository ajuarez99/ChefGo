package com.example.chefgo.Validation;


import android.content.Context;

public interface IValidationRegister {
    boolean validateEmail(String email);
    boolean validatePassword(String password, String confirmPassword);
    boolean validateAddress(final Context ctx, String address, String state);
}
