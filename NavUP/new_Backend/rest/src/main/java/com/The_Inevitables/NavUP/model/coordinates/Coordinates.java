package com.The_Inevitables.NavUP.model.coordinates;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Coordinates")
public class Coordinates implements SuperEntity {

	public Coordinates() {
		super();
	}
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coordinatesId", unique = true, nullable = false, updatable = false)
	private Long coordinatesId;
	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "coordinates")
	private ArrayList<ArrayList<ArrayList<Float>>> coordinates;

	public Long getCoordinatesId() {
		return coordinatesId;
	}

	public void setCoordinatesId(Long coordinatesId) {
		this.coordinatesId = coordinatesId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<ArrayList<ArrayList<Float>>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<ArrayList<ArrayList<Float>>> coordinates) {
		this.coordinates = coordinates;
	}
	
	



	
}
