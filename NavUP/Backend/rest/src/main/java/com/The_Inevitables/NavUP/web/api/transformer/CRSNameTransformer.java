package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.CRSName;
import com.The_Inevitables.NavUP.web.api.dto.crsNameDTO.CRSNameDTO;

@Stateless
@LocalBean
public class CRSNameTransformer {

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
