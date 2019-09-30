package com.example.demo.orderhistory;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;




public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Integer> {

	public List<OrderHistory> findByChefName(String chefName);
}

