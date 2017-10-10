package com.The_Inevitables.NavUP.web.api.api_modules.building;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.The_Inevitables.NavUP.model.GeoJSONFormatter.BuildingGeoJson.BuildingGeoJson;
import com.The_Inevitables.NavUP.model.building.Building;
import com.The_Inevitables.NavUP.web.api.dto.building.BuildingDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("building")
@RequestScoped
public class BuildingAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.building.BuildingService buildingService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.building.BuildingTransformer buildingTransformer;
	
	BuildingGeoJson geoJson = new BuildingGeoJson();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BuildingDTO createBuilding(BuildingDTO request){
		Building building = buildingService.createBuilding(buildingTransformer.toEntity(request));
		return buildingTransformer.toDTO(building);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Building> getAllBuildings() {
	        return  (List<Building>) buildingService.getAllBuildings();
	}
	
	@DELETE
	@Path("delete/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBuildingByName(BuildingDTO request){
			buildingService.removeBuildingByName(request.getName());
	}
	
	
	 @POST
	 @Path("find/name")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Building findBuildingByName(BuildingDTO request)
	 {
		 return buildingService.getBuildingByName(request.getName());
	 }
	 
	 @POST 
	 @Path("get/geoJSON/name")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Building getGeoJSONByName(BuildingDTO request) {
		 return buildingService.getBuildingByName(request.getName());
	 }
	 
	 @GET
	 @Path("get/geoJSON")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String createBuildingsGeoJSON() {
		 Collection<Building> buildings = buildingService.getAllBuildings();
		 geoJson.createMultipleGeoJSON(buildings);
		 return geoJson.printGeoJSON();
	 }
	 
	 @POST 
	 @Path("geoJSON/building")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<Building> getBuildingFromGeoJSON(String request) {
		 BuildingGeoJson buildingGeoJSON = new BuildingGeoJson();
		 JsonParser parser = new JsonParser();
		 JsonObject o = parser.parse(request).getAsJsonObject();
		 
		 try {
			 Collection<Building> buildings = buildingGeoJSON.createObjectsFromGeoJSON(o);
			 for(Building b : buildings) {
				 buildingService.createBuilding(b);
			 }
		 }
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pr = new PrintWriter(sw);
			e.printStackTrace(pr);
			String stackTrance = sw.toString();
			System.out.println(stackTrance);
		}
		 
		 return buildingService.getAllBuildings();
	 }

}
