package com.epiuse.tenderplatform.web.api;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("hello")
public class HelloWorldAPI {

	
	@GET
	@Produces({ MediaType.TEXT_PLAIN})
	public String getStuff(String s) {
		return "Works";
	}
}
