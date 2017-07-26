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

import com.The_Inevitables.NavUP.model.Building;
import com.The_Inevitables.NavUP.service.building.BuildingService;
import com.The_Inevitables.NavUP.web.api.dto.building.BuildingDTO;
import com.The_Inevitables.NavUP.web.api.transformer.BuildingTransformer;

@Path("building")
@RequestScoped
public class BuildingAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.building.BuildingService buildingService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.BuildingTransformer buildingTransformer;
	
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
		ArrayList<Building> list = (ArrayList<Building>) buildingService.getAllBuildings();
	        return  list;
	}
	
	@DELETE
	@Path("delete/name")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBuildingByName(BuildingDTO request){
			buildingService.removeBuildingByName(request.getBuildingName());
	}
	
	@DELETE
	@Path("delete/abrev")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteBuildingByAbrev(BuildingDTO request){
			buildingService.removeBuildingByAbrev(request.getBuildingAbreviation());
	}
	
	 @POST
	 @Path("find/name")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Building findBuildingByName(BuildingDTO request)
	 {
		 return buildingService.getBuildingByName(request.getBuildingName());
	 }
	 
	 @POST
	 @Path("find/abrev")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Building findBuildingByAbbrev(BuildingDTO request)
	 {
		 return buildingService.getBuildingByAbrev(request.getBuildingAbreviation());
	 }
	
	
}
