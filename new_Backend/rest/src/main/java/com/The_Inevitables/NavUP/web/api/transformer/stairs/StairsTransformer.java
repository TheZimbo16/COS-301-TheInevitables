package com.The_Inevitables.NavUP.web.api.transformer.stairs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.stairs.Stairs;
import com.The_Inevitables.NavUP.web.api.dto.stairs.StairsDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;

@Stateless
@LocalBean
public class StairsTransformer implements ApiTransformer {

	public StairsDTO toDTO(Stairs request) {
		StairsDTO stairsDTO = new StairsDTO();
		
		stairsDTO.setBuilding(request.getBuilding());
		stairsDTO.setCoordinates(request.getCoordinates());
		stairsDTO.setLevel(request.getLevel());
		stairsDTO.setPosition(request.getPosition());
		
		return stairsDTO;
	}
	
	public Stairs toEntity(StairsDTO request) {
		Stairs stairs = new Stairs();
		
		stairs.setBuilding(request.getBuilding());
		stairs.setCoordinates(request.getCoordinates());
		stairs.setLevel(request.getLevel());
		stairs.setPosition(request.getPosition());
		
		return stairs;
	}
}
