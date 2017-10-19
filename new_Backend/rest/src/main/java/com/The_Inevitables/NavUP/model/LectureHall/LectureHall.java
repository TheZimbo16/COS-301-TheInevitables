package com.The_Inevitables.NavUP.model.LectureHall;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;

@Entity
@Table(name = "LectureHalls")
public class LectureHall implements SuperEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private int id;
	
	@Column(name = "room_name")
	private String room_name;
	
	@Column(name = "building")
	private String building;
	
	@Column(name = "level")
	private int level;
	
	@Column(name = "coordinates")
	ArrayList<ArrayList<ArrayList<Float>>> coordinates;
	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoom_name() {
		return room_name;
	}
	
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public ArrayList<ArrayList<ArrayList<Float>>> getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(ArrayList<ArrayList<ArrayList<Float>>> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
