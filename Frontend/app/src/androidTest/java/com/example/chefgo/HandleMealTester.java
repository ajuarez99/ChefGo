package com.example.chefgo;

import com.example.chefgo.ChefClient.ChefHandleMealActivity;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HandleMealTester {

    String jsonResponse;

    public static void main(String[] args){
        HandleMealTester tester = new HandleMealTester();
        ChefHandleMealActivity obj = new ChefHandleMealActivity();
    }

    public void testSplitJSONResponse1(ChefHandleMealActivity obj) {
        jsonResponse = "This" + "\n" + "is" + "\n" + "a" + "\n" + "test";
        String[] expectedResponse = {"This", "is", "a", "test"};
        Assert.assertArrayEquals(obj.splitJSONResponse(jsonResponse), expectedResponse);
    }
}
