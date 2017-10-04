package com.The_Inevitables.NavUP.web.api.dto.building;

import java.util.ArrayList;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class BuildingDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private int id;
	private String name;
	private String description;
	private ArrayList<ArrayList<ArrayList<Float>>> coordinates;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<ArrayList<ArrayList<Float>>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<ArrayList<ArrayList<Float>>> coordinates) {
		this.coordinates = coordinates;
	}
}
