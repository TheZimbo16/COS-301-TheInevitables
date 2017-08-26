package com.The_Inevitables.NavUP.web.api.api_modules.geoJSON;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.The_Inevitables.NavUP.model.GeoJSON.Feature;

import com.The_Inevitables.NavUP.web.api.dto.feature.FeatureDTO;


@Path("feature")
@RequestScoped
public class FeatureAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.feature.FeatureService featureService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.geoJSON.FeatureTransformer featureTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FeatureDTO createObject(FeatureDTO request){
		Feature feature = featureService.createObject(featureTransformer.toEntity(request));
		return featureTransformer.toDTO(feature);
	}
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Feature> getAllObjects() {
		List<Feature> list =  featureService.getAllObjects();
	    return  list;
	}
}
