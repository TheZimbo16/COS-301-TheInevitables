package com.The_Inevitables.NavUP.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.The_Inevitables.NavUP.web.api.HelloWorldAPI;
import com.The_Inevitables.NavUP.web.api.ProblemAPI;
import com.The_Inevitables.NavUP.web.api.UserAPI;

@ApplicationPath("api")
public class NavUPApplication extends Application {

	public NavUPApplication() {
		super();
	}

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();

		// jackson json provider can be added here

		s.add(HelloWorldAPI.class);
		s.add(ProblemAPI.class);
		s.add(UserAPI.class);
		
		return s;

	}

}
