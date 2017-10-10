package com.The_Inevitables.NavUP.web.api.transformer.entrance;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.Entrances.Entrance;
import com.The_Inevitables.NavUP.web.api.dto.entrance.EntranceDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;


@Stateless
@LocalBean
public class EntranceTransformer implements ApiTransformer {
	
	public EntranceDTO toDTO(Entrance entrance) {
		EntranceDTO entranceDTO = new EntranceDTO();
		
		entranceDTO.setName(entrance.getName());
		entranceDTO.setDescription(entrance.getDescription());
		entranceDTO.setCoordinates(entrance.getCoordinates());
		
		return entranceDTO;
	}
	
	public Entrance toEntity(EntranceDTO entranceDTO) {
		Entrance entrance = new Entrance();
		
		entrance.setName(entranceDTO.getName());
		entrance.setDescription(entranceDTO.getDescription());
		entrance.setCoordinates(entranceDTO.getCoordinates());
		
		return entrance;
	}
	

}
