package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.User;
import com.The_Inevitables.NavUP.web.api.dto.user.UserDTO;

@LocalBean
@Stateless
public class UserTransformer implements ApiTransformer{

	public UserDTO toDTO(User user){
		UserDTO userDTO = new UserDTO();

		userDTO.setStudentNumber(user.getStudentNumber());
		userDTO.setStudentName(user.getStudentName());
		userDTO.setUserDisabled(user.getUserDisabled());
		userDTO.setUserPassword(user.getUserPassword());
		userDTO.setUserSurname(user.getUserSurname());
		
		return userDTO;
	}

	public User toEntity(UserDTO request) {
		User user = new User();
		
		user.setStudentNumber(request.getStudentNumber());
		user.setStudentName(request.getStudentName());
		user.setUserDisabled(request.getUserDisabled());
		user.setUserPassword(request.getUserPassword());
		user.setUserSurname(request.getUserSurname());
		
		return user;
	}
	
	public User getEntity(UserDTO request) {
		User user = new User();
		
		user.getStudentNumber();
		user.getStudentName();
		user.getUserDisabled();
		user.getUserPassword();
		user.getUserSurname();
		
		
		return user;
	}
}
