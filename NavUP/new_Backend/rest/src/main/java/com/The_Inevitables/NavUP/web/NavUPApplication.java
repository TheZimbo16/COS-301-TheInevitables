package com.The_Inevitables.NavUP.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.The_Inevitables.NavUP.web.api.api_modules.user.UserAPI;
import com.The_Inevitables.NavUP.web.api.api_modules.geoJSON.FeatureAPI;
import com.The_Inevitables.NavUP.web.api.api_modules.geoJSON.GeoJSONAPI;
import com.The_Inevitables.NavUP.web.api.api_modules.building.BuildingAPI;
import com.The_Inevitables.NavUP.web.api.api_modules.coordinates.CoordinatesAPI;
import com.The_Inevitables.NavUP.web.api.api_modules.navigation.NavigationAPI;

@ApplicationPath("api")
public class NavUPApplication extends Application {

	public NavUPApplication() {
		super();
	}

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();

		// jackson json provider can be added here
		s.add(UserAPI.class);
		s.add(BuildingAPI.class);
		s.add(CoordinatesAPI.class);
		s.add(FeatureAPI.class);
		s.add(NavigationAPI.class);
		s.add(GeoJSONAPI.class);
		
		return s;

	}

}
