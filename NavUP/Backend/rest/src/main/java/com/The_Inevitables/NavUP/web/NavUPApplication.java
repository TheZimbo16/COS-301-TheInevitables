package com.The_Inevitables.NavUP.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.The_Inevitables.NavUP.web.api.UserAPI;
import com.The_Inevitables.NavUP.web.api.FeatureAPI;
import com.The_Inevitables.NavUP.web.api.GeoJSONAPI;
import com.The_Inevitables.NavUP.model.GeoJSON;
import com.The_Inevitables.NavUP.web.api.BuildingAPI;
import com.The_Inevitables.NavUP.web.api.CRSAPI;
import com.The_Inevitables.NavUP.web.api.CoordinatesAPI;
import com.The_Inevitables.NavUP.web.api.LocationTypeAPI;
import com.The_Inevitables.NavUP.web.api.LocationAPI;

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
		s.add(CRSAPI.class);
		s.add(GeoJSONAPI.class);
		return s;

	}

}
