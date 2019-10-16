package om.example.demo.menu;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Menu")
public class Menu {
	
	@Column(name= "Dish_Name")
	@Size(max = 50)
	private String dishName;
	
	@Column(name = "cost")
	private double cost;
	
	@Column(name = "mDescription")
	@Size(max =200)
	private String mDescription;
	
   // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @JoinColumn(name = "chefID")
	private Users chef;
	
	public Menu() {
	
	}
	public Menu(String dish, double cost, String desc, Users chef) {
		this.dishName = dish;
		this.cost = cost;
		this.mDescription = desc;
		this.chef = chef;
	
	}
	
	public String getDish() {
		return this.dishName;
	}
	public void setDish(String dish) {
		this.dishName = dish;
	}
	public double getCost() {
		return this.cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return this.mDescription;
	}
	public void setDescription(String desc) {
		this.mDescription = desc;
	}
	public String getChef() {
		return this.chef.getUsername();
	}
	public void setChef(Users chef) {
		this.chef = chef;
	}
	
}
