package com.The_Inevitables.NavUP.model.GeoJSONFormatter.StairsGeoJson;
import java.util.ArrayList;
import java.util.Collection;

import com.The_Inevitables.NavUP.model.GeoJSONFormatter.GeoJSON.GeoJSON;
import com.The_Inevitables.NavUP.model.stairs.Stairs;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class StairsGeoJson extends GeoJSON {

private JsonObject geoJSON;
	
	public JsonObject getGeoJson() {
		return this.geoJSON;
	}
	
	public JsonObject createGeoJSON(Stairs stairs) {
		JsonObject featureCollection = new JsonObject();
		 
		 try {
			 featureCollection.addProperty("type", "featureCollection");
			 featureCollection.add("crs", createCRS());
			 featureCollection.add("features", createFeature(stairs));
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 this.geoJSON = featureCollection;
		 return featureCollection;
	}
	
	public JsonArray createFeature(Stairs s) {
		JsonArray features = new JsonArray();
		JsonObject feature = addFeature(s);
		features.add(feature);
		return features;
	}
	
	public JsonArray createFeatures(Collection<Stairs> s) {
		JsonArray features = new JsonArray();
		for(Stairs stairs: s) {
				features.add(addFeature(stairs));
		}
		return features;
	}
	
	public JsonObject addFeature(Stairs s) {
		JsonObject feature = new JsonObject();
		JsonObject properties = new JsonObject();
		properties.addProperty("id", s.getId());
		properties.addProperty("level", s.getLevel());
		properties.addProperty("position", s.getPosition());
		properties.addProperty("building", s.getBuilding());
		
		JsonObject geometry = new JsonObject();
		JsonArray coordinates = new JsonArray();
		geometry.addProperty("type", "Point");
		
		JsonArray coords = new JsonArray();
		for(int i = 0; i < s.getCoordinates().size(); i++) {
			JsonPrimitive elem = new JsonPrimitive(s.getCoordinates().get(i));
			coords.add(elem);
		}
		
		coordinates = coords;
		
		geometry.add("coordinates", coordinates);
		
		feature.addProperty("type", "Feature");
		feature.add("properties", properties);
		feature.add("geometry", geometry);
		
		return feature;
	}
	
	public JsonObject createGeoJSON(Collection<Stairs> stairs) {
		JsonObject featureCollection = new JsonObject();
		featureCollection.addProperty("type", "featureCollection");
		featureCollection.add("crs", createCRS());
		featureCollection.add("features", createFeatures(stairs));
		
		this.geoJSON = featureCollection;
		return featureCollection;
	}
	
	public Collection<Stairs> getObjectsFromGeoJSON(JsonArray features) {
		Collection<Stairs> stairs = new ArrayList<Stairs>();
		ArrayList<Float> coordinates = new ArrayList<Float>();
		
		for(JsonElement f: features) {
			Stairs stair = new Stairs();
			JsonObject properties = f.getAsJsonObject().getAsJsonObject("properties");
			JsonObject geometry = f.getAsJsonObject().getAsJsonObject("geometry");
			JsonArray coords = geometry.getAsJsonArray("coordinates");
			
			for(int i = 0; i < coords.size(); i++) {
				Float tmp = new Float(coords.get(i).getAsFloat());
				coordinates.add(tmp);
			}
			
			stair.setId(Integer.parseInt(properties.get("id").getAsString().toString()));
			stair.setPosition(properties.get("position").getAsString().toString());
			stair.setLevel(Integer.parseInt(properties.get("level").getAsString().toString()));
			stair.setBuilding(properties.get("building").getAsString().toString());
			stair.setCoordinates(coordinates);
			
			stairs.add(stair);
		}
		
		return stairs;
	}
	
	public Collection<Stairs> createObjectsFromGeoJSON(JsonObject json) {
		return getObjectsFromGeoJSON(json.getAsJsonArray("features"));
	}
	
	public String printGeoJSON() {
		return this.geoJSON.toString();
	}
}
