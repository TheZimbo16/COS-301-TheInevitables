package com.The_Inevitables.NavUP.service.crs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.The_Inevitables.NavUP.dao.impl.CRSDao;
import com.The_Inevitables.NavUP.model.CRS;

@Stateless
public class CRSService {

	@EJB
	CRSDao crsDao;
	
	public CRS createCRS(CRS entity)
	{
		return crsDao.create(entity);
	}
	
	public CRS getCRS()
	{
		return crsDao.getCRS();
	}
	
	
}
