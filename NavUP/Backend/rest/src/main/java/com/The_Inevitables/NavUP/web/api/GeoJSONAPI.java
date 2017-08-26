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
import com.The_Inevitables.NavUP.model.GeoJSON;
import com.The_Inevitables.NavUP.service.geoJSON.GeoJSONService;
import com.The_Inevitables.NavUP.web.api.dto.geoJSON.GeoJSONDTO;
import com.The_Inevitables.NavUP.web.api.transformer.GeoJSONTransformer;

@Path("geoJSON")
@RequestScoped
public class GeoJSONAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.geoJSON.GeoJSONService geoJSONService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.GeoJSONTransformer geoJSONTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GeoJSONDTO createBuilding(GeoJSONDTO request){
		GeoJSON geoJSON = geoJSONService.createObject(geoJSONTransformer.toEntity(request));
		return geoJSONTransformer.toDTO(geoJSON);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public GeoJSON getAllObjects() {
		return geoJSONService.getAllObjects();
	       
	}
	
	
}
