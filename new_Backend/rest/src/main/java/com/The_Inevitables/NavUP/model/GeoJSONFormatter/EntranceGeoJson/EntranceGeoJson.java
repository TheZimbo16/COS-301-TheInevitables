package com.The_Inevitables.NavUP.model.GeoJSONFormatter.EntranceGeoJson;
import java.util.ArrayList;
import java.util.Collection;

import com.The_Inevitables.NavUP.model.Entrances.Entrance;
import com.The_Inevitables.NavUP.model.GeoJSONFormatter.GeoJSON.GeoJSON;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class EntranceGeoJson extends GeoJSON{

	private JsonObject geoJson;
	
	public JsonObject getGeoJson() {
		return this.geoJson;
	}
	
	public JsonObject createGeoJSON(Entrance entrance) {
		JsonObject featureCollection = new JsonObject();
		 
		 try {
			 featureCollection.addProperty("type", "featureCollection");
			 featureCollection.add("crs", createCRS());
			 featureCollection.add("features", createFeature(entrance));
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 this.geoJson = featureCollection;
		 return featureCollection;
	}
	
	public JsonArray createFeature(Entrance e) {
		JsonArray features = new JsonArray();
		JsonObject feature = addFeature(e);
		features.add(feature);
		return features;
	}
	
	public JsonArray createFeatures(Collection<Entrance> e) {
		JsonArray features = new JsonArray();
		for(Entrance entrance: e) {
				features.add(addFeature(entrance));
		}
		return features;
	}
	
	public JsonObject addFeature(Entrance e) {
		JsonObject feature = new JsonObject();
		JsonObject properties = new JsonObject();
		properties.addProperty("name", e.getName());
		properties.addProperty("description", e.getDescription());
		properties.addProperty("id", e.getId());
		
		JsonObject geometry = new JsonObject();
		JsonArray coordinates = new JsonArray();
		geometry.addProperty("type", "Point");
		
		JsonArray coords = new JsonArray();
		for(int i = 0; i < e.getCoordinates().size(); i++) {
			JsonPrimitive elem = new JsonPrimitive(e.getCoordinates().get(i));
			coords.add(elem);
		}
		
		coordinates = coords;
		
		geometry.add("coordinates", coordinates);
		
		feature.addProperty("type", "Feature");
		feature.add("properties", properties);
		feature.add("geometry", geometry);
		
		return feature;
	}
	
	public JsonObject createGeoJSON(Collection<Entrance> entrances) {
		JsonObject featureCollection = new JsonObject();
		featureCollection.addProperty("type", "featureCollection");
		featureCollection.add("crs", createCRS());
		featureCollection.add("features", createFeatures(entrances));
		
		this.geoJson = featureCollection;
		return featureCollection;
	}
	
	public Collection<Entrance> getObjectsFromGeoJSON(JsonArray features) {
		Collection<Entrance> entrances = new ArrayList<Entrance>();
		ArrayList<Float> coordinates = new ArrayList<Float>();
		
		for(JsonElement f: features) {
			Entrance entrance = new Entrance();
			JsonObject properties = f.getAsJsonObject().getAsJsonObject("properties");
			JsonObject geometry = f.getAsJsonObject().getAsJsonObject("geometry");
			JsonArray coords = geometry.getAsJsonArray("coordinates");
			
			for(int i = 0; i < coords.size(); i++) {
				Float tmp = new Float(coords.get(i).getAsFloat());
				coordinates.add(tmp);
			}
			
			entrance.setName(properties.get("name").getAsString().toString());
			entrance.setDescription(properties.get("description").getAsString().toString());
			entrance.setId(properties.get("id").getAsInt());
			entrance.setCoordinates(coordinates);
			
			entrances.add(entrance);
		}
		
		return entrances;
	}
	
	public Collection<Entrance> createObjectsFromGeoJSON(JsonObject json) {
		return getObjectsFromGeoJSON(json.getAsJsonArray("features"));
	}
	
	public String printGeoJSON() {
		return this.geoJson.toString();
	}
}
