package com.The_Inevitables.NavUP.web.api.dto.feature;

import com.The_Inevitables.NavUP.model.Building;
import com.The_Inevitables.NavUP.model.Coordinates;
import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class FeatureDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private String type;
	private Long featureId;
	private Building properties;
	private Coordinates geometry;
	
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

	public Long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

}
