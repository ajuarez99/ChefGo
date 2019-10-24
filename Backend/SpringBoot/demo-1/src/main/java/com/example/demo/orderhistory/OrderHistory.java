package com.example.demo.orderhistory;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.reviews.Reviews;
import com.example.demo.user.Users;

@Entity
@Table(name = "Order_History")
public class OrderHistory {


	
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewID")
    private Reviews review;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private Users customer;

    @Column(name = "price")
    private Double price;


    @Column(name = "orderDate")
    private Date orderDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid")
    @NotNull 
    private Integer oid;
    
    @Column(name= "dishName")
    @Size(max = 50)
    private String dishName;
    
    @Column(name = "active")
    private Integer isActive;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chefID")
    private Users chef;
    
    
    
    
    public OrderHistory() {
    }
    


    public OrderHistory(Integer oid, Date orderDate, Double price, Users chef, String dishName, Users customer) {
    	this.price = price;
    	this.oid = oid;
    	this.orderDate = orderDate;
    	this.dishName = dishName;
    	this.chef = chef;
    	this.customer = customer;
    	this.isActive = 1;
    	this.review = new Reviews();
    }
    

    public Reviews getReview() {
    	if(this.review != null) return this.review;
    	else return null;
    }
    public void setReview(Reviews review) {
    	this.review = review;
    }
   public Integer getoid() {
    	return this.oid;
    }
    public void setOid(Integer oid) {
    	this.oid = oid;
    }
    
    public Double getPrice() {
    	return this.price;
    }
    public void setPrice(Double price) {
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
    public Users getChef() {
    	if(this.chef != null) return this.chef;
    	else return null;
    }
    public void setChef(Users chef) {
    	this.chef = chef;
    }

	public Users getCustomer() {
    	if(this.customer != null) return this.customer;
    	else return null;

	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}
	
	public Integer getIsActive() {
		return this.isActive;
	}

	public void setActive(Integer isActive) {
		this.isActive = isActive;
	}


    
    
}
