package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entities.Users;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}
	
	public Users getUserbyId(int id) {
		Optional<Users> u = userRepo.findById(id);
		return u.get();
	}
	public void addUser(Users user) {
		userRepo.save(user);
	}
	
	public void updateUsername(int id, String username) {
		Optional<Users> u = userRepo.findById(id);
		Users update = u.get();
		update.setUsername(username);
		userRepo.save(update);
			
	}

	public void deleteUser(int id) {
		userRepo.deleteById(id);
		
	}
	
	
}
