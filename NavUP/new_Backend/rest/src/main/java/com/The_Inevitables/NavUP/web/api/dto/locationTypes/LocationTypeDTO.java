package com.The_Inevitables.NavUP.web.api.dto.locationTypes;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class LocationTypeDTO extends DTO implements ApiResponse{

	private static final long serialVersionUID = -6481118649565834031L;
	
	private long locationId;
	private String locationDescription;
	
	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

}
