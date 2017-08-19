package com.The_Inevitables.NavUP.web.api.dto.coordinates;

import java.util.ArrayList;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class CoordinatesDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private Long coordinatesId;
	private String type;
	private ArrayList<ArrayList<ArrayList<Float>>> coordinates;

	public Long getCoordinatesId() {
		return coordinatesId;
	}

	public void setCoordinatesId(Long coordinatesId) {
		this.coordinatesId = coordinatesId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<ArrayList<ArrayList<Float>>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<ArrayList<ArrayList<Float>>> coordinates) {
		this.coordinates = coordinates;
	}
}
