package com.example.demo.reviews;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    
    @ManyToOne
    @JoinColumn(name = "reviewerID")
    private Users reviewer;
    
    @ManyToOne
    @JoinColumn(name = "revieweeID")
    private Users reviewee;
    
    
    public Reviews() {
    	
    }
    
    public Reviews(Integer rid, double rating, String rDescription, Date rDate, Users reviewer, Users reviewee) {
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
    	return this.reviewer.getUsername();
    }
    public void setReviewer(Users reviewer) {
    	this.reviewer = reviewer;
    }
    public String getReviewee() {
    	return this.reviewee.getUsername();
    }
    public void setReviewee(Users reviewee) {
    	this.reviewee = reviewee;
    }

    
}
