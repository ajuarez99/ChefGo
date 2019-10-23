package com.example.chefgo;
import android.content.SharedPreferences;

import androidx.test.espresso.Espresso;
import androidx.test.runner.AndroidJUnit4;

import com.example.chefgo.LoginorRegistrationActivity.ValidationRegister;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
@RunWith(AndroidJUnit4.class)
public class LoginTesting {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(ValidationRegister.validateEmail("name@email.com"));
    }
    @Test
    public void emailValidator_IncorrectEmailSimple_ReturnsFalse() {
        assertFalse(ValidationRegister.validateEmail("name"));
    }
    @Test
    public void emailValidator_empty_returnsFalse() {
        assertFalse(ValidationRegister.validateEmail(""));
    }
    @Test
    public void passwordValidator_dontMatch_returnFalse(){
        assertFalse(ValidationRegister.validatePassword("pass","passs"));
    }
    @Test
    public void passwordValidator_Match_returnTrue(){
        assertTrue(ValidationRegister.validatePassword("pass","pass"));
    }
    @Test
    public void passwordValidator_null_returnFalse(){
        assertFalse(ValidationRegister.validatePassword(null,"pass"));


}

}
