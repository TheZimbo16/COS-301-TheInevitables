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
public class GeoJSON implements SuperEntity{
	
	public GeoJSON() {
		super();
	}
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "geoJSONiD")
	private int geoJSONiD;
	
	@Column(name = "type")
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "crs", referencedColumnName = "crsId",insertable = false, updatable = false)
	private CRS crs;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "features", referencedColumnName = "featureId",insertable = false, updatable = false)
	private Feature features;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CRS getCrs() {
		return crs;
	}

	public void setCrs(CRS crs) {
		this.crs = crs;
	}

	public int getGeoJSONiD() {
		return geoJSONiD;
	}

	public void setGeoJSONiD(int geoJSONiD) {
		this.geoJSONiD = geoJSONiD;
	}

	public Feature getFeatures() {
		return features;
	}

	public void setFeatures(Feature features) {
		this.features = features;
	}

}
