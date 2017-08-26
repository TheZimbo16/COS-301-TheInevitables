package com.The_Inevitables.NavUP.model.GeoJSON;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;
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
	private String type = "FeatureCollection";
	
	@Column(name = "crs")
	private CRS crs = new CRS();
	
	@Column(name = "features")
	private Collection<Feature> features;
	
	public int getGeoJSONiD() {
		return geoJSONiD;
	}

	public void setGeoJSONiD(int geoJSONiD) {
		this.geoJSONiD = geoJSONiD;
	}
	
	public Collection<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Collection<Feature> features) {
		this.features = features;
	}
	
	/*@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "geoJSONiD")
	private int geoJSONiD;
	
	@Column(name = "type")
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "crs", referencedColumnName = "crsId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private CRS crs;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "features", referencedColumnName = "featureId")
	private Collection<Feature> features = new ArrayList<Feature>();

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


*/


}
