package com.example.demo.orderhistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.reviews.Reviews;
import com.example.demo.user.Users;

/**
 * 
 * @author SB_3
 *
 */
@Service
public class OrderHistoryService {

	
	@Autowired
	private OrderHistoryRepo orderHistoryRepo;
	/**
	 * gets all orders
	 * @return list of all orders
	 */
	public List<OrderHistory> getAllOrders() {
		List<OrderHistory> orders = new ArrayList<>();

		orderHistoryRepo.findAll().forEach(orders::add);
		return orders;
	}
	/**
	 * gets an order if it is activly being made
	 * @param isActive flag to tell if an order is active
	 * @return a list of orders that are active
	 */
	public List<OrderHistory> getOrdersByIsActive(int isActive){
		List<OrderHistory> u = orderHistoryRepo.findByIsActive(isActive);
		return u;
	}
	/**
	 * gets a list of orders by chef name
	 * @param username name of chef
	 * @return list of orders by chef name
	 */
	public List<OrderHistory> getOrderByChefName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByChefUsername(username);
		return u;
	}

	public List<OrderHistory> getOrderByCustName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByCustomerUsername(username);
		return u;
	}
	/**
	 * get active orders by a certain chef
	 * @param username name of chef
	 * @return list of active orders of specific chef
	 */
	public List<OrderHistory> getOrderByIsActiveAndChefName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndChefUsername(1,username);
		return u;
	}
	/**
	 * gets active orders that havent had a chef assigned to it yet
	 * @return a list of active orders waiting to be picked up
	 */
	public List<OrderHistory> getOrdersByIsActiveAndWithoutChef() {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndChef(1,null);
		return u;
	}
	
	/**
	 * finds active orders based on customer name
	 * @param username name of customer
	 * @return list of active orders of customer
	 */
	public List<OrderHistory> getOrderByIsActiveAndCustomerName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndCustomerUsername(1,username);
		return u;
	}
	
	/**
	 * get order by id
	 * @param oid id of order
	 * @return order
	 */
	public OrderHistory getOrderByOid(int oid) {
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		
		return u.get();
	}
	
	/**
	 * gets most recent orders  
	 * @param username name of customer
	 * @return list in descending order of orders
	 */
	public List<OrderHistory> getMostRecentOrder(String username) {
		return orderHistoryRepo.findTopByCustomerUsernameOrderByOrderDateDesc(username);
	}
	/**
	 * adds order to order table
	 * @param order Order object to be added to DB
	 */
	public void addOrderToHistory(OrderHistory order) {
		order.setActive(1);
		orderHistoryRepo.save(order);
	}
	/**
	 * updates the dish name of a specfic order
	 * @param id id of order
	 * @param dish updated name of order
	 */
	public void updateDishName(int id, String dish) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setDish(dish);
		orderHistoryRepo.save(update);	
	}
	
	public void updateIsActive(int id, int active) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setActive(active);
		orderHistoryRepo.save(update);	
	}
	/**
	 * adds a review to a specific order
	 * @param id id of order
	 * @param review review of order
	 */
	public void addReview(int id, Reviews review) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setReview(review);
		update.setActive(0);
		orderHistoryRepo.save(update);	
	}
	/**
	 * deletes specific order off of order table
	 * @param oid id of order
	 */
	public void deleteOrder(Integer oid) {
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		OrderHistory toDelete = u.get();
		orderHistoryRepo.delete(toDelete);
	}
	
	public List<Users> getChefByOid(int oid){
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		OrderHistory order = u.get();
		List<Users> chef = new ArrayList<Users>();
		chef.add(order.getChef());
		return chef;
	}
	
}
