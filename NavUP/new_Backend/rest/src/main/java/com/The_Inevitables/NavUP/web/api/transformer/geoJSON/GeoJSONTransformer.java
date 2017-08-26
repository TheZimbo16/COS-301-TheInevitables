package com.The_Inevitables.NavUP.web.api.transformer.geoJSON;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.GeoJSON.GeoJSON;
import com.The_Inevitables.NavUP.web.api.dto.geoJSON.GeoJSONDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;

@Stateless
@LocalBean
public class GeoJSONTransformer implements ApiTransformer {
	
	public GeoJSONDTO toDTO(GeoJSON geoJson){
		GeoJSONDTO geoJSONDTO = new GeoJSONDTO();
		
		geoJSONDTO.setGeoJSONiD(geoJson.getGeoJSONiD());
		geoJSONDTO.setFeatures(geoJson.getFeatures());
		
		return geoJSONDTO;
	}
	
	public GeoJSON toEntity(GeoJSONDTO request) {
		GeoJSON geoJson = new GeoJSON();
		
		geoJson.setGeoJSONiD(request.getGeoJSONiD());
		geoJson.setFeatures(request.getFeatures());
		
		return geoJson;
	}
	
}
