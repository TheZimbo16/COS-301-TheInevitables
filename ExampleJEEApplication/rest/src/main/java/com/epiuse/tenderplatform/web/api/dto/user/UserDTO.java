package com.epiuse.tenderplatform.web.api.dto.user;

import com.epiuse.tenderplatform.web.api.dto.ApiResponse;
import com.epiuse.tenderplatform.web.api.dto.problem.DTO;

public class UserDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 1938248728652936986L;

	public String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



}
