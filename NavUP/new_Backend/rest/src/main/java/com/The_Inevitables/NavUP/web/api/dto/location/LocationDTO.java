package com.The_Inevitables.NavUP.web.api.dto.location;


import com.The_Inevitables.NavUP.model.building.Building;
import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class LocationDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = -7088685673499054839L;
	
	private long locationId;
	private String locationName;
	private int floorNumber;
	private int locationTypeId;
	private int buildingId;
	private int roomNumber;
	private float longitude;
	private float latitude;
	private String tagId;
	private Building parent;
	
	public Building getParent() {
		return parent;
	}

	public void setParent(Building parent) {
		this.parent = parent;
	}
	
	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getLocationTypeId() {
		return locationTypeId;
	}

	public void setLocationTypeId(int i) {
		this.locationTypeId = i;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		this.buildingId = i;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
}
