package com.The_Inevitables.NavUP.web.api.api_modules.entrance;

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

import com.The_Inevitables.NavUP.model.Entrances.Entrance;
import com.The_Inevitables.NavUP.model.GeoJSONFormatter.EntranceGeoJson.EntranceGeoJson;
import com.The_Inevitables.NavUP.web.api.dto.entrance.EntranceDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("entrance")
@RequestScoped
public class EntranceAPI {

	@EJB
	com.The_Inevitables.NavUP.service.entrance.EntranceService entranceService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.entrance.EntranceTransformer entranceTransformer;
	
	EntranceGeoJson entranceGeoJSON = new EntranceGeoJson();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EntranceDTO createEntrance(EntranceDTO request) {
		Entrance entrance = entranceService.createEntrance(entranceTransformer.toEntity(request));
		return entranceTransformer.toDTO(entrance);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllEntrances() {
		Collection<Entrance> entrances = entranceService.getAllEntrances();
		entranceGeoJSON.createGeoJSON(entrances);
		return entranceGeoJSON.printGeoJSON();
	}
	
	@DELETE
	@Path("delete/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBuildingByName(EntranceDTO request) {
		entranceService.deleteEntranceByName(request.getName());
	}
	
	 @POST
	 @Path("find/name")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public JsonObject findEntranceByName(EntranceDTO request) {
		 Entrance entrance = entranceService.getEntranceByName(request.getName());
		 return entranceGeoJSON.createGeoJSON(entrance);
	 }
	 
	 @POST 
	 @Path("geoJSON")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<Entrance> getEntranceFromGeoJSON(String request) {
		 JsonParser parser = new JsonParser();
		 JsonObject o = parser.parse(request).getAsJsonObject();
		 
		 try {
			 Collection<Entrance> entrances = entranceGeoJSON.createObjectsFromGeoJSON(o);
			 for(Entrance e : entrances) {
				 entranceService.createEntrance(e);
			 }
		 }
		 catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pr = new PrintWriter(sw);
			e.printStackTrace(pr);
			String stackTrance = sw.toString();
			System.out.println(stackTrance);
		}
		 
		return entranceService.getAllEntrances();
	 }
}
