package com.example.chefgo;

import com.example.chefgo.DomainObjects.OrderHistoryDomain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class OrderHistoryTest {

    OrderHistoryDomain ohd;

    public static void main(String[] args){
        OrderHistoryTest tester = new OrderHistoryTest();
        tester.testConstructor();
    }

    @Test
    private void testConstructor(){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dateRepresentation = cal.getTime();

        ohd = new OrderHistoryDomain(1, dateRepresentation, 12.0, "Carter", "food", "customer");
    }
}
