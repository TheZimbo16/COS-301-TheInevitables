package com.The_Inevitables.NavUP.web.api.transformer.geoJSON;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.GeoJSON.CRSName;
import com.The_Inevitables.NavUP.web.api.dto.crsNameDTO.CRSNameDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;

@Stateless
@LocalBean
public class CRSNameTransformer implements ApiTransformer {

	public CRSNameDTO toDTO(CRSName crsname){
		CRSNameDTO crsNameDTO = new CRSNameDTO();
		
		crsNameDTO.setCrsNameId(crsname.getCrsNameId());
		crsNameDTO.setName(crsname.getName());
		
		return crsNameDTO;
	}
	
	public CRSName toEntity(CRSNameDTO request) {
		CRSName crsName = new CRSName();
		
		crsName.setCrsNameId(request.getCrsNameId());
		crsName.setName(request.getName());
		
		return crsName;
	}
	

}
