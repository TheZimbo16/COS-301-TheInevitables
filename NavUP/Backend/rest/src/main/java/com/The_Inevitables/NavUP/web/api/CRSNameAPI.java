package com.The_Inevitables.NavUP.web.api;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("crsname")
@RequestScoped
public class CRSNameAPI {

	@EJB
	com.The_Inevitables.NavUP.service.crsName.CRSNameService crsNameService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.CRSNameTransformer crsNameTransformer;
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCRSName()
	{
		return crsNameService.getCrsName();
	}
}
