package com.The_Inevitables.NavUP.service.POI;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.POIDao;
import com.The_Inevitables.NavUP.dao.impl.ProblemStatementDao;
import com.The_Inevitables.NavUP.dao.impl.UserDao;
import com.The_Inevitables.NavUP.model.POI;
import com.The_Inevitables.NavUP.model.ProblemStatement;
import com.The_Inevitables.NavUP.model.User;

@Stateless
public class POIService  {
	
	@EJB
	POIDao poiDao;

	public POI createUser(POI entity) {
		return poiDao.create(entity);
	}
	
	public List<POI>findAllUsers() {
		return poiDao.findAllLocations();
	}

	
}
