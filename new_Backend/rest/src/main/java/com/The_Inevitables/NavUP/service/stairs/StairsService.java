package com.The_Inevitables.NavUP.service.stairs;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.stairs.StairsDao;
import com.The_Inevitables.NavUP.model.GeoJSONFormatter.StairsGeoJson.StairsGeoJson;
import com.The_Inevitables.NavUP.model.stairs.Stairs;
import com.google.gson.JsonObject;

@Stateless
public class StairsService {

	@EJB
	StairsDao stairsDao; 
	
	StairsGeoJson geoJson = new StairsGeoJson();
	
	public Stairs createStairs(Stairs stair) {
		return stairsDao.create(stair);
	}
	
	public Stairs updateStairs(Stairs stair) {
		return stairsDao.update(stair);
	}
	
	public void deleteStairByName(String name) {
		stairsDao.deleteByStairName(name);
	}
	
	public List<Stairs> getAllStairs() {
		return (List<Stairs>) stairsDao.getAllStairs();
	}
	
	public Stairs getStairsByName(String name) {
		return stairsDao.getByStairsName(name);
	}
	
	public JsonObject createStairsGeoJSON(Stairs stair) {
		return geoJson.createGeoJSON(stair);
	}
	
	public JsonObject createStairsGeoJSON(Collection<Stairs> stairs) {
		return geoJson.createGeoJSON(stairs);
	}
}
