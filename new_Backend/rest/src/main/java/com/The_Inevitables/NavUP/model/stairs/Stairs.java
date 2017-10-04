package com.The_Inevitables.NavUP.model.stairs;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;

@Entity
@Table(name = "Stairs")
public class Stairs implements SuperEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private int id;
	
	@Column(name = "level")
	private int level;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "building")
	private String building;
	
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
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}
}
