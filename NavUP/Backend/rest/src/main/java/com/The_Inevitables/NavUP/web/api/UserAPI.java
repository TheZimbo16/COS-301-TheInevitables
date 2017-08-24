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

import com.The_Inevitables.NavUP.model.User;
import com.The_Inevitables.NavUP.service.user.UserService;
import com.The_Inevitables.NavUP.web.api.dto.user.UserDTO;
import com.The_Inevitables.NavUP.web.api.transformer.UserTransformer;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Path("user")
@RequestScoped
public class UserAPI {
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
	com.The_Inevitables.NavUP.service.user.UserService userService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.UserTransformer userTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO createUser(UserDTO request){
		User user = userService.createUser(userTransformer.toEntity(request));
		return userTransformer.toDTO(user);
	}
	
	 @GET 
	 @Path("get")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<User> getAllCustomers() {
		ArrayList<User> list = (ArrayList<User>) userService.findAllUsers();
	        return  list;
	 } 
	 
	 @DELETE
	 @Path("delete")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteProblem(UserDTO request){
			userService.removeUser(request.getStudentNumber());
		}
	 
	 @POST
	 @Path("verify")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public boolean verifyUser(UserDTO request)
	 {
		 return userService.verifyUser(request.getStudentNumber(), request.getUserPassword());
	 }
	 
//	 public User verifyUser(UserDTO request)
//	 {
//		 User user = userService.getUser(request.getStudentNumber());
//		 return user;
//	 }
	  @Path("/showallitems")
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response showAllItems(@HeaderParam("token") String token) 
	      throws JsonGenerationException,
	    JsonMappingException, IOException {
	    //Item item = null;
	    logger.info("Inside showAllItems...");
	    if (token == null) {
	      //StatusMessage statusMessage = new StatusMessage();
	      //statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
	      //statusMessage.setMessage(
		  return Response.status(Status.FORBIDDEN.getStatusCode())
		       .entity("YOU ARE NOT AUTHORIZED TO MAKE THIS REQUEST").build();
	    }
	    JsonWebKeySet jwks = new JsonWebKeySet(jwkList); 
	    JsonWebKey jwk = jwks.findJsonWebKey("1", null,  null,  null);
	    logger.info("JWK (1) ===> " + jwk.toJson());
	    // Validate Token's authenticity and check claims
	    JwtConsumer jwtConsumer = new JwtConsumerBuilder()
	      .setRequireExpirationTime()
	      .setAllowedClockSkewInSeconds(30)
	      .setRequireSubject()
	      .setExpectedIssuer("avaldes.com")
	      .setVerificationKey(jwk.getKey())
	      .build();
	    try {
	      //  Validate the JWT and process it to the Claims
	      JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
	      logger.info("JWT validation succeeded! " + jwtClaims);
	    } catch (InvalidJwtException e) {
	      logger.error("JWT is Invalid: " + e);
	      return Response.status(
	          Status.FORBIDDEN.getStatusCode())
	          .entity("YOU ARE NOT AUTHORIZED TO MAKE THIS REQUEST").build();
	    }
	    ArrayList<User> list = (ArrayList<User>) userService.findAllUsers();
        return  Response.status(200).entity(list).build();

	  }
	  
	  	@Path("/authenticate")
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response authenticateCredentials(@HeaderParam("studentNo") 
	     	int studentNo,
	        @HeaderParam("password") String password)
	        throws JsonGenerationException, JsonMappingException, 
	                IOException {
	      logger.info("Authenticating User Credentials...");
	      if (studentNo == 0) {
	       // StatusMessage statusMessage = new StatusMessage();
	       // statusMessage.setStatus(
	            //Status.PRECONDITION_FAILED.getStatusCode());
	        //statusMessage.setMessage("Username value is missing!!!");
	        return Response.status(
	            Status.PRECONDITION_FAILED.getStatusCode())
	            .entity("failed").build();
	      }
	      if (password == null) {
	       // StatusMessage statusMessage = new StatusMessage();
	       // statusMessage.setStatus(
	          //Status.PRECONDITION_FAILED.getStatusCode());
	       // statusMessage.setMessage("Password value is missing!!!");
	        return Response.status(
	            Status.PRECONDITION_FAILED.getStatusCode())
	            .entity("failed").build();
	      }
	      boolean validate=userService.verifyUser(studentNo, password); 
	      if (validate == false) {
	        //StatusMessage statusMessage = new StatusMessage();
	        //statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
	        //statusMessage.setMessage(

	        return Response.status(Status.FORBIDDEN.getStatusCode())
	            .entity("failed").build();
	      }
	      User user = userService.getUser(studentNo);
	      RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);
	      senderJwk.setKeyId("1");
	      logger.info("JWK (1) ===> " + senderJwk.toJson());
	      JwtClaims claims = new JwtClaims();
	      claims.setIssuer("avaldes.com");
	      claims.setExpirationTimeMinutesInTheFuture(10);
	      claims.setGeneratedJwtId();
	      claims.setIssuedAtToNow();
	      claims.setNotBeforeMinutesInThePast(2);
	      claims.setSubject(user.getStudentName());
	      //claims.setStringListClaim("roles", user.getRolesList()); 
	      JsonWebSignature jws = new JsonWebSignature();
	      jws.setPayload(claims.toJson());
	      jws.setKeyIdHeaderValue(senderJwk.getKeyId());
	      jws.setKey(senderJwk.getPrivateKey());
	      jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256); 
	      String jwt = null;
	      try {
	        jwt = jws.getCompactSerialization();
	      } catch (JoseException e) {
	        e.printStackTrace();
	      }
	      return Response.status(200).entity(jwt).build();
	    }

	  
}
	

