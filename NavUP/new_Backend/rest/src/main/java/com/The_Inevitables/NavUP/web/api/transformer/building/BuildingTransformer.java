package com.The_Inevitables.NavUP.web.api.transformer.building;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.building.Building;
import com.The_Inevitables.NavUP.web.api.dto.building.BuildingDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;

@Stateless
@LocalBean
public class BuildingTransformer implements ApiTransformer {

	public BuildingDTO toDTO(Building build){
		BuildingDTO buildingDTO = new BuildingDTO();
	
		buildingDTO.setName(build.getName());
		buildingDTO.setDescriptio(build.getDescription());
		
		return buildingDTO;
	}
	
	public Building toEntity(BuildingDTO request) {
		Building building = new Building();
		
		building.setName(request.getName());
		building.setDescription(request.getDescription());
		
		return building;
	}
	
	/*public Building getEntity(BuildingDTO request) {
		
	}*/

}
