package com.example.demo.reviews;

import java.util.List;

import org.springframework.data.repository.CrudRepository;





public interface ReviewsRepo extends CrudRepository<Reviews, Integer> {


	public List<Reviews> findByReviewee(String reviewee);

	public List<Reviews> findByReviewer(String reviewer);
}
