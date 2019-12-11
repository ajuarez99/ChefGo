package com.example.chefgo;

import android.content.Context;
import android.location.Geocoder;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.example.chefgo.CustomerClient.CustomerMapsActivity;
import com.example.chefgo.CustomerClient.Geocoding.CustomerGeoCode;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class GeocodeTesting {
    private CustomerGeoCode gecode = new CustomerGeoCode();


    Context ctx;


    @Test
    public void Geocoder_AllNullChecks_ReturnsNUll() {
        assertEquals(null,gecode.getLocationFromAddress(null,null));
    }

}
