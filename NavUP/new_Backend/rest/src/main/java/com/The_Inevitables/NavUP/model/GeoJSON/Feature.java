package com.The_Inevitables.NavUP.model.GeoJSON;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.building.Building;
import com.The_Inevitables.NavUP.model.coordinates.Coordinates;
import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Features")
public class Feature implements SuperEntity {

	public Feature() {
		super();
	}
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "featureId", unique = true, nullable = false, updatable = false)
	private Long featureId;
	
	@Column(name = "type")
	private String type;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "buildingId", referencedColumnName = "buildingId")
	private Building properties;
	
	@OneToOne(cascade = CascadeType.ALL)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "coordinatesId", referencedColumnName = "coordinatesId")
	private Coordinates geometry;

	public Long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
