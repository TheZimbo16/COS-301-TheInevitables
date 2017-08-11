package com.The_Inevitables.NavUP.web.api.transformer;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.Feature;
import com.The_Inevitables.NavUP.model.GeoJSON;
import com.The_Inevitables.NavUP.web.api.dto.geoJSON.GeoJSONDTO;

@Stateless
@LocalBean
public class GeoJSONTransformer implements ApiTransformer {


	public GeoJSONDTO toDTO(GeoJSON geoJson){
		GeoJSONDTO geoJSONDTO = new GeoJSONDTO();
		
		geoJSONDTO.setGeoJSONiD(geoJson.getGeoJSONiD());
		geoJSONDTO.setType(geoJson.getType());
		geoJSONDTO.setCrs(geoJson.getCrs());
		geoJSONDTO.setFeatures(geoJson.getFeatures());
		
		return geoJSONDTO;
	}
	
	public GeoJSON toEntity(GeoJSONDTO request) {
		GeoJSON geoJson = new GeoJSON();
		
		geoJson.setGeoJSONiD(request.getGeoJSONiD());
		geoJson.setType(request.getType());
		geoJson.setCrs(request.getCrs());
		geoJson.setFeatures(request.getFeatures());
		
		return geoJson;
	}
	
}
