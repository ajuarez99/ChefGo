package com.example.demo.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MenuRepo extends JpaRepository<Menu, Integer>{
	
	public List<Menu> findByChef(String username);
	public Menu findByTitle(String title);
}
