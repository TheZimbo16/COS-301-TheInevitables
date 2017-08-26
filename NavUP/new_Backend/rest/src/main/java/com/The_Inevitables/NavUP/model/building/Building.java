package com.The_Inevitables.NavUP.model.building;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.GeoJSON.GeoJSON;
import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;

@Entity
@Table(name = "Buildings")
public class Building extends GeoJSON implements SuperEntity {

	public Building() {
		super();
	}
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	

	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descriptio) {
		this.description = descriptio;
	}

	
}
