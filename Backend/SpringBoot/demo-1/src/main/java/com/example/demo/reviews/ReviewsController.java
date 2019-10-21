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
	public List<Reviews> displayAllReviews() {
		return review.getAllReviews();
	}
	
	@RequestMapping("/reviews/{rid}")
	public Reviews displayOrderById(@PathVariable int id) {
		return review.getReviewById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/reviews/{rid}")
	public void updateReview(@PathVariable Integer rid, @RequestBody Reviews newReview) {
		review.updateReview(rid, newReview);
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
