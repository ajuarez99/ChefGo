package com.example.demo.orderhistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;




public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Integer> {

	public List<OrderHistory> findByChefName(String chefName);
	public Optional<OrderHistory> findByOid(int oid);
}

