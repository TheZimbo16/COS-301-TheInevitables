package com.The_Inevitables.NavUP.web.api.dto.navigation;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class NavigationDTO extends DTO implements ApiResponse{

	
	private static final long serialVersionUID = 1938248728652936986L;
	
	private String locationName;
	private String locationCoordinates;


	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationCoordinates() {
		return locationCoordinates;
	}
	public void setLocationCoordinates(String locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}


	
	
	





}
