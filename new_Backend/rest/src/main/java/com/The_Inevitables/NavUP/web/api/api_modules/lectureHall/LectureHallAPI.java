package com.The_Inevitables.NavUP.web.api.api_modules.lectureHall;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

import com.The_Inevitables.NavUP.model.GeoJSONFormatter.LectureHallGeoJson.LectureHallGeoJson;
import com.The_Inevitables.NavUP.model.LectureHall.LectureHall;
import com.The_Inevitables.NavUP.web.api.dto.lectureHall.LectureHallDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("lecture-hall")
@RequestScoped
public class LectureHallAPI {

	@EJB
	com.The_Inevitables.NavUP.service.lectureHall.LectureHallService lectureHallService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.lectureHall.LectureHallTransformer lectureHallTransformer;
	
	LectureHallGeoJson geoJSON = new LectureHallGeoJson();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LectureHallDTO createLectureHall(LectureHallDTO request) {
		LectureHall lectureHall = lectureHallService.createLectureHall(lectureHallTransformer.toEntity(request));
		return lectureHallTransformer.toDTO(lectureHall);
	}
	
	@DELETE
	@Path("delete/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteByName(LectureHallDTO request) {
		lectureHallService.deleteLectureHallByRoomName(request.getRoom_name());
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LectureHall> getAllLectureHalls() {
		return (List<LectureHall>) lectureHallService.getAllLectureHalls();
	}
	
	@GET
	@Path("get/geoJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllLectureHallsAsGeoJSON() {
		
		try {
			Collection<LectureHall> lectureHalls = lectureHallService.getAllLectureHalls();
			return geoJSON.createGeoJSON(lectureHalls).toString();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	@POST
	@Path("get/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LectureHall getLectureHallByName(LectureHallDTO request) {
		return lectureHallService.getLectureHallByRoomName(request.getRoom_name());
	}
	
	@POST
	@Path("get/geoJSON/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getLectureHallGeoJSONByName(LectureHallDTO request) {
		LectureHall lectureHall =  lectureHallService.getLectureHallByRoomName(request.getRoom_name());
		geoJSON.createGeoJSON(lectureHall);
		return geoJSON.printGeoJSON();
	}
	
	@POST
	@Path("get/geoJSON/building")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getLectureHallByBuildingName(LectureHallDTO request) {
		try {
			List<LectureHall> lectureHalls = lectureHallService.getBuildingLectureHalls(request.getBuilding());
			
			if(lectureHalls == null)
				throw new Exception("no lecture halls found");
			
			geoJSON.createGeoJSON(lectureHalls);
			return geoJSON.printGeoJSON();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	
	@POST 
	@Path("geoJSON")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<LectureHall> getLectureHallFromGeoJSON(String request) {
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(request).getAsJsonObject();
		
		try {
			Collection<LectureHall> lectureHalls = geoJSON.createObjectsFromGeoJSON(o);
			for(LectureHall l : lectureHalls) {
				lectureHallService.createLectureHall(l);
			}
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pr = new PrintWriter(sw);
			e.printStackTrace(pr);
			String stackTrance = sw.toString();
			System.out.println(stackTrance);
		}
		
		return lectureHallService.getAllLectureHalls();
	}
	
	
}
