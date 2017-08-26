package com.The_Inevitables.NavUP.web.api.api_modules.coordinates;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.The_Inevitables.NavUP.model.coordinates.Coordinates;
import com.The_Inevitables.NavUP.web.api.dto.coordinates.CoordinatesDTO;

@Path("coordinates")
@RequestScoped
public class CoordinatesAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.coordinates.CoordinatesService coordinatesService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.coordinates.CoordinatesTransformer coordinatesTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CoordinatesDTO createCoords(CoordinatesDTO request){
		Coordinates coords = coordinatesService.createCoordinates(coordinatesTransformer.toEntity(request));
		return coordinatesTransformer.toDTO(coords);
	}
	
	 @GET 
	 @Path("get")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Coordinates> getAllCoordinates() {
		 return  coordinatesService.getAllCoords();
	 } 	
}
