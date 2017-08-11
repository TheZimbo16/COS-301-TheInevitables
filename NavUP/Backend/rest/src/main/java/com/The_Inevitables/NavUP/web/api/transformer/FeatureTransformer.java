package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import com.The_Inevitables.NavUP.model.Feature;
import com.The_Inevitables.NavUP.web.api.dto.feature.FeatureDTO;

@Stateless
@LocalBean
public class FeatureTransformer implements ApiTransformer {

	public FeatureDTO toDTO(Feature obj){
		FeatureDTO featureDTO= new FeatureDTO();
		
		featureDTO.setFeatureId(obj.getFeatureId());
		featureDTO.setType(obj.getType());
		featureDTO.setProperties(obj.getProperties());
		featureDTO.setGeometry(obj.getGeometry());
		
		
		return featureDTO;
	}
	
	public Feature toEntity(FeatureDTO request) {
		Feature feature = new Feature();
		
		feature.setFeatureId(request.getFeatureId());
		feature.setProperties(request.getProperties());
		feature.setType(request.getType());
		feature.setGeometry(request.getGeometry());
		
		return feature;
	}
	

}
