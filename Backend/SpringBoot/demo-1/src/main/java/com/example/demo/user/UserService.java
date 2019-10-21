package com.example.demo.user;

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
	
	
	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<>();

		userRepo.findAll().forEach(users::add);
		return users;
	}

	
	public Users getUserByUsername(String username) {
		Optional<Users> u = userRepo.findByUsername(username);
		return u.get();
	}

	public void addUser(Users user) {
		userRepo.save(user);
	}
	
	
	public void updatePassword(String username, String password) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users update = u.get();
		update.setPassword(password);
		userRepo.save(update);
			
	}
	
	public void updateEmail(String username, String email) {
		Optional<Users> u = userRepo.findByUsername(username);
		Users update = u.get();
		update.setEmail(email);
		userRepo.save(update);
			
	}
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
