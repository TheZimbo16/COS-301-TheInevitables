package com.The_Inevitables.NavUP.service.building;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.building.BuildingDao;
import com.The_Inevitables.NavUP.model.GeoJSONFormatter.BuildingGeoJson.BuildingGeoJson;
import com.The_Inevitables.NavUP.model.building.Building;
import com.google.gson.JsonObject;


@Stateless
public class BuildingService {

    @EJB
    BuildingDao buildingDao;
    
    BuildingGeoJson buildingGeoJSON;
    
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
    
    
    public List<Building> getAllBuildings()
    {
    	return (List<Building>) buildingDao.findAllBuildings();
    }
    
    public Building getBuildingByName(String name)
    {
    	return buildingDao.getBuildingByName(name);
    }
    
    public JsonObject getBuildingGeoJson(String name) {
    	Building building = getBuildingByName(name);
    	return buildingGeoJSON.createGeoJSON(building);
    }
    
    public JsonObject getAllBuildingsGeoJson() {
    	Collection<Building> buildings = new ArrayList<Building>();
    	buildings = getAllBuildings();
    	
    	return buildingGeoJSON.createMultipleGeoJSON(buildings);
    }
    
    public JsonObject createBuildingGeoJSON(Building building) {
    	return buildingGeoJSON.createGeoJSON(building);
    }
    
    public JsonObject createBuilingsGeoJSON(Collection<Building> building) {
    	return buildingGeoJSON.createMultipleGeoJSON(building);
    }

}
