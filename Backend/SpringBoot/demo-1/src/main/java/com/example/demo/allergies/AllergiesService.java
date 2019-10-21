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
	
	public List<Allergies> getUserAllergies(String username){
		List<Allergies> allergies = new ArrayList<Allergies>();
		
		allergiesRepo.findByUserUsername(username).forEach(allergies :: add);
		return allergies;
	}
	
	
	public void addAllergy(Allergies allergy) {
		allergiesRepo.save(allergy);
	}
	
	public void assignUserToAllergy(Allergies allergy, Users user) {
		allergy.setUser(user);
		allergiesRepo.save(allergy);
		
	}
	
	
	
	
}
