package com.example.demo.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.UserRepo;
import com.example.demo.user.Users;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepo menuRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public List<Menu> getAllMenus() {
		List<Menu> menus = new ArrayList<>();

		menuRepo.findAll().forEach(menus::add);
		return menus;
	}
	
	public List<Menu> getAllMenusByChef(String username) {
		List<Menu> menus = new ArrayList<>();
		
		menus = menuRepo.findByChefUsername(username);
		return menus;
	}
	
	public Menu getMenuByTitle(String title) {
		return menuRepo.findByTitle(title);
	}
	
	public void addMenu(Menu menu) {
		menuRepo.save(menu);
	}
	
	public void updateMenu(String title, Menu menu) {
		Menu update = menuRepo.findByTitle(title);
		update = menu;
		menuRepo.save(update);
	}
	
	public void deleteMenu(String title) {
		Menu toDelete = menuRepo.findByTitle(title);
		menuRepo.delete(toDelete);
	}
	
	
	
	
	
	

}
