package com.The_Inevitables.NavUP.web.api.dto.crs;

import com.The_Inevitables.NavUP.model.GeoJSON.CRSName;
import com.The_Inevitables.NavUP.web.api.dto.ApiResponse;
import com.The_Inevitables.NavUP.web.api.dto.problem.DTO;

public class CRSDTO extends DTO implements ApiResponse {

	private static final long serialVersionUID = -6219825258458733417L;

	private int crsId;
	private String type;
	private CRSName properties;
	public int getCrsId() {
		return crsId;
	}
	public void setCrsId(int crsId) {
		this.crsId = crsId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CRSName getProperties() {
		return properties;
	}
	public void setProperties(CRSName properties) {
		this.properties = properties;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
