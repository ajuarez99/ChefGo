package com.example.demo.reviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryRepo;
import com.example.demo.user.Users;

@RestController
public class ReviewsService {
	

	
	@Autowired
	private ReviewsRepo reviewsRepo;
	
	public List<Reviews> getAllReviews() {
		List<Reviews> orders = new ArrayList<>();

		reviewsRepo.findAll().forEach(orders::add);
		return orders;
	}
	
	public List<Reviews> getReviewsByReviewee(String username) {
		List<Reviews> u = reviewsRepo.findByReviewee(username);
		return u;
	}
	
	public List<Reviews> getReviewsByReviewer(String username) {
		List<Reviews> u = reviewsRepo.findByReviewer(username);
		return u;
	}
	
	
	public Reviews getReviewById(int id) {
		Optional<Reviews> u = reviewsRepo.findById(id);
		
		return u.get();
	}

	public void addReviews(Reviews order) {
		reviewsRepo.save(order);
	}
	public void updateReview(int id, String review) {
	Optional<Reviews> u = reviewsRepo.findById(id);
	Reviews update = u.get();
	update.setDescription(review);
	reviewsRepo.save(update);
	}


	public void deleteReview(int id) {
		reviewsRepo.deleteById(id);		
	}

}
