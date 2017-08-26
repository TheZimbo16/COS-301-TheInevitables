package com.The_Inevitables.NavUP.web.api;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.The_Inevitables.NavUP.model.CRS;
import com.The_Inevitables.NavUP.web.api.dto.crs.CRSDTO;

@Path("crs")
@RequestScoped
public class CRSAPI {
	
	@EJB
	com.The_Inevitables.NavUP.service.crs.CRSService crsService;
	
	@EJB
	com.The_Inevitables.NavUP.web.api.transformer.CRSTransformer crsTransformer;
	
	@GET 
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public CRS getCRS()
	{
		return crsService.getCRS();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CRSDTO createLocation(CRSDTO request)
	{
		CRS crs = crsService.createCRS(crsTransformer.toEntity(request));
		return crsTransformer.toDTO(crs);
	}
}
