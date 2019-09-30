package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
	
	@RequestMapping("/users/{username}")
	public Users displayUser(@PathVariable String username) {
		return users.getUserByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{id}/username")
	public void updateUsername(@PathVariable int id, @RequestBody String username) {
		users.updateUsername(id, username);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{id}/password")
	public void updatePassword(@PathVariable int id, @RequestBody String password) {
		users.updatePassword(id, password);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		users.deleteUser(id);
	}
}
