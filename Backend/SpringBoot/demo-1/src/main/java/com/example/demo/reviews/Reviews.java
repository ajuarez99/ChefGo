package com.example.demo.reviews;



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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.user.Users;

@Entity
@Table(name = "Reviews")
public class Reviews {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    @NotNull 
    private Integer rid;
 
    @Column(name = "rating")
    @Digits(fraction = 1, integer = 1)
    private double rating;
    
    @Column(name = "rDescription")
    @Size(max = 300)
    private String rDescription;
    
    
    @Column(name = "reviewDate")
    private Date reviewDate;
    
    @Column(name = "reviewer")
    @Size(max = 30)
    private String reviewer;
    
    @Column(name = "reviewee")
    @Size(max = 30)
    private String reviewee;
    
    
    public Reviews() {
    	
    }
    
    public Reviews(Integer rid, double rating, String rDescription,Date rDate,String reviewer, String reviewee) {
    	this.rid =rid;
    	this.rating = rating;
    	this.rDescription = rDescription;
    	this.reviewDate = rDate;
    	this.reviewer= reviewer;
    	this.reviewee = reviewee;
    	
    }
    
    public Integer getrid() {
    	return this.rid;
    }
    public void setRid(Integer rid) {
    	this.rid = rid;
    }
    
    public double getRating() {
    	return this.rating;
    }
    public void setRating(double rating) {
    	this.rating = rating;
    }
    public String getDescription() {
    	return this.rDescription;
    }
    public void setDescription(String Description) {
    	this.rDescription= Description;
    }
    public Date getDate() {
    	return this.reviewDate;
    }
    public void setDate(Date date) {
    	this.reviewDate = date;
    }
    public String getReviewer() {
    	return this.reviewer;
    }
    public void setReviewer(String reviewer) {
    	this.reviewer = reviewer;
    }
    public String getReviewee() {
    	return this.reviewee;
    }
    public void setReviewee(String r) {
    	this.reviewee = r;
    }

    
}
