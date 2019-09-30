package com.example.demo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "username")
    @NotNull 
    private String username;
 
    @Column(name = "fname")
    @Size(max = 30)
    private String fName;
    
    @Column(name = "lname")
    @Size(max = 30)
    private String lname;
    
    
    @Column(name = "password")
    @Size(max = 30)
    private String password;
    
    @Column(name = "userType")
    private Integer userType;
    
    @Column(name = "rating")
    private Double rating;
    
    
    @Column(name = "address")
    @Size(max = 40)
    private String address;
    
    @Column(name = "state")
    @Size(max = 20)
    private String state;
    
    @Column (name = "zip")
    private Integer zip;
    
    public Users() {
    	
    }
    
    public Users(String username, String f, String l, String pass, String user, Double rating, Integer type, String address, String state, Integer zip) {
    	this.username = username;
    	this.fName= f;
    	this.lname = l;
    	this.password = pass;
    	this.userType = type;
    	this.rating = rating;
    	this.address = address;
    	this.state = state;
    	this.zip = zip;
    }
    
    public Integer getUserType() {
    	return this.userType;
    }
    public void setUserType(Integer user) {
    	this.userType = user;
    }
    
    public String getfName() {
    	return this.fName;
    }
    public void setfName(String name) {
    	this.fName = name;
    }
    public String getlName() {
    	return this.lname;
    }
    public void setLname(String name) {
    	this.lname= name;
    }
    public String getUsername() {
    	return this.username;
    }
    public void setUsername(String name) {
    	this.username = name;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setPassword(String pass) {
    	this.password = pass;
    }
    public Double getRating() {
    	return this.rating;
    }
    public void setRating(Double r) {
    	this.rating = r;
    }
    
    public String getAddress() {
    	return this.address;
    }
    public void setAddress(String address) {
    	this.address = address;
    }
    public String getState() {
    	return this.state;
    }
    public void setState(String state) {
    	this.state = state;
    }
    public void setZip(Integer zip) {
    	this.zip = zip;
    }
    public Integer getZip() {
    	return this.zip;
    }
}
