package com.The_Inevitables.NavUP.service.geoJSON;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import com.The_Inevitables.NavUP.dao.impl.GeoJSONDao;
import com.The_Inevitables.NavUP.model.GeoJSON;

@Stateless
public class GeoJSONService {

  @EJB
  GeoJSONDao geoJSONdao;
  
  public GeoJSON createObject(GeoJSON entity)
  {
  	return geoJSONdao.create(entity);
  }
  @Transactional 
  public GeoJSON getAllObjects()
  {
  	return geoJSONdao.findAllGeoJSON();
  }

}
