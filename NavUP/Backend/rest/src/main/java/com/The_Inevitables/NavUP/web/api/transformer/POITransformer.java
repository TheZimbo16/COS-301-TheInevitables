package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.POI;
import com.The_Inevitables.NavUP.web.api.dto.poi.POIDTO;

@LocalBean
@Stateless
public class POITransformer implements ApiTransformer{

	public POIDTO toDTO(POI user){
		POIDTO userDTO = new POIDTO();

		userDTO.setLocationName(user.getLocationName());
		userDTO.setCoordinates(user.getCoordinates());
		userDTO.setType(user.getType());

		
		return userDTO;
	}

	public POI toEntity(POIDTO request) {
		POI user = new POI();
		
		user.setLocationName(request.getLocationName());
		user.setCoordinates(request.getCoordinates());
		user.setType(request.getType());
		return user;
	}
	
	public POI getEntity(POIDTO request) {
		POI user = new POI();
		
		user.getCoordinates();
		user.getLocationName();
		user.getType();
		
		
		return user;
	}
}
