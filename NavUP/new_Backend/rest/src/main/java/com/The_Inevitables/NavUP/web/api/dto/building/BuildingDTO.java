package com.The_Inevitables.NavUP.web.api.dto.building;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class BuildingDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private Long buildingId;
	private String name;
	private String description;
	

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
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

	public void setDescriptio(String description) {
		this.description = description;
	}
}
