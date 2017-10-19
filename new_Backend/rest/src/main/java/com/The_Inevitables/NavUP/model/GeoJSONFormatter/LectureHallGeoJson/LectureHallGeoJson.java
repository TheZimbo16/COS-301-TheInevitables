package com.The_Inevitables.NavUP.model.GeoJSONFormatter.LectureHallGeoJson;
import java.util.ArrayList;
import java.util.Collection;

import com.The_Inevitables.NavUP.model.GeoJSONFormatter.GeoJSON.GeoJSON;
import com.The_Inevitables.NavUP.model.LectureHall.LectureHall;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class LectureHallGeoJson extends GeoJSON {
	private JsonObject lectureHallGeoJSON;
	
	public JsonObject getGeoJson() {
		return this.lectureHallGeoJSON;
	}
	
	public JsonObject createGeoJSON(LectureHall lectureHall) {
		JsonObject FeatureCollection = new JsonObject();
		 
		 try {
			 FeatureCollection.addProperty("type", "FeatureCollection");
			 FeatureCollection.add("crs", createCRS());
			 FeatureCollection.add("features", createFeature(lectureHall));
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 this.lectureHallGeoJSON = FeatureCollection;
		 return FeatureCollection;
	}
	
	public JsonArray createFeature(LectureHall lectureHall) {
		JsonArray features = new JsonArray();
		JsonObject feature = addFeature(lectureHall);
		features.add(feature);
		return features;
	}
	
	public JsonArray createFeatures(Collection<LectureHall> lectureHall) {
		JsonArray features = new JsonArray();
		for(LectureHall l: lectureHall) {
				features.add(addFeature(l));
		}
		return features;
	}
	
	public JsonObject addFeature(LectureHall lectureHall) {
		JsonObject feature = new JsonObject();
		JsonObject properties = new JsonObject();
		properties.addProperty("room_name", lectureHall.getRoom_name());
		properties.addProperty("building", lectureHall.getBuilding());
		properties.addProperty("level", lectureHall.getLevel());
		
		JsonObject geometry = new JsonObject();
		JsonArray coordinates = new JsonArray();
		JsonArray coordinatesLevelTwo = new JsonArray();
		geometry.addProperty("type", "Polygon");
		
		
		for (int i = 0; i < lectureHall.getCoordinates().size(); i++) {
			 for (int j = 0; j < lectureHall.getCoordinates().get(i).size(); j++) {
				 JsonArray myArray = new JsonArray();
				 for(int c = 0; c < lectureHall.getCoordinates().get(i).get(j).size(); c++) {
					 JsonPrimitive elem = new JsonPrimitive(lectureHall.getCoordinates().get(i).get(j).get(c));
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
	
	public JsonObject createGeoJSON(Collection<LectureHall> lectureHalls) {
		JsonObject FeatureCollection = new JsonObject();
		 FeatureCollection.addProperty("type", "FeatureCollection");
		 FeatureCollection.add("crs", createCRS());
		 FeatureCollection.add("features", createFeatures(lectureHalls));
		
		this.lectureHallGeoJSON = FeatureCollection;
		return FeatureCollection;
	}
	
	public Collection<LectureHall> getObjectsFromGeoJSON(JsonArray features) {
		Collection<LectureHall> lectureHalls = new ArrayList<LectureHall>();
		
		for(JsonElement f: features) {
			ArrayList<ArrayList<ArrayList<Float>>> coordinates = new ArrayList<ArrayList<ArrayList<Float>>>();
			ArrayList<ArrayList<Float>> temp = new ArrayList<ArrayList<Float>>();
			ArrayList<Float> coordinateValues = new ArrayList<Float>();
			
			LectureHall newLectureHall = new LectureHall();
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
			newLectureHall.setBuilding(properties.get("building").getAsString().toString());
			newLectureHall.setRoom_name(properties.get("room_name").getAsString().toString());
			newLectureHall.setLevel(Integer.parseInt(properties.get("level").getAsString().toString()));
			newLectureHall.setCoordinates(coordinates);
			lectureHalls.add(newLectureHall);
		}
		
		return lectureHalls;
	}
	
	public Collection<LectureHall> createObjectsFromGeoJSON(JsonObject json) {
		return getObjectsFromGeoJSON(json.getAsJsonArray("features"));
	}
	
	public String printGeoJSON() {
		return this.lectureHallGeoJSON.toString();
	}
}
