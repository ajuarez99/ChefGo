package om.example.demo.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

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
	
	@Column(name = "chefname")
	@Id
	private String chefName;
	
	public Menu() {
	
	}
	public Menu(String dish, double cost, String desc, String name) {
		this.dishName = dish;
		this.cost = cost;
		this.mDescription = desc;
		this.chefName = name;
	
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
		return this.chefName;
	}
	public void setChef(String chef) {
		this.chefName = chef;
	}
	
}
