package com.The_Inevitables.NavUP.model.navigation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Navigation")
public class Navigation implements SuperEntity {

	@JsonIgnore
	@Id
	@Column(name = "locationName", unique = true, nullable = false, updatable = false)
	private String locationName;
	
	@Column(name = "locationCoordinates")
    private String locationCoordinates;

	public Navigation() {
		super();
	}
	
	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCoordinates() {
		return locationCoordinates;
	}

	public void setLocationCoordinates(String locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}
}