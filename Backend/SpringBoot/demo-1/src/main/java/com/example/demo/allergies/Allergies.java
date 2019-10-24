package com.example.demo.allergies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Allergies")
public class Allergies {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	
	@Column(name = "allergy")
	@Size(max = 40)
	private String allergy;
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private Users user;
	

	
	public Allergies() {
		
	}
	
	public Allergies(Integer id, String allergy, Users user) {
		this.allergy = allergy;
		this.id = id;
		this.user = user;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public String getAllergy() {
		return this.allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public Users getUser() {
		return this.user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
