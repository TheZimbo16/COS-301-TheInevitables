package com.The_Inevitables.NavUP.web.api.dto.building;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class BuildingDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 4123116456737543994L;
	
	private Long buildingId;
	private String Name;
	private String Descriptio;
	

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescriptio() {
		return Descriptio;
	}

	public void setDescriptio(String descriptio) {
		Descriptio = descriptio;
	}
}
