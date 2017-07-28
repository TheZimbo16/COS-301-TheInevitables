package com.The_Inevitables.NavUP.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Features")
public class Feature implements SuperEntity {
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "geoJSONiD",referencedColumnName="geoJSONiD")
    private GeoJSON geoJSON;
	
	
	
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
	

	@OneToOne(cascade = CascadeType.ALL)
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

	public GeoJSON getGeoJSON() {
		return geoJSON;
	}

	public void setGeoJSON(GeoJSON geoJSON) {
		this.geoJSON = geoJSON;
	}




	

	
}
