package com.The_Inevitables.NavUP.service.coordinates;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.CoordinatesDao;
import com.The_Inevitables.NavUP.model.Coordinates;


@Stateless
public class CoordinatesService {

    @EJB
    CoordinatesDao coordinatesDao;
    
    public Coordinates createCoordinates(Coordinates entity)
    {
    	return coordinatesDao.create(entity);
    }
    
    public Coordinates updateBuilding(Coordinates entity)
    {
    	return coordinatesDao.update(entity);
    }
    
    public void removeBuildingByName(String buildingName)
    {
    	coordinatesDao.deleteByName(buildingName);
    }
    
    public void removeBuildingByAbrev(String buildingAbrev)
    {
    	coordinatesDao.deleteByAbrev(buildingAbrev);
    }
    
    public List<Coordinates> getAllCoords()
    {
    	return coordinatesDao.findAllCoordinates();
    }
    


}
