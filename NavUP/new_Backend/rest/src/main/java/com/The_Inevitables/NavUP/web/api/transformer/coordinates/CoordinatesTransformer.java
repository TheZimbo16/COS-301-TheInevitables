package com.The_Inevitables.NavUP.web.api.transformer.coordinates;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.coordinates.Coordinates;
import com.The_Inevitables.NavUP.web.api.dto.coordinates.CoordinatesDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;

@Stateless
@LocalBean
public class CoordinatesTransformer implements ApiTransformer {

	public CoordinatesDTO toDTO(Coordinates coords){
		CoordinatesDTO coordsDTO = new CoordinatesDTO();
		
		coordsDTO.setCoordinates(coords.getCoordinates());
		coordsDTO.setType(coords.getType());
		coordsDTO.setCoordinatesId(coords.getCoordinatesId());
		
		return coordsDTO;
	}
	
	public Coordinates toEntity(CoordinatesDTO request) {
		Coordinates coords = new Coordinates();
		coords.setCoordinatesId(request.getCoordinatesId());
		coords.setType(request.getType());
		coords.setCoordinates(request.getCoordinates());
		return coords;
	}
	

}
