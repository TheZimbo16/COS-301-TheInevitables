package com.epiuse.tenderplatform.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.epiuse.tenderplatform.web.api.HelloWorldAPI;
import com.epiuse.tenderplatform.web.api.ProblemAPI;
import com.epiuse.tenderplatform.web.api.UserAPI;

@ApplicationPath("api")
public class TenderPlatformApplication extends Application {

	public TenderPlatformApplication() {
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
