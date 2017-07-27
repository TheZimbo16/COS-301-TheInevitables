package com.The_Inevitables.NavUP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Buildings")
public class Building implements SuperEntity {

	public Building() {
		super();
	}
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buildingId", unique = true, nullable = false, updatable = false)
	private Long buildingId;
	
	@Column(name = "Name")
	private String Name;
	
	@Column(name = "Descriptio")
	private String Descriptio;
	
	

	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescriptio() {
		return Descriptio;
	}

	public void setDescriptio(String descriptio) {
		Descriptio = descriptio;
	}

	
}
