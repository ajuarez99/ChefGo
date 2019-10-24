package com.example.demo.orderhistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.reviews.Reviews;


@Service
public class OrderHistoryService {

	
	@Autowired
	private OrderHistoryRepo orderHistoryRepo;
	
	public List<OrderHistory> getAllOrders() {
		List<OrderHistory> orders = new ArrayList<>();

		orderHistoryRepo.findAll().forEach(orders::add);
		return orders;
	}
	
	public List<OrderHistory> getOrdersByIsActive(int isActive){
		List<OrderHistory> u = orderHistoryRepo.findByIsActive(isActive);
		return u;
	}
	
	public List<OrderHistory> getOrderByChefName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByChefUsername(username);
		return u;
	}
	
	
	public List<OrderHistory> getOrderByIsActiveAndChefName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndChefUsername(1,username);
		return u;
	}
	
	public List<OrderHistory> getOrderByIsActiveAndCustomerName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndCustomerUsername(1,username);
		return u;
	}
	
	
	public OrderHistory getOrderByOid(int oid) {
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		
		return u.get();
	}

	public void addOrderToHistory(OrderHistory order) {
		order.setActive(1);
		orderHistoryRepo.save(order);
	}
	
	public void updateDishName(int id, String dish) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setDish(dish);
		orderHistoryRepo.save(update);	
	}
	
	public void addReview(int id, Reviews review) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setReview(review);
		orderHistoryRepo.save(update);	
	}
	
	public void deleteOrder(Integer oid) {
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		OrderHistory toDelete = u.get();
		orderHistoryRepo.delete(toDelete);
	}
	


	public void deleteOrder(int id) {
		orderHistoryRepo.deleteById(id);
		
	}
	
	
}
