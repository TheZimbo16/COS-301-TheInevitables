package com.The_Inevitables.NavUP.service.entrance;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.entrances.EntranceDao;
import com.The_Inevitables.NavUP.model.Entrances.Entrance;
import com.The_Inevitables.NavUP.model.GeoJSONFormatter.EntranceGeoJson.EntranceGeoJson;
import com.google.gson.JsonObject;


@Stateless
public class EntranceService {

	@EJB
	EntranceDao entranceDao;
	
	EntranceGeoJson geoJson = new EntranceGeoJson();
	
	public Entrance createEntrance(Entrance entrance) {
		return entranceDao.create(entrance);
	}
	
	public Entrance updateEntrance(Entrance entrance) {
		return entranceDao.update(entrance);
	}
	
	public void deleteEntranceByName(String entrance) {
		entranceDao.deleteEntranceByName(entrance);
	}
	
	public List<Entrance> getAllEntrances() {
		return (List<Entrance>) entranceDao.findAllEntrances();
	}
	
	public Entrance getEntranceByName(String name) {
		return entranceDao.getEntranceByName(name);
	}
	
	public JsonObject createEntanceGeoJSON(Entrance entrance) {
		return geoJson.createGeoJSON(entrance);
	}
	
	public JsonObject createEntrancesGeoJSON(Collection<Entrance> entrances) {
		return geoJson.createGeoJSON(entrances);
	}
}
