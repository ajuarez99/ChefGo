package com.example.demo.reviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryRepo;
import com.example.demo.user.Users;

@Service
public class ReviewsService {
	

	
	@Autowired
	private ReviewsRepo reviewsRepo;
	
	public List<Reviews> getAllReviews() {
		List<Reviews> orders = new ArrayList<>();

		reviewsRepo.findAll().forEach(orders::add);
		return orders;
	}
	
	public List<Reviews> getReviewsByReviewee(String username) {
		List<Reviews> reviews = reviewsRepo.findByReviewee(username);
		return reviews;
	}
	
	public List<Reviews> getReviewsByReviewer(String username) {
		List<Reviews> reviews = reviewsRepo.findByReviewer(username);
		return reviews;
	}
	
	public Reviews getReviewById(int id) {
		Optional<Reviews> review = reviewsRepo.findById(id);
		
		return review.get();
	}

	public void addReviews(Reviews order) {
		reviewsRepo.save(order);
	}
	
	public void updateReview(int id, Reviews newReview) {
	Optional<Reviews> u = reviewsRepo.findById(id);
	Reviews update = u.get();
	update = newReview;
	reviewsRepo.save(update);
	}


	public void deleteReview(int id) {
		reviewsRepo.deleteById(id);		
	}

}
