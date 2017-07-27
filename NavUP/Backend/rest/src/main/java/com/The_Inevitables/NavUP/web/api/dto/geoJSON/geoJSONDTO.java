package com.The_Inevitables.NavUP.web.api.dto.geoJSON;

import com.The_Inevitables.NavUP.model.Building;
import com.The_Inevitables.NavUP.model.Coordinates;
import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class geoJSONDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private int buildingId;
	private int coordinatesId;
	private String type;
	private Long geoJSONId;
	private Building properties;
	private Coordinates geometry;
	
	public Long getGeoJSONId() {
		return geoJSONId;
	}

	public void setGeoJSONId(Long geoJSONId) {
		this.geoJSONId = geoJSONId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCoordinatesId() {
		return coordinatesId;
	}

	public void setCoordinatesId(int coordinatesId) {
		this.coordinatesId = coordinatesId;
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
