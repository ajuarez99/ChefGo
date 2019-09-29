package com.example.demo.user;

import org.springframework.data.repository.CrudRepository;

import Entities.Users;

public interface UserRepo extends CrudRepository<Users, Integer> {
	
	

}
