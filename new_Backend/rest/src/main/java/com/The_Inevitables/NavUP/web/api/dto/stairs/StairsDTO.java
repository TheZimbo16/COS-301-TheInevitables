package com.The_Inevitables.NavUP.web.api.dto.stairs;

import java.util.ArrayList;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class StairsDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = -5202696237579020080L;
	
	private int level;
	private String position;
	private String building;
	private ArrayList<Float> coordinates;
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}

}
