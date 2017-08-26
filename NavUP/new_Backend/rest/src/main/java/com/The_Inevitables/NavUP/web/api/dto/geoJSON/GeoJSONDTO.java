package com.The_Inevitables.NavUP.web.api.dto.geoJSON;

import java.util.Collection;

import com.The_Inevitables.NavUP.model.GeoJSON.Feature;
import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class GeoJSONDTO extends DTO implements ApiResponse{
	
	private static final long serialVersionUID = -1339921244123546454L;
	
	private int geoJSONiD;
	private Collection<Feature> features;
	
	public int getGeoJSONiD() {
		return geoJSONiD;
	}
	
	public void setGeoJSONiD(int geoJSONiD) {
		this.geoJSONiD = geoJSONiD;
	}
	
	public Collection<Feature> getFeatures() {
		return features;
	}
	
	public void setFeatures(Collection<Feature> collection) {
		this.features = collection;
	}
	
}


