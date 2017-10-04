package com.The_Inevitables.NavUP.model.GeoJSONFormatter.BuildingGeoJson;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import com.The_Inevitables.NavUP.model.GeoJSONFormatter.GeoJSON.GeoJSON;
import com.The_Inevitables.NavUP.model.building.Building;

public class BuildingGeoJson extends GeoJSON{
	
	private JsonObject buildingGeoJSON;
	
	public JsonObject getGeoJson() {
		return this.buildingGeoJSON;
	}

	public JsonObject createGeoJSON(Building b)
	{
		 JsonObject featureCollection = new JsonObject();
		 
		 try {
			 featureCollection.addProperty("type", "featureCollection");
			 featureCollection.add("crs", createCRS());
			 featureCollection.add("features", createFeature(b));
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 this.buildingGeoJSON = featureCollection;
		 return featureCollection;
	}
	
	public JsonArray createFeature(Building b) {
		JsonArray features = new JsonArray();
		JsonObject feature = addFeature(b);
		features.add(feature);
		return features;
	}
	
	public JsonArray createFeatures(Collection<Building> b) {
		JsonArray features = new JsonArray();
		for(Building building: b) {
				features.add(addFeature(building));
		}
		return features;
	}
	
	public JsonObject addFeature(Building b) {
		JsonObject feature = new JsonObject();
		JsonObject properties = new JsonObject();
		properties.addProperty("name", b.getName());
		properties.addProperty("description", b.getDescription() );
		properties.addProperty("id", b.getId() );
		
		JsonObject geometry = new JsonObject();
		JsonArray coordinates = new JsonArray();
		JsonArray coordinatesLevelTwo = new JsonArray();
		geometry.addProperty("type", "Polygon");
		
		
		for (int i = 0; i < b.getCoordinates().size(); i++) {
			 for (int j = 0; j < b.getCoordinates().get(i).size(); j++) {
				 JsonArray myArray = new JsonArray();
				 for(int c = 0; c < b.getCoordinates().get(i).get(j).size(); c++) {
					 JsonPrimitive elem = new JsonPrimitive(b.getCoordinates().get(i).get(j).get(c));
					 myArray.add(elem);
				 }
				 coordinatesLevelTwo.add(myArray);
			 }
		}
		
		coordinates.add(coordinatesLevelTwo);
		geometry.add("coordinates", coordinates);
		
		
		feature.addProperty("type", "Feature");
		feature.add("properties", properties);
		feature.add("geometry", geometry);
		
		return feature;
	}
	
	public JsonObject createMultipleGeoJSON(Collection<Building> buildings) {
		JsonObject featureCollection = new JsonObject();
		 featureCollection.addProperty("type", "featureCollection");
		 featureCollection.add("crs", createCRS());
		 featureCollection.add("features", createFeatures(buildings));
		
		this.buildingGeoJSON = featureCollection;
		return featureCollection;
	}
	
	public Collection<Building> getObjectsFromGeoJSON(JsonArray features) {
		Collection<Building> buildings = new ArrayList<Building>();
		
		ArrayList<ArrayList<ArrayList<Float>>> coordinates = new ArrayList<ArrayList<ArrayList<Float>>>();
		ArrayList<ArrayList<Float>> temp = new ArrayList<ArrayList<Float>>();
		ArrayList<Float> coordinateValues = new ArrayList<Float>();
		
		for(JsonElement f: features) {
			Building newBuilding = new Building();
			JsonObject properties = f.getAsJsonObject().getAsJsonObject("properties");
			JsonObject geometry = f.getAsJsonObject().getAsJsonObject("geometry");
			JsonArray coords = geometry.getAsJsonArray("coordinates");
			
			for(int i = 0; i < coords.size(); i++) {
				JsonArray innerArray = (JsonArray) coords.get(i);
				for(int j = 0; j < innerArray.size(); j++) {
					JsonArray coordinateTriplets = (JsonArray) innerArray.get(j);
					for(int c = 0; c < coordinateTriplets.size(); c++) {
						Float tmp = new Float(coordinateTriplets.get(c).getAsFloat());
						coordinateValues.add(tmp);
					}
					temp.add(coordinateValues);
				}
			}
			
			coordinates.add(temp);
			newBuilding.setName(properties.get("name").getAsString().toString());
			newBuilding.setDescription(properties.get("description").getAsString().toString());
			newBuilding.setId(properties.get("id").getAsInt());
			newBuilding.setCoordinates(coordinates);
			buildings.add(newBuilding);
		}
		
		return buildings;
	}
	
	public Collection<Building> createObjectsFromGeoJSON(JsonObject json) {
		return getObjectsFromGeoJSON(json.getAsJsonArray("features"));
	}
	
	public String printGeoJSON() {
		return this.buildingGeoJSON.toString();
	}
}
