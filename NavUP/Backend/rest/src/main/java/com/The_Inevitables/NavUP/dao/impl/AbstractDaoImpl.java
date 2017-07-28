package com.The_Inevitables.NavUP.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import com.The_Inevitables.NavUP.model.SuperEntity;
import com.The_Inevitables.NavUP.model.User;
import com.The_Inevitables.NavUP.model.LocationType;
import com.The_Inevitables.NavUP.model.Building;
import com.The_Inevitables.NavUP.model.CRS;
import com.The_Inevitables.NavUP.model.CRSName;
import com.The_Inevitables.NavUP.model.Coordinates;
import com.The_Inevitables.NavUP.model.Feature;
import com.The_Inevitables.NavUP.model.GeoJSON;
import com.The_Inevitables.NavUP.model.Location;

public abstract class AbstractDaoImpl <E extends SuperEntity> {

    @PersistenceContext
    protected EntityManager em;
	
	public E create(E entity){
		em.persist(entity);
		return entity;
	}
	
	public E update(E entity){
		em.merge(entity);
		return entity;
	}
	//=====================================================================================================================================
	//										BUILDING QUERIES
	//=====================================================================================================================================
	//finds and returns all buildings
	public List<Building> findAllBuildings()
	{
		CriteriaQuery<Building> criteria = em.getCriteriaBuilder().createQuery(Building.class); 
        criteria.from(Building.class); 
        return em.createQuery(criteria).getResultList(); 
	}
	
	public Building getBuildingByName(String buildingName)
	{
		Building building = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Building u WHERE u.buildingName = :p");
			query.setParameter("p", buildingName);
			building = (Building) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return building;
	}
	
	public Building getBuildingByAbrev(String abrev)
	{
		Building building = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Building u WHERE u.buildingAbreviation = :p");
			query.setParameter("p", abrev);
			building = (Building) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return building;
	}
	
	//removes a building
	public void deleteByName(String buildingName)
	{
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.Building u WHERE u.buildingName = :p");
			query.setParameter("p", buildingName).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteByAbrev(String buildingName)
	{
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.Building u WHERE u.buildingAbreviation = :p");
			query.setParameter("p", buildingName).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//======================================================================================================================================
	//										LOCATION QUERIES
	//======================================================================================================================================
	public void deleteLocationType(String locationTypeName)
	{
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.LocationType u WHERE u.locationDescription = :p");
			query.setParameter("p", locationTypeName).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<LocationType> getAllLocationTypes()
	{
		CriteriaQuery<LocationType> criteria = em.getCriteriaBuilder().createQuery(LocationType.class); 
        criteria.from(LocationType.class); 
        return em.createQuery(criteria).getResultList();
	}
	
	 public void deleteLocation(String locationName)
	 {
		 try
			{
				Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.Location u WHERE u.locationName = :p");
				query.setParameter("p", locationName).executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	 
	 public List<Location> getAllLocations()
	 {
		 CriteriaQuery<Location> criteria = em.getCriteriaBuilder().createQuery(Location.class); 
	     criteria.from(Location.class); 
	     return em.createQuery(criteria).getResultList(); 
	 }
	//======================================================================================================================================
	//											USER QUERIES
	//======================================================================================================================================
	public void delete(int studentNo){
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.User u WHERE u.studentNumber = :p");
			query.setParameter("p", studentNo).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean verifyUser(int studentNo, String password)
	{
		User user = getUser(studentNo);
		if(user != null)
		{
			if(user.getUserPassword().equals(password))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
	
	public User getUser(int studentNo)
	{
		User user = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.User u WHERE u.studentNumber = :p");
			query.setParameter("p", studentNo);
			user = (User) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	 public List<User> findAllCustomers() { 
	        CriteriaQuery<User> criteria = em.getCriteriaBuilder().createQuery(User.class); 
	        criteria.from(User.class); 
	        return em.createQuery(criteria).getResultList(); 
	 }
	 //======================================================================================================================================
	 //											Feature QUERIES
	 //======================================================================================================================================

	 public List<Feature> findAllObjects() { 
		List<Feature> obj = null;
			try
			{
				Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Feature u");
				obj = query.getResultList();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return obj;
	 }
	 
	 
	 
	 
	 //======================================================================================================================================
	 // 										Coordinates Queries
	 //======================================================================================================================================
	 
	public List<Coordinates> findAllCoordinates()
	{
		CriteriaQuery<Coordinates> criteria = em.getCriteriaBuilder().createQuery(Coordinates.class); 
        criteria.from(Coordinates.class); 
        return (List<Coordinates>) em.createQuery(criteria).getSingleResult(); 
	}
	 
	 
	 
	 //======================================================================================================================================
	 // 										CRSName Queries
	 //======================================================================================================================================
	public String getCrsName()
    {
		CriteriaQuery<CRSName> criteria = em.getCriteriaBuilder().createQuery(CRSName.class); 
        criteria.from(CRSName.class); 
        return em.createQuery(criteria).getSingleResult().toString();
    }
	
	
	
	//======================================================================================================================================
	// 										CRS Queries
	//======================================================================================================================================
	public CRS getCRS()
	{
		CriteriaQuery<CRS> criteria = em.getCriteriaBuilder().createQuery(CRS.class); 
        criteria.from(CRS.class); 
        return em.createQuery(criteria).getSingleResult();
	}
	
	//======================================================================================================================================
	// 										GeoJSON Queries
	//======================================================================================================================================
	public GeoJSON findAllGeoJSON()
	{
		CriteriaQuery<GeoJSON> criteria = em.getCriteriaBuilder().createQuery(GeoJSON.class); 
        criteria.from(GeoJSON.class); 
        return (GeoJSON) em.createQuery(criteria).getSingleResult();
	}
	
}
