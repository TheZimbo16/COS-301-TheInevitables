package com.The_Inevitables.NavUP.web.api.api_modules.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.The_Inevitables.NavUP.model.user.User;
import com.The_Inevitables.NavUP.web.api.dto.user.UserDTO;


@Path("user")
@RequestScoped
public class UserAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.user.UserService userService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.user.UserTransformer userTransformer;
	
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
}
