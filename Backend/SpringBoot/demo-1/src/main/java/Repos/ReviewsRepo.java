package Repos;

import org.springframework.data.repository.CrudRepository;

import Entities.Reviews;

public interface ReviewsRepo extends CrudRepository<Reviews, Integer> {

}
