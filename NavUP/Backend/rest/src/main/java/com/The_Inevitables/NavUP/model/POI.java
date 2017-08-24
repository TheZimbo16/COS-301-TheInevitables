package com.The_Inevitables.NavUP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POI")
public class POI implements SuperEntity {

	public POI() {
		super();
	}
	
	@Id
	@Column(name = "locationName", unique = true, nullable = false, updatable = false)
	private String locationName;
	
	@Column(name = "Coordinates")
	private String coordinates;
	
	@Column(name = "Type")
	private String type;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	
}
	
//	@Column(name = "username")
//	private String username;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//
//	private Long id;
//
//	
//	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
