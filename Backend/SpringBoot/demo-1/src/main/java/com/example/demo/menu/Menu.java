package com.example.demo.menu;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Menu")
public class Menu {
	
	@Id
	@NotNull
	@Column(name = "title")
	@Size(max = 50)
	private String title;
	
	@Column(name= "appetizer")
	@Size(max = 50)
	private String appetizer;
	
	@Column(name= "entree")
	@Size(max = 50)
	private String entree;
	
	@Column(name= "dessert")
	@Size(max = 50)
	private String dessert;
	
	@Column(name = "cost")
	private double cost;
	
	@Column(name = "mDescription")
	@Size(max =200)
	private String mDescription;
	
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chefID")
	private Users chef;
	
    
	public Menu() {
	
	}
	
	public Menu(String title, String appetizer, String entree, String dessert, double cost, String desc, Users chef) {
		
		this.title = title;
		this.appetizer = appetizer == null ? "none" : appetizer;
		this.entree = entree == null ? "none" : entree;
		this.dessert = dessert == null ? "none" : dessert;
		this.cost = cost;
		this.mDescription = desc;
		this.chef = chef;
	
	}
	

	public String getAppetizer() {
		return this.appetizer;
	}
	public void setAppetizer(String appetizer) {
		this.appetizer = appetizer;
	}
	public String getEntree() {
		return this.entree;
	}
	public void setEntree(String entree) {
		this.entree = entree;
	}
	public String getDessert() {
		return this.dessert;
	}
	public void setDessert(String dessert) {
		this.dessert = dessert;
	}
	public double getCost() {
		return this.cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return this.mDescription;
	}
	public void setDescription(String desc) {
		this.mDescription = desc;
	}
	public String getChef() {
		return this.chef.getUsername();
	}
	public void setChef(Users chef) {
		this.chef = chef;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
