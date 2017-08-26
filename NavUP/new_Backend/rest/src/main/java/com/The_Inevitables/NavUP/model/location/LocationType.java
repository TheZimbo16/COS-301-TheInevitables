package com.The_Inevitables.NavUP.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;

@Entity
@Table(name = "LocationTypes")
public class LocationType implements SuperEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locationId", unique = true, nullable = false, updatable = false)
	private long locationId;
	
	@Column(name = "locationDescription")
	private String locationDescription;
	
	public LocationType() {
		super();
	}

	
	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
}
