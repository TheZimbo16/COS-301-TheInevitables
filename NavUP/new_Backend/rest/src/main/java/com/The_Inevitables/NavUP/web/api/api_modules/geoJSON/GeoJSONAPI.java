package com.The_Inevitables.NavUP.web.api.api_modules.geoJSON;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.The_Inevitables.NavUP.model.GeoJSON.GeoJSON;
import com.The_Inevitables.NavUP.web.api.dto.geoJSON.GeoJSONDTO;

@Path("geoJSON")
@RequestScoped
public class GeoJSONAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.geoJSON.GeoJSONService geoJSONService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.geoJSON.GeoJSONTransformer geoJSONTransformer;
	
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
