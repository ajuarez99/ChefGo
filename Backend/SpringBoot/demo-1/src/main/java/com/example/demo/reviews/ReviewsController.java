package com.example.demo.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ReviewsController {
	
	@Autowired
	private ReviewsService review; 
	
	@RequestMapping("/reviews")
	public List<Reviews> displayAllOrders() {
		return review.getAllReviews();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/reviews")
	public void addOrder(@RequestBody Reviews order) {
		review.addReviews(order);
	}
	
	@RequestMapping("/reviews/{id}")
	public Reviews displayOrderById(@PathVariable int id) {
		return review.getReviewById(id);
	}

	@RequestMapping("/reviewee/{username}")
	public List<Reviews> getReviewsByReviewee(@PathVariable String username) {
		return review.getReviewsByReviewee(username);
	}
	@RequestMapping("/reviewer/{username}")
	public List<Reviews> getReviewsByReviewer(@PathVariable String username) {
		return review.getReviewsByReviewer(username);
	}
}
