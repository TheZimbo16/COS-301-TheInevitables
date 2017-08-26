package com.The_Inevitables.NavUP.service.feature;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.geoJSON.FeatureDao;
import com.The_Inevitables.NavUP.model.GeoJSON.Feature;


@Stateless
public class FeatureService {

    @EJB
    FeatureDao featureDao;
    
    public Feature createObject(Feature entity)
    {
    	return featureDao.create(entity);
    }
    
    public List<Feature> getAllObjects()
    {
    	return  featureDao.findAllObjects();
    }
    

}
