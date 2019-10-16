package com.example.demo.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.orderhistory.OrderHistory;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepo menuRepo;
	
	public List<Menu> getAllMenus() {
		List<Menu> menus = new ArrayList<>();

		menuRepo.findAll().forEach(menus::add);
		return menus;
	}

}
