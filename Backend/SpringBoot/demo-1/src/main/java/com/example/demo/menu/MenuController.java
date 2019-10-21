package com.example.demo.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@RequestMapping("/menus")
	public List<Menu> getAllMenus(){
		List<Menu> menus = menuService.getAllMenus();
		return menus;
	}
	
	@RequestMapping("/menus//chef/{username}")
	public List<Menu> getAllMenusByChef(@PathVariable String username){
		List<Menu> menus = menuService.getAllMenusByChef(username);
		return menus;		
	}
	
	@RequestMapping("/menus/title/{title}")
	public Menu getMenuByTitle(@PathVariable String title) {
		return menuService.getMenuByTitle(title);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/menus")
	public void addMenu(@RequestBody Menu menu) {
		menuService.addMenu(menu);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/menus/{title}")
	public void updateMenu(@RequestBody Menu menu, @PathVariable String title) {
		menuService.updateMenu(title, menu);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/menus/{title}")
	public void deleteMenu(@PathVariable String title) {
		menuService.deleteMenu(title);
	}
}
