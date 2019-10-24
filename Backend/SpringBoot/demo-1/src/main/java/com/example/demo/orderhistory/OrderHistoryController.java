package com.example.demo.orderhistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.reviews.Reviews;

@RestController
public class OrderHistoryController {
	@Autowired
	private OrderHistoryService orderHistory;
	
	@RequestMapping("/orderHistory")
	public List<OrderHistory> displayAllOrders() {
		return orderHistory.getAllOrders();
	}
	
	@RequestMapping("/orderHistory/active")
	public List<OrderHistory> displayActiveOrders() {
<<<<<<< HEAD
		return orderHistory.getOrdersByIsActiveAndWithoutChef();
=======
		return orderHistory.getOrdersByIsActive(1);
>>>>>>> 17-swipe-meals
	}
	
	@RequestMapping("/orderHistory/activeChef/{username}")
	public List<OrderHistory> displayActiveOrdersByChef(@PathVariable String username) {
		return orderHistory.getOrderByIsActiveAndChefName(username);
	}
	
	@RequestMapping("/orderHistory/activeCust/{username}")
	public List<OrderHistory> displayActiveOrdersByCustomer(@PathVariable String username) {
		return orderHistory.getOrderByIsActiveAndCustomerName(username);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/orderHistory")
	public void addOrder(@RequestBody OrderHistory order) {
		orderHistory.addOrderToHistory(order);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/orderHistory/review/{oid}")
	public void addReview(@RequestBody Reviews review, @PathVariable Integer oid) {
		orderHistory.addReview(oid, review);
	}
	
	@RequestMapping("/order/{username}")
	public List<OrderHistory> getOrderByChef(@PathVariable String username) {
		return orderHistory.getOrderByChefName(username);
	}
	
	@RequestMapping("/orderHistory/{id}")
	public OrderHistory displayOrderById(@PathVariable int id) {
		return orderHistory.getOrderByOid(id);
	}

	 
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public void deleteOrder(@PathVariable Integer id) {
		orderHistory.deleteOrder(id);
	}
}
