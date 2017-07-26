package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.Building;
import com.The_Inevitables.NavUP.web.api.dto.building.BuildingDTO;

@Stateless
@LocalBean
public class BuildingTransformer implements ApiTransformer {

	public BuildingDTO toDTO(Building build){
		BuildingDTO buildingDTO = new BuildingDTO();
		
		buildingDTO.setBuildingId(build.getBuildingId());
		buildingDTO.setBuildingName(build.getBuildingName());
		buildingDTO.setBuildingAbreviation(build.getBuildingAbreviation());
		
		return buildingDTO;
	}
	
	public Building toEntity(BuildingDTO request) {
		Building building = new Building();
		
		building.setBuildingId(request.getBuildingId());
		building.setBuildingName(request.getBuildingName());
		building.setBuildingAbreviation(request.getBuildingAbreviation());
		
		return building;
	}
	
	/*public Building getEntity(BuildingDTO request) {
		
	}*/

}
