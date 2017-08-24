package com.The_Inevitables.NavUP.web.api.dto.poi;

import javax.persistence.Column;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class POIDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 1938248728652936986L;
	
	private String locationName;
	private String coordinates;
	private String type;
	
	public String getLocationName() {
		return locationName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
}