package com.The_Inevitables.NavUP.service.building;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.building.BuildingDao;
import com.The_Inevitables.NavUP.model.building.Building;


@Stateless
public class BuildingService {

    @EJB
    BuildingDao buildingDao;
    
    public Building createBuilding(Building entity)
    {
    	return buildingDao.create(entity);
    }
    
    public Building updateBuilding(Building entity)
    {
    	return buildingDao.update(entity);
    }
    
    public void removeBuildingByName(String buildingName)
    {
    	buildingDao.deleteByName(buildingName);
    }
    
    public void removeBuildingByAbrev(String buildingAbrev)
    {
    	buildingDao.deleteByAbrev(buildingAbrev);
    }
    
    public List<Building> getAllBuildings()
    {
    	return (List<Building>) buildingDao.findAllBuildings();
    }
    
    public Building getBuildingByName(String name)
    {
    	return buildingDao.getBuildingByName(name);
    }
    
    public Building getBuildingByAbrev(String name)
    {
    	return buildingDao.getBuildingByAbrev(name);
    }

}
