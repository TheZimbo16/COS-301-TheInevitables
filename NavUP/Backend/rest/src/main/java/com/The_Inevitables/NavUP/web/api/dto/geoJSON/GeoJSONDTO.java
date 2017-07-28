package com.The_Inevitables.NavUP.web.api.dto.geoJSON;

import java.util.List;

import com.The_Inevitables.NavUP.model.CRS;
import com.The_Inevitables.NavUP.model.Feature;
import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class GeoJSONDTO extends DTO implements ApiResponse{
	
	private static final long serialVersionUID = -1339921244123546454L;
	
	private int geoJSONiD;
	private String type;
	private CRS crs;
	private Feature features;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CRS getCrs() {
		return crs;
	}

	public void setCrs(CRS crs) {
		this.crs = crs;
	}

	public Feature getFeatures() {
		return features;
	}

	public void setFeatures(Feature feature) {
		this.features = feature;
	}
	
	public int getGeoJSONiD() {
		return geoJSONiD;
	}

	public void setGeoJSONiD(int geoJSONiD) {
		this.geoJSONiD = geoJSONiD;
	}
}
