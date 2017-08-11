package com.The_Inevitables.NavUP.service.crsName;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.CRSNameDao;
import com.The_Inevitables.NavUP.model.CRSName;


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
