package com.The_Inevitables.NavUP.service.crsName;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.geoJSON.CRSNameDao;
import com.The_Inevitables.NavUP.model.GeoJSON.CRSName;


@Stateless
public class CRSNameService {

	 @EJB
	 CRSNameDao crsNameDao;
	    
	    public CRSName createCRSName(CRSName entity)
	    {
	    	return crsNameDao.create(entity);
	    }
	    
	    public CRSName getCrsName()
	    {
	    	return crsNameDao.getCrsName();
	    }
	    

}
