package com.The_Inevitables.NavUP.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GeoJSON")
public class GeoJSON implements SuperEntity {

	public GeoJSON() {
		super();
	}
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "geoJSONId", unique = true, nullable = false, updatable = false)
	private Long geoJSONId;
	
	@JsonIgnore
	@Column(name = "buildingId")
	private int buildingId;
	
	
	@Column(name = "type")
	private String type;
	
	
	@JsonIgnore
	@Column(name = "coordinatesId")
	private int coordinatesId;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "buildingid", referencedColumnName = "buildingId",insertable = false, updatable = false)
	private Building properties;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordinatesid", referencedColumnName = "coordinatesId",insertable = false, updatable = false)
	private Coordinates geometry;

	public Long getGeoJSONId() {
		return geoJSONId;
	}

	public void setGeoJSONId(Long geoJSONId) {
		this.geoJSONId = geoJSONId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = "Feature";
	}

	public int getCoordinatesId() {
		return coordinatesId;
	}

	public void setCoordinatesId(int coordinatesId) {
		this.coordinatesId = coordinatesId;
	}

	public Building getProperties() {
		return properties;
	}

	public void setProperties(Building properties) {
		this.properties = properties;
	}

	public Coordinates getGeometry() {
		return geometry;
	}

	public void setGeometry(Coordinates geometry) {
		this.geometry = geometry;
	}



	

		
	
	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/



	
	

	

	
}
