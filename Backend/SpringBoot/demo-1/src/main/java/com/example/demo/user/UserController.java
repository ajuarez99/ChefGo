package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderhistory.OrderHistory;


@RestController
public class UserController {

	@Autowired
	private UserService users;
	
	@RequestMapping("/users")
	public List<Users> displayAllUsers() {
		return users.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/users")
	public void addUser(@RequestBody Users user) {
		users.addUser(user);
	}
	
//	@RequestMapping("/user/{username}/{password}")
//	public Users validateUser(@PathVariable String username, @PathVariable String password) {
//		Users user = users.getUserByUsername(username);
//		
//		if(user.getPassword().equals(password)) {
//			return user;
//		}
//		else {
//			return null;
//		}
//	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/email")
	public void updateUserEmail(@PathVariable String username, @RequestBody String email) {
		users.updateEmail(username, email);
	}
	
//	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/order")
//	public void updateUserEmail(@PathVariable String username, @RequestBody OrderHistory order) {
//		users.addOrder(username, order);
//	}
	
	
	@RequestMapping( path = "/user/{username}")
	public Users findbyUserName(@PathVariable String username) {
		return users.getUserByUsername(username);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/user/{username}/{fname}/{lname}")
	public void updatefName(@PathVariable String username, @PathVariable String fname, @PathVariable String lname) {
		String name = fname + " " + lname;
		users.updateName(username, name);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/password")
	public void updatePassword(@PathVariable String username, @RequestBody String password) {
		users.updatePassword(username, password);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/users/userType/{username}/{type}")
	public void updateUserType(@PathVariable String username, @PathVariable Integer type) {
		users.updateUserType(username, type);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/address")
	public void updateAddress(@PathVariable String username, @RequestBody String address, @RequestBody String state,
			@RequestBody Integer zip) {
		users.updateAddress(username, address, state, zip);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{username}")
	public void deleteUser(@PathVariable String username) {
		users.deleteUser(username);
	}
}
