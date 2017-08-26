package com.The_Inevitables.NavUP.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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