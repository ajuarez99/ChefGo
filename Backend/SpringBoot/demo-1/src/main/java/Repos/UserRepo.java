package Repos;

import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.repository.CrudRepository;

import Entities.Users;

public interface UserRepo extends CrudRepository<Users, Integer>, Repository {
	
	

}
