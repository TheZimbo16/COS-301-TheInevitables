package com.The_Inevitables.NavUP.web.api.transformer.navigation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.navigation.Navigation;
import com.The_Inevitables.NavUP.web.api.dto.navigation.NavigationDTO;
import com.The_Inevitables.NavUP.web.api.dto.user.UserDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;

@Stateless
@LocalBean
public class NavigationTransformer implements ApiTransformer {

	public NavigationDTO toDTO(Navigation navigation)
	{
		NavigationDTO navigationDTO = new NavigationDTO();
		
		navigationDTO.setLocationCoordinates(navigation.getLocationCoordinates());
		navigationDTO.setLocationName(navigation.getLocationName());
	
		
		return navigationDTO;
	}
	
	public Navigation toEntity(NavigationDTO request) 
	{
		Navigation navigation = new Navigation();
		
		navigation.setLocationName(request.getLocationName());
		navigation.setLocationCoordinates(request.getLocationCoordinates());
		
		return navigation;
	}
	
	public Navigation getEntity(UserDTO request) {
		Navigation navigation = new Navigation();
		
		navigation.getLocationCoordinates();
		navigation.getLocationName();

		return navigation;
	}
}
