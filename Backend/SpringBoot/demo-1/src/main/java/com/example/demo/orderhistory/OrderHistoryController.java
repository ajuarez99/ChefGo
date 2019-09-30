package com.example.demo.orderhistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderHistoryController {
	@Autowired
	private OrderHistoryService orderHistory;
	
	@RequestMapping("/orderHistory")
	public List<OrderHistory> displayAllOrders() {
		return orderHistory.getAllOrders();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/orderHistory")
	public void addOrder(@RequestBody OrderHistory order) {
		orderHistory.addOrderToHistory(order);
	}
	
	@RequestMapping("/orderHistory/{id}")
	public OrderHistory displayOrderById(@PathVariable int id) {
		return orderHistory.getOrderById(id);
	}

	@RequestMapping("/orderHistory/{username}")
	public List<OrderHistory> getOrderByChef(@PathVariable String username) {
		return orderHistory.getOrderByChefName(username);
	}

}
