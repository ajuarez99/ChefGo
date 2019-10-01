package com.example.demo.allergies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.Users;

@Service
public class AllergiesService {

	@Autowired
	private AllergiesRepo allergiesRepo;
	
	
	public List<Allergies> getAllAllergies() {
		List<Allergies> allergies = new ArrayList<Allergies>();
		
		allergiesRepo.findAll().forEach(allergies :: add);
		return allergies;
	}
	
	public List<Allergies> getUserAllergies(Users user){
		List<Allergies> allergies = new ArrayList<Allergies>();
		
		allergiesRepo.findByUser(user).forEach(allergies :: add);
		return allergies;
	}
	
	public void assignUserToAllergy(Allergies allergy, Users user) {
		allergy.setUser(user);
		allergiesRepo.save(allergy);
		
	}
	
	
	
	
}
