package com.example.demo.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SB_3
 */

@RestController
public class ReviewsController {
	
	@Autowired
	private ReviewsService review; 
	/**
	 * returns all reviews
	 * @return
	 */
	@RequestMapping("/reviews")
	public List<Reviews> displayAllReviews() {
		return review.getAllReviews();
	}
	/**
	 * returns a certain review by Id
	 * @param id
	 * @return
	 */
	@RequestMapping("/reviews/{rid}")
	public Reviews displayOrderById(@PathVariable int id) {
		return review.getReviewById(id);
	}
	/**
	 * adds a review to the table
	 * @param newReview
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/reviews")
	public void addReview(@RequestBody Reviews newReview) {
		review.addReviews(newReview);
	}
	/**
	 * updates a review 
	 * @param rid
	 * @param newReview
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/reviews/{rid}")
	public void updateReview(@PathVariable Integer rid, @RequestBody Reviews newReview) {
		review.updateReview(rid, newReview);
	}
	/**
	 * get all reviews by a certain reviewee name
	 * @param username
	 * @return
	 */
	@RequestMapping("/reviewee/{username}")
	public List<Reviews> getReviewsByReviewee(@PathVariable String username) {
		return review.getReviewsByReviewee(username);
	}
	/**
	 * gets all erviews by a certain reviewer name
	 * @param username
	 * @return
	 */
	@RequestMapping("/reviewer/{username}")
	public List<Reviews> getReviewsByReviewer(@PathVariable String username) {
		return review.getReviewsByReviewer(username);
	}
}
