package com.The_Inevitables.NavUP.service.location;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.location.LocationDao;
import com.The_Inevitables.NavUP.model.location.Location;

@Stateless
public class LocationService {

	@EJB
	LocationDao locationDao;
	
	public Location createLocation(Location entity)
	{
		 return locationDao.create(entity);
	}
	
	public Location updateLocation(Location entity)
	{
		return locationDao.update(entity);
	}
	
	public void removeLocation(String locationName)
	{
		locationDao.deleteLocation(locationName);
	}
	
	public List<Location> getAllLocations()
	{
		return locationDao.getAllLocations();
	}
}
