package com.epiuse.tenderplatform.web.api.dto;

import java.io.Serializable;

public class ApiError implements Serializable {

	private static final long serialVersionUID = 7265918923116248291L;

	private String message;

	public ApiError() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
