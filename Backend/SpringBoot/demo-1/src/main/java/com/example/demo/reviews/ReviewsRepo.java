package com.example.demo.reviews;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author SB_3
 */



public interface ReviewsRepo extends CrudRepository<Reviews, Integer> {

/**
 * queries table by username of reviewee
 * @param username
 * @return
 */
	public List<Reviews> findByRevieweeUsername(String username);
	/**
	 * queries table by username of reviewer
	 * @param username
	 * @return
	 */
	public List<Reviews> findByReviewerUsername(String username);
}
