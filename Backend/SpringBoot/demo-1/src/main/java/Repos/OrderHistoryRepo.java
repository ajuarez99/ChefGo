package Repos;

import org.springframework.data.repository.CrudRepository;

import Entities.OrderHistory;


public interface OrderHistoryRepo extends CrudRepository<OrderHistory, Integer>{

}
