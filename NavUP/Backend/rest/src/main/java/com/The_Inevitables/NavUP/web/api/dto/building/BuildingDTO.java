package com.The_Inevitables.NavUP.web.api.dto.building;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class BuildingDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private Long buildingId;
	private String buildingName;
	private String buildingAbreviation;
	
	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAbreviation() {
		return buildingAbreviation;
	}

	public void setBuildingAbreviation(String buildingAbreviation) {
		this.buildingAbreviation = buildingAbreviation;
	}

}
