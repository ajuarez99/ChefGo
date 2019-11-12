package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryController;
import com.example.demo.orderhistory.OrderHistoryService;
import com.example.demo.user.Users;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Demo1Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Demo1ApplicationTests {

	

	@InjectMocks
	OrderHistoryController controller;
	@Mock
	OrderHistoryService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUpdateOrderChef() throws Exception {
		Users chef = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", 50014);
		OrderHistory Order = new OrderHistory(1, new Date(100), 22.50, null, "chicken", null);
		
		Mockito.when(service.getOrderByOid(1)).thenReturn(Order);
		Mockito.doNothing().when(service).addOrderToHistory(Mockito.any(OrderHistory.class));
		
		controller.updateOrderChef(chef, 1);
		Mockito.verify(service).addOrderToHistory(Order);
		assertEquals(Order.getChef().getUsername(), chef.getUsername());
		
	}



}
