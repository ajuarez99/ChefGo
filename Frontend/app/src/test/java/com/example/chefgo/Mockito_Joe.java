package com.example.chefgo;

import android.util.Log;

import com.example.chefgo.ProcessAndParse.ParserUtil;
import com.example.chefgo.ProcessAndParse.ProcessReviews;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mockito_Joe {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /*
    To test that the first name is being returned.
     */
    @Test
    public void getRevieweeFirstNameTrue() {

        //Mock the unimplemented ProcessReviews class
        ProcessReviews process = mock(ProcessReviews.class);

        JSONObject review = new JSONObject();
        try{
            review.put("reviewer", "Joe Strobel");
            review.put("reviewee", "Karthik Prakash");
        }catch(JSONException e){
            Log.d("Message:", e.getMessage());
        }


        //Mock the strings returned by ProcessReviews functions
        when(process.getReviewee(review)).thenReturn("Karthik Prakash");

        //Get the reviewee (Mocked)
        String reviewee = process.getReviewee(review);

        //Get the first name of the reviewee
        String revieweeFname = ParserUtil.parseName(reviewee);

        //Test that the parser util worked correctly
        Assert.assertEquals("Karthik", revieweeFname);
    }

    /*
    This test makes sure that the parser does not include a space behind the first name
     */
    @Test
    public void getRevieweeFirstNameFalse() {

        //Mock the unimplemented ProcessReviews class
        ProcessReviews process = mock(ProcessReviews.class);

        JSONObject review = new JSONObject();
        try{
            review.put("reviewer", "Joe Strobel");
            review.put("reviewee", "Karthik Prakash");
        }catch(JSONException e){
            Log.d("Message:", e.getMessage());
        }


        //Mock the strings returned by ProcessReviews functions
        when(process.getReviewee(review)).thenReturn("Karthik Prakash");

        //Get the reviewee (Mocked)
        String reviewee = process.getReviewee(review);

        //Get the first name of the reviewee
        String revieweeFname = ParserUtil.parseName(reviewee);

        //Test that the parser util did not include the space behind the first name
        Assert.assertNotEquals("Karthik ", revieweeFname);
    }

    /*
    To test if the last name is being parsed correctly.
     */
    @Test
    public void getReviewerLastNameTrue(){
        //Mock the unimplemented ProcessReviews class
        ProcessReviews process = mock(ProcessReviews.class);

        JSONObject review = new JSONObject();
        try{
            review.put("reviewer", "Joe Strobel");
            review.put("reviewee", "Karthik Prakash");
        }catch(JSONException e){
            Log.d("Message:", e.getMessage());
        }


        //Mock the strings returned by ProcessReviews functions
        when(process.getReviewee(review)).thenReturn("Karthik Prakash");

        //Get the reviewee (Mocked)
        String reviewee = process.getReviewee(review);

        //Get the last name of the reviewee
        String revieweeLname = ParserUtil.parseLastName(reviewee);

        //Test that the parser util correctly grabbed the full last name
        Assert.assertEquals("Prakash", revieweeLname);
    }

}


