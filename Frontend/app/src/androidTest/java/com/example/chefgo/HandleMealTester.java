package com.example.chefgo;

import com.example.chefgo.ChefClient.ChefHandleMealActivity;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class HandleMealTester {

    String jsonResponse;

    public static void main(String[] args){
        HandleMealTester tester = new HandleMealTester();
        tester.testSplitJSONResponse();
    }

    public void testSplitJSONResponse() {

        String[] expectedResponse;
        String[] falseResponse;
        String[] actualResponse;

        jsonResponse = "This" + "\n" + "is" + "\n" + "a" + "\n" + "test";
        try {
            expectedResponse = new String[]{"This", "is", "a", "test"};
            falseResponse = new String[]{"This", "is", "bad", "data"};
            actualResponse = ChefHandleMealActivity.splitJSONResponse(jsonResponse);

            Assert.assertArrayEquals(actualResponse, expectedResponse);
            Assert.assertNotEquals(actualResponse, falseResponse);
            Assert.assertEquals(actualResponse[0], "This");
            Assert.assertNotEquals(actualResponse[3], falseResponse[3]);
            Assert.assertEquals(actualResponse.length, 4);
        } catch(AssertionError ae){
            System.out.println(ae);
        }

        jsonResponse = "Testing" + "\n" + "with" + "\n" + "a" + "\n" + "newline" + "\n" + "char" + "\n" + "at" + "\n" + "end" + "\n";
        try {
            expectedResponse = new String[]{"Testing", "with", "a", "newline", "char", "at", "end"};
            falseResponse = new String[]{"This", "is", "a", "false", "response"};
            actualResponse = ChefHandleMealActivity.splitJSONResponse(jsonResponse);

            Assert.assertArrayEquals(actualResponse, expectedResponse);
            Assert.assertNotEquals(actualResponse, falseResponse);
            Assert.assertEquals(actualResponse[0], "Testing");
            Assert.assertNotEquals(actualResponse[3], falseResponse[3]);
            Assert.assertEquals(actualResponse.length, 7);
        } catch(AssertionError ae){
            System.out.println(ae);
        }
    }
}
