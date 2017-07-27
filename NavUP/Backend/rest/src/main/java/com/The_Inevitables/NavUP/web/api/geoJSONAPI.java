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
import com.The_Inevitables.NavUP.model.GeoJSON;

import com.The_Inevitables.NavUP.web.api.dto.geoJSON.geoJSONDTO;


@Path("geoJSON")
@RequestScoped
public class geoJSONAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.geoJSON.geoJSONService GeoJSONService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.geoJSONTransformer GeoJSONTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public geoJSONDTO createObject(geoJSONDTO request){
		GeoJSON geojsonObj = GeoJSONService.createObject(GeoJSONTransformer.toEntity(request));
		return GeoJSONTransformer.toDTO(geojsonObj);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public GeoJSON getAllObjects() {
		GeoJSON list =  (GeoJSON) GeoJSONService.getAllObjects();
	        return  list;
	}

	 
	
	
}
