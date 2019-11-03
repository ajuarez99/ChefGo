package com.example.demo.allergies;
/**
 * @author SB_3
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Allergies")
public class Allergies {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	
	@Column(name = "allergy")
	@Size(max = 40)
	private String allergy;
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private Users user;
	

	/**
	 *  creates empty allergy
	 */
	public Allergies() {
		
	}
	/**
	 * constructer for allergy if given one
	 * @param id
	 * @param allergy
	 * @param user
	 */
	public Allergies(Integer id, String allergy, Users user) {
		this.allergy = allergy;
		this.id = id;
		this.user = user;
	}
	
	/**
	 * sets id of allergy
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * gets id of allergy
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * gets allergy name
	 * @return
	 */
	public String getAllergy() {
		return this.allergy;
	}
	/**
	 * set allergys name
	 * @param allergy
	 */
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	/**
	 * gets user of that allergy
	 * @return
	 */
	public Users getUser() {
		return this.user;
}
	/**
	 * sets user of the allergy
	 * @param user
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
}
