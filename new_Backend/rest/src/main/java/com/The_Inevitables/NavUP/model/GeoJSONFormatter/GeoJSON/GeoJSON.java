package com.The_Inevitables.NavUP.model.GeoJSONFormatter.GeoJSON;
import com.google.gson.JsonObject;

public class GeoJSON {
	
	public JsonObject createCRS() {
		 JsonObject properties = new JsonObject();
		 properties.addProperty("name", "urn:ogc:def:crs:OGC:1.3:CRS84");
		 
		 JsonObject crs = new JsonObject();
		 crs.addProperty("type", "name");
		 crs.add("properties", properties);
		 
		 return crs;
	}
	
	
}
