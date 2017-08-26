package com.The_Inevitables.NavUP.web.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import com.The_Inevitables.NavUP.model.POI;
import com.The_Inevitables.NavUP.model.User;
import com.The_Inevitables.NavUP.service.user.UserService;
import com.The_Inevitables.NavUP.web.api.dto.poi.POIDTO;
import com.The_Inevitables.NavUP.web.api.dto.user.UserDTO;
import com.The_Inevitables.NavUP.web.api.transformer.UserTransformer;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Path("poi")
@RequestScoped
public class POIAPI {
	 static Logger logger = Logger.getLogger(UserAPI.class);
	  static List<JsonWebKey> jwkList = null;
	  
	  static {    
		      logger.info("Inside static initializer...");
		      jwkList = new LinkedList<>(); 
		      for (int kid = 1; kid <= 3; kid++) { 
		        JsonWebKey jwk = null;
		        try {
		          jwk = RsaJwkGenerator.generateJwk(2048); 
		          logger.info("PUBLIC KEY (" + kid + "): "
		            + jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
		        } catch (JoseException e) {
		          e.printStackTrace();
		        } 
		        jwk.setKeyId(String.valueOf(kid));  
		        jwkList.add(jwk); 
		      } 
		    }

	@EJB
	com.The_Inevitables.NavUP.service.POI.POIService userService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.POITransformer userTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public POIDTO createUser(POIDTO request){
		POI user = userService.createUser(userTransformer.toEntity(request));
		return userTransformer.toDTO(user);
	}
	
	 @GET 
	 @Path("get")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<POI> getAllLocations() {
		ArrayList<POI> list = (ArrayList<POI>) userService.findAllUsers();
	        return  list;
	 } 
	 
	 
	  
}
	
