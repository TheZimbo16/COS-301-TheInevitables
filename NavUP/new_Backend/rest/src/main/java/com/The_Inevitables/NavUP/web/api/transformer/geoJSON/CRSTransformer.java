package com.The_Inevitables.NavUP.web.api.transformer.geoJSON;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.GeoJSON.CRS;
import com.The_Inevitables.NavUP.web.api.dto.crs.CRSDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;


@Stateless
@LocalBean
public class CRSTransformer implements ApiTransformer {
	
	public CRSDTO toDTO(CRS crs){
		CRSDTO crsDTO = new CRSDTO();
		
		crsDTO.setCrsId(crs.getCrsId());
		crsDTO.setProperties(crs.getProperties());
		crsDTO.setType(crs.getType());
		
		return crsDTO;
	}
	
	public CRS toEntity(CRSDTO request) {
		CRS crs = new CRS();
		
		crs.setCrsId(request.getCrsId());
		crs.setProperties(request.getProperties());
		crs.setType(request.getType());
		
		return crs;
	}
}
