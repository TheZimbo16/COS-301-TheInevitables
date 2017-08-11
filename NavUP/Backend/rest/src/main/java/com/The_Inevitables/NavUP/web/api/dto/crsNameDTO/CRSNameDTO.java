package com.The_Inevitables.NavUP.web.api.dto.crsNameDTO;

import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class CRSNameDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = -8685850624553413208L;
	
	private int crsNameId;
	private String name;
	
	public int getCrsNameId() {
		return crsNameId;
	}

	public void setCrsNameId(int crsNameId) {
		this.crsNameId = crsNameId;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
