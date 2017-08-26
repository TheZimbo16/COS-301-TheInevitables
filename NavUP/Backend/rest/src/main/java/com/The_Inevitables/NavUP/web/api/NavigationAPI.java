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


import com.The_Inevitables.NavUP.model.Navigation;
import com.The_Inevitables.NavUP.web.api.dto.navigation.NavigationDTO;

@Path("nav")
@RequestScoped
public class NavigationAPI {

	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.NavigationTransformer navigationTransformer;
	
	@EJB
	com.The_Inevitables.NavUP.service.navigation.NavigationService navigationService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public NavigationDTO createLocation(NavigationDTO request)
	{
		Navigation navigation = navigationService.createCoordinates(navigationTransformer.toEntity(request));
		return navigationTransformer.toDTO(navigation);
	}
	
	 @POST
	 @Path("getSingleUser")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Navigation verifyUser(NavigationDTO request)
	 {
		 return navigationService.getCoordinates(request.getLocationName());
	 }
}
