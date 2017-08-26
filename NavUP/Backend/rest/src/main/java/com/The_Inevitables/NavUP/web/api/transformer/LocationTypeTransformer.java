package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.LocationType;
import com.The_Inevitables.NavUP.web.api.dto.locationTypes.LocationTypeDTO;;


@Stateless
@LocalBean
public class LocationTypeTransformer implements ApiTransformer {

	public LocationTypeDTO toDTO(LocationType locationtype)
	{
		LocationTypeDTO locationTypeDTO = new LocationTypeDTO();
		
		locationTypeDTO.setLocationId(locationtype.getLocationId());
		locationTypeDTO.setLocationDescription(locationtype.getLocationDescription());
		
		return locationTypeDTO;
	}
	
	public LocationType toEntity(LocationTypeDTO request) 
	{
		LocationType locationType = new LocationType();
		
		locationType.setLocationId(request.getLocationId());
		locationType.setLocationDescription(request.getLocationDescription());
		
		return locationType;
	}

}
