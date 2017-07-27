package com.The_Inevitables.NavUP.service.geoJSON;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.geoJSONDao;

import com.The_Inevitables.NavUP.model.GeoJSON;


@Stateless
public class geoJSONService {

    @EJB
    geoJSONDao GeoJSONDao;
    
    public GeoJSON createObject(GeoJSON entity)
    {
    	return GeoJSONDao.create(entity);
    }
    
    public GeoJSON getAllObjects()
    {
    	return (GeoJSON) GeoJSONDao.findAllObjects();
    }
    

}
