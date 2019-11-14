package com.example.demo.user;
/**
 * @author SB_3
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.orderhistory.OrderHistory;


@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	/**
	 * Returns list of all User objects in DB
	 * @return List of all User objects
	 */
	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<>();

		userRepo.findAll().forEach(users::add);
		return users;
	}

	/**
	 * Returns User with given username
	 * @param username Username of User to find
	 * @return User object with specified username
	 */
	public Users getUserByUsername(String username) {
		Optional<Users> u = userRepo.findByUsername(username);
		return u.get();
	}
	
	public List<Users> getUsersByZipAnduserType(Integer zip){
		Optional<List<Users>> u = userRepo.findByZipAndUserType(zip,(Integer) 2);
		return u.get();
	}
	/**
	 * Adds User to DB
	 * @param user User object to be added
	 */
	public void addUser(Users user) {
		userRepo.save(user);
	}
	
	/**
	 * Updates User's password
	 * @param username Username of User that needs to be changed
	 * @param password New password
	 */
	public void updatePassword(String username, String password) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users update = u.get();
		update.setPassword(password);
		userRepo.save(update);
			
	}
	
	/**
	 * Updates User's email 
	 * @param username Username of User that needs to be changed
	 * @param email New email
	 */
	public void updateEmail(String username, String email) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users update = u.get();
		update.setEmail(email);
		userRepo.save(update);
			
	}
	/**
	 * Updates User's name
	 * @param username Username of User that needs to be changed 
	 * @param name New name
	 */
	public void updateName(String username, String name) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users update = u.get();
		update.setName(name);
		userRepo.save(update);
	}
	
	public void updateAddress(String username, String address, String state, Integer zip) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users update = u.get();
		update.setAddress(address);
		update.setState(state);
		update.setZip(zip);
		userRepo.save(update);
	}

	public void deleteUser(String username) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users toDelete = u.get();
		userRepo.delete(toDelete);
	}
	
	public void updateUserType(String username, Integer type) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users toUpdate = u.get();
		toUpdate.setUserType(type);
		userRepo.save(toUpdate);
	}
	
//	public void addOrder(String username, OrderHistory order) {
//		Optional<Users> u = userRepo.findByUsername(username);
//		Users madeOrder = u.get();
//		madeOrder.addOrder(order);
//		userRepo.save(madeOrder);
//	}

	
	
}
