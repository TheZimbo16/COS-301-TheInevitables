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
		
		return userDTO;
	}

	public User toEntity(UserDTO request) {
		User user = new User();
		
		user.setStudentNumber(request.getStudentNumber());
		user.setStudentName(request.getStudentName());
		
		return user;
	}
	
	public User getEntity(UserDTO request) {
		User user = new User();
		
		user.getStudentNumber();
		user.getStudentName();
		
		return user;
	}
}
