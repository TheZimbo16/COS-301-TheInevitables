package com.epiuse.tenderplatform.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.epiuse.tenderplatform.model.User;
import com.epiuse.tenderplatform.web.api.dto.user.UserDTO;

@LocalBean
@Stateless
public class UserTransformer implements ApiTransformer{

	public UserDTO toDTO(User user){
		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		
		return userDTO;
	}

	public User toEntity(UserDTO request) {
		User user = new User();
		
		user.setId(request.getId());
		user.setUsername(request.getUsername());
		
		return user;
	}
	
	public User getEntity(UserDTO request) {
		User user = new User();
		
		user.getId();
		user.getUsername();
		
		return user;
	}
}
