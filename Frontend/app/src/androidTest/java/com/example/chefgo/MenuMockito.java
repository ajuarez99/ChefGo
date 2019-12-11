package com.example.chefgo;

import com.example.chefgo.DomainObjects.MenuDomain;
import com.example.chefgo.DomainObjects.UsersDomain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MenuMockito {
    @Mock
    MenuDomain mockMenu;
    public String testDess = "mojito";
    public String testApp= "apple";

    @Before
    public void setup(){
        mockMenu = new MenuDomain();

        mockMenu.setAppetizer(testApp);
        mockMenu.setCost(12);
        mockMenu.setDessert(testDess);

    }

    @Test
    public void testSetters(){
        //make sure username setter works
        Assert.assertEquals(mockMenu.getAppetizer(),"apple");

        Assert.assertEquals(mockMenu.getCost(),12, 0.0);

        Assert.assertEquals(mockMenu.getDessert(),"mojito");

    }
}
