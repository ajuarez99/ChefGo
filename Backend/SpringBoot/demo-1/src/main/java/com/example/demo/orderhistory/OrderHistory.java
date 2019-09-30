package com.example.demo.orderhistory;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.reviews.Reviews;

@Entity
@Table(name = "Order_History")
public class OrderHistory {


	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Reviews review;
//    
	@Id
	@Column(name = "id")
	private Integer id;
	
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
    
    @Column(name= "chefName")
    @Size(max = 60)
    private String chefName;
    
    @Column(name= "customerName")
    @Size(max = 60)
    private String customerName;
    
    public OrderHistory() {
    }
    


    public OrderHistory(/*Integer rid,*/ Integer oid,Date orderDate, Double price, String chefName, String dishName, String customerName) {
    	//this.rid = rid;
    	this.price = price;
    	this.oid = oid;
    	this.orderDate = orderDate;
    	this.dishName = dishName;
    	this.chefName = chefName;
    	this.customerName = customerName;

    }
    public Reviews getReview() {
    	return this.review;
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
