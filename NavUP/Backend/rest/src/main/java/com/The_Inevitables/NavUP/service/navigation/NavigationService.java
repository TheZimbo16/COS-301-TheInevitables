package com.The_Inevitables.NavUP.service.navigation;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.NavigationDao;
import com.The_Inevitables.NavUP.model.Navigation;
import com.The_Inevitables.NavUP.model.User;


@Stateless
public class NavigationService {

    @EJB
    NavigationDao navigationDao;
    
    public Navigation createCoordinates(Navigation entity)
    {
    	return navigationDao.create(entity);
    }
    
    public Navigation getCoordinates(String identity){
		return navigationDao.getNav(identity);
	}

}
