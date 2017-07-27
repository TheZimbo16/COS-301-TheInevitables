package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import com.The_Inevitables.NavUP.model.GeoJSON;
import com.The_Inevitables.NavUP.web.api.dto.geoJSON.geoJSONDTO;

@Stateless
@LocalBean
public class geoJSONTransformer implements ApiTransformer {

	public geoJSONDTO toDTO(GeoJSON obj){
		geoJSONDTO geoJSONdto= new geoJSONDTO();
		
		geoJSONdto.setBuildingId(obj.getBuildingId());
		geoJSONdto.setGeoJSONId(obj.getGeoJSONId());
		geoJSONdto.setCoordinatesId(obj.getCoordinatesId());
		geoJSONdto.setType(obj.getType());
		geoJSONdto.setProperties(obj.getProperties());
		geoJSONdto.setGeometry(obj.getGeometry());
		
		
		return geoJSONdto;
	}
	
	public GeoJSON toEntity(geoJSONDTO request) {
		GeoJSON geojsonObj = new GeoJSON();
		
		geojsonObj.setBuildingId(request.getBuildingId());
		geojsonObj.setGeoJSONId(request.getGeoJSONId());
		geojsonObj.setProperties(request.getProperties());
		geojsonObj.setType(request.getType());
		geojsonObj.setGeometry(request.getGeometry());
		geojsonObj.setCoordinatesId(request.getCoordinatesId());
		
		return geojsonObj;
	}
	

}
