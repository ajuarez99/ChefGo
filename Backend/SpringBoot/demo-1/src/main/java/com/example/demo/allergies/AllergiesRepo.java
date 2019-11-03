package com.example.demo.allergies;
/**
 * @author SB_3
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.Users;


public interface AllergiesRepo extends JpaRepository<Allergies, Integer>{
	/**
	 * specfic query to find by username
	 * @param username
	 * @return
	 */
	public List<Allergies> findByUserUsername(String username);
}
