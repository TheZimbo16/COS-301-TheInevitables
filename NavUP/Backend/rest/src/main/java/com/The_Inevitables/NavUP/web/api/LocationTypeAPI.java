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

import com.The_Inevitables.NavUP.model.LocationType;
import com.The_Inevitables.NavUP.service.locationTypes.LocationTypesService;
import com.The_Inevitables.NavUP.web.api.dto.locationTypes.LocationTypeDTO;
import com.The_Inevitables.NavUP.web.api.transformer.LocationTypeTransformer;


@Path("location-type")
@RequestScoped
public class LocationTypeAPI {

	@EJB
	com.The_Inevitables.NavUP.service.locationTypes.LocationTypesService locationTypeService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.LocationTypeTransformer locationTypeTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LocationTypeDTO createLocationType(LocationTypeDTO request)
	{
		LocationType locationType = locationTypeService.createLocationType(locationTypeTransformer.toEntity(request));
		return locationTypeTransformer.toDTO(locationType);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LocationType> getAllLocationTypes() 
	{
		ArrayList<LocationType> list = (ArrayList<LocationType>) locationTypeService.getAllLocationTypes();
	    return  list;
	}
	
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteLocationType(LocationTypeDTO request)
	{
		locationTypeService.removeLocationType(request.getLocationDescription());
	}
}
