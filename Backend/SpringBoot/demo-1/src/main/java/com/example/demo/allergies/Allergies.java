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
	
	
	@Column(name = "aType")
	@Size(max = 40)
	private String aType;
	
	@ManyToOne
	private Users user;
	
	public Allergies(String aType, Integer id, Users user) {
		this.aType = aType;
		this.id = id;
		this.user= user;
	}
	
	public Allergies() {

	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public String getType() {
		return this.aType;
	}
	public void setType(String aType) {
		this.aType = aType;
	}
	
}
