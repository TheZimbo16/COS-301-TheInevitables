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
@Table(name = "Buildings")
public class Building implements SuperEntity {

	public Building() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buildingId", unique = true, nullable = false, updatable = false)
	private Long buildingId;
	
	@Column(name = "buildingName")
	private String buildingName;
	
	@Column(name = "buildingAbreviation")
	private String buildingAbreviation;
	
	

	/*******************************************************/
	/*				GETTERS AND SETTERS					   */
	/*******************************************************/
	
	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAbreviation() {
		return buildingAbreviation;
	}

	public void setBuildingAbreviation(String buildingAbreviation) {
		this.buildingAbreviation = buildingAbreviation;
	}
	
}
