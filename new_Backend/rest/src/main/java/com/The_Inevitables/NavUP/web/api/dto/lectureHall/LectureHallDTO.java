package com.The_Inevitables.NavUP.web.api.dto.lectureHall;

import java.util.ArrayList;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class LectureHallDTO extends DTO implements ApiResponse{

	private static final long serialVersionUID = 2854529146076431628L;
	
	private String room_name;
	private String building;
	private int level;
	ArrayList<ArrayList<ArrayList<Float>>> coordinates;
	
	public String getRoom_name() {
		return room_name;
	}
	
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public ArrayList<ArrayList<ArrayList<Float>>> getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(ArrayList<ArrayList<ArrayList<Float>>> coordinates) {
		this.coordinates = coordinates;
	}
	

}
