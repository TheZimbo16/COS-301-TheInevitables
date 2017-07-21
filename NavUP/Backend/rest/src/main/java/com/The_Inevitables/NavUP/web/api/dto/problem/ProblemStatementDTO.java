package com.The_Inevitables.NavUP.web.api.dto.problem;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;

public class ProblemStatementDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = 1938248728652936986L;

	private String status;
	private String description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
