package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.Location;
import com.The_Inevitables.NavUP.web.api.dto.location.LocationDTO;


@Stateless
@LocalBean
public class LocationTransformer implements ApiTransformer{

	public LocationDTO toDTO(Location location)
	{
		LocationDTO locationDTO = new LocationDTO();
		
		locationDTO.setLocationId(location.getLocationId());
		locationDTO.setLocationName(location.getLocationName());
		locationDTO.setBuildingId(location.getBuildingId());
		locationDTO.setFloorNumber(location.getFloorNumber());
		locationDTO.setLocationTypeId(location.getLocationTypeId());
		locationDTO.setRoomNumber(location.getRoomNumber());
		locationDTO.setLatitude(location.getLatitude());
		locationDTO.setLongitude(location.getLongitude());
		locationDTO.setTagId(location.getTagId());
		
		return locationDTO;
	}
	
	public Location toEntity(LocationDTO request)
	{
		Location location = new Location();
		
		location.setLocationId(request.getLocationId());
		location.setLocationName(request.getLocationName());
		location.setBuildingId(request.getBuildingId());
		location.setFloorNumber(request.getFloorNumber());
		location.setLocationTypeId(request.getLocationTypeId());
		location.setRoomNumber(request.getRoomNumber());
		location.setLatitude(request.getLatitude());
		location.setLongitude(request.getLongitude());
		location.setTagId(request.getTagId());
		
		return location;
	}

}
