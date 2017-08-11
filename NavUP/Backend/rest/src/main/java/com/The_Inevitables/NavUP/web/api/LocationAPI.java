package com.The_Inevitables.NavUP.web.api;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.The_Inevitables.NavUP.model.Location;
import com.The_Inevitables.NavUP.service.location.LocationService;
import com.The_Inevitables.NavUP.web.api.dto.location.LocationDTO;
import com.The_Inevitables.NavUP.web.api.transformer.LocationTransformer;

@Path("location")
@RequestScoped
public class LocationAPI {

	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.LocationTransformer locationTransformer;
	
	@EJB
	com.The_Inevitables.NavUP.service.location.LocationService locationService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LocationDTO createLocation(LocationDTO request)
	{
		Location location = locationService.createLocation(locationTransformer.toEntity(request));
		return locationTransformer.toDTO(location);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getAllLocations()
	{
		ArrayList<Location> list = (ArrayList<Location>) locationService.getAllLocations();
	    return  list;
	}
	
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteLocation(LocationDTO request)
	{
		locationService.removeLocation(request.getLocationName());
	}
}
