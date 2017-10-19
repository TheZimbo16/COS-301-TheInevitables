package com.The_Inevitables.NavUP.web.api.api_modules.stairs;

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

import com.The_Inevitables.NavUP.model.GeoJSONFormatter.StairsGeoJson.StairsGeoJson;
import com.The_Inevitables.NavUP.model.stairs.Stairs;
import com.The_Inevitables.NavUP.web.api.dto.stairs.StairsDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("stairs")
@RequestScoped
public class StairsAPI {

	@EJB
	com.The_Inevitables.NavUP.service.stairs.StairsService stairsService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.stairs.StairsTransformer stairsTransformer;
	
	StairsGeoJson geoJSON = new StairsGeoJson();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StairsDTO createStairs(StairsDTO request) {
		Stairs stairs = stairsService.createStairs(stairsTransformer.toEntity(request));
		return stairsTransformer.toDTO(stairs);
	}
	
	@DELETE
	@Path("delete/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteStairsByName(StairsDTO request) {
		stairsService.deleteStairByName(request.getBuilding());
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Stairs> getAllStairs() {
		return (List<Stairs>) stairsService.getAllStairs();
	}
	
	@GET
	@Path("get/geoJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStairsAsGeoJSON() {
		Collection<Stairs> stairs = stairsService.getAllStairs();
		geoJSON.createGeoJSON(stairs);
		return geoJSON.printGeoJSON();
	}
	
	@POST 
	@Path("geoJSON")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Stairs> getStairsFromGeoJSON(String request) {
		 JsonParser parser = new JsonParser();
		 JsonObject o = parser.parse(request).getAsJsonObject();
		 
		 try {
			 Collection<Stairs> stairs = geoJSON.createObjectsFromGeoJSON(o);
			 for(Stairs s : stairs) {
				 stairsService.createStairs(s);
			 }
		 }
		 catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pr = new PrintWriter(sw);
			e.printStackTrace(pr);
			String stackTrance = sw.toString();
			System.out.println(stackTrance);
		}
		 
		 return stairsService.getAllStairs();
	}
}
