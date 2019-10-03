package com.example.demo.allergies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.Users;


public interface AllergiesRepo extends JpaRepository<Allergies, Integer>{

	//public List<Allergies> findByUser(Users user);
}
