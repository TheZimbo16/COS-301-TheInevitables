package com.The_Inevitables.NavUP.web.api.dto.entrance;

import java.util.ArrayList;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class EntranceDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = -7181107186797303668L;

	private int id;
	private String name;
	private String description;
	private ArrayList<Float> coordinates;
	
	
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

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}
}
