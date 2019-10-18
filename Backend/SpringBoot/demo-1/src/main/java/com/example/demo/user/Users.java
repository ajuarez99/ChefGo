package com.example.demo.user;




import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.orderhistory.OrderHistory;



@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "username")
    @Size(max = 50)
    @NotNull 
    private String username;
 
    @Column(name = "email")
    private String email;
    
    @Column(name = "name")
    @Size(max = 30)
    private String name;
    
    @OneToMany(mappedBy = "customer")
    private List<OrderHistory> orders;
    
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
    
    public Users(String username, String email, String name, String pass, String user, Double rating,
    		Integer type, String address, String state, Integer zip) {
    	this.username = username;
    	this.email = email;
    	this.name= name;
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
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
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
    
    public String getEmail() {
    	return this.email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void addOrder(OrderHistory order) {
    	this.orders.add(order);
    	order.setCustomer(this);
    }
    
    public List<OrderHistory> getOrders(){
    	return this.orders;
    }
}
