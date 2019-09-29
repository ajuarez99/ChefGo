package com.example.demo.orderhistory;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderHistory {

	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    @NotNull 
    private Integer rid;
    
    @Column(name = "price")
    private double price;
 
    @Column(name = "orderDate")
    private Date orderDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid")
    @NotNull 
    private Integer oid;
    
    @Column(name= "DishName")
    @Size(max = 50)
    private String dishName;
    
    @Column(name= "chefName")
    @Size(max = 60)
    private String chefName;
    
    @Column(name= "customerName")
    @Size(max = 60)
    private String customerName;
    
    public OrderHistory() {
    }
    
    public OrderHistory(Integer rid, Integer oid, Date date, double price, String chef, String dish, String customer) {
    	this.rid = rid;
    	this.oid = oid;
    	this.orderDate = date;
    	this.dishName = dish;
    	this.chefName = chef;
    	this.customerName = customer;
    }
    public Integer getrid() {
    	return this.rid;
    }
    public void setRid(Integer rid) {
    	this.rid = rid;
    }
    public Integer getoid() {
    	return this.oid;
    }
    public void setOid(Integer oid) {
    	this.oid = oid;
    }
    
    public double getPrice() {
    	return this.price;
    }
    public void setPrice(double price) {
    	this.price = price;
    }
    public String getDish() {
    	return this.dishName;
    }
    public void setDish(String dish) {
    	this.dishName= dish;
    }
    public Date getDate() {
    	return this.orderDate;
    }
    public void setDate(Date date) {
    	this.orderDate = date;
    }
    public String getChef() {
    	return this.chefName;
    }
    public void setChef(String chef) {
    	this.chefName = chef;
    }
    public String getCustomer() {
    	return this.customerName;
    }
    public void setCustomer(String r) {
    	this.customerName = r;
    }

    
    
}
