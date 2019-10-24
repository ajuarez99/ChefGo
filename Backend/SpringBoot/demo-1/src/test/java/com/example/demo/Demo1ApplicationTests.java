package com.example.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryController;
import com.example.demo.orderhistory.OrderHistoryRepo;
import com.example.demo.orderhistory.OrderHistoryService;
import com.example.demo.user.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
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
