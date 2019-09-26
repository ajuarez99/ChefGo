package Entities;

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
@Table(name = "Allergies")
public class Allergies {

	@Column(name = "users_name")
	@Id
	@Size(max = 30)
	private String userName;
	
	@Column(name = "aType")
	@Size(max = 40)
	private String aType;
	
	public Allergies() {
		
	}
	
	public Allergies(String name, String type) {
		this.userName = name;
		this.aType = type;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public String getType() {
		return this.aType;
	}
	public void setType(String type) {
		this.aType = type;
	}
}
