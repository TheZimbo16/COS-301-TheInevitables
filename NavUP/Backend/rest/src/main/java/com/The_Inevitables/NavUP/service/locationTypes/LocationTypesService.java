package com.The_Inevitables.NavUP.service.locationTypes;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.LocationTypesDao;
import com.The_Inevitables.NavUP.model.LocationType;


@Stateless
public class LocationTypesService {

	@EJB
	LocationTypesDao locationTypesDao;
	
   public LocationType createLocationType(LocationType entity)
   {
	   return locationTypesDao.create(entity);
   }
   
   public LocationType updateLocationType(LocationType entity)
   {
	   return locationTypesDao.update(entity);
   }
   
   public void removeLocationType(String locationName)
   {
	   locationTypesDao.deleteLocationType(locationName);
   }
   
   public List<LocationType> getAllLocationTypes()
   {
	   return locationTypesDao.getAllLocationTypes();
   }

}
