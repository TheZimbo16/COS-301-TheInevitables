package com.The_Inevitables.NavUP.model.Entrances;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;

@Entity
@Table(name = "Entrances")
public class Entrance implements SuperEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "coordinates")
	private ArrayList<Float> coordinates;
	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}
	
	
	
}
