package com.example.demo.allergies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.Users;

@RestController
public class AllergiesController {
	
	@Autowired
	private AllergiesService allergies;
	
	@RequestMapping("/allergies")
	public List<Allergies> displayAllAllergies() {
		return allergies.getAllAllergies();
	}
	
	@RequestMapping("/allergies/{username}")
	public List<Allergies> displayUserAllergies(@PathVariable String username){
		return allergies.getUserAllergies(username);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/allergies")
	public void addAllergy(@RequestBody Allergies allergy) {
		allergies.addAllergy(allergy);
	}
	

}
