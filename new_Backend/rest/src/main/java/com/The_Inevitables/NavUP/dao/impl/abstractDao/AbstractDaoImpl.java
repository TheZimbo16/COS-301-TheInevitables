package com.The_Inevitables.NavUP.dao.impl.abstractDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.The_Inevitables.NavUP.model.super_entity.SuperEntity;
import com.The_Inevitables.NavUP.model.user.User;
import com.The_Inevitables.NavUP.model.navigation.Navigation;
import com.The_Inevitables.NavUP.model.Entrances.Entrance;
import com.The_Inevitables.NavUP.model.LectureHall.LectureHall;
import com.The_Inevitables.NavUP.model.building.Building;
import com.The_Inevitables.NavUP.model.stairs.Stairs;

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
        return (List<Building>) em.createQuery(criteria).getResultList(); 
	}
	
	public Building getBuildingByName(String buildingName)
	{
		Building building = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Building u WHERE u.name = :p");
			query.setParameter("p", buildingName);
			building = (Building) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return building;
	}
	
	public ArrayList<ArrayList<ArrayList<Float>>> getBuildingCoordinates(String name) {
		Building building = getBuildingByName(name);
		if(building != null) {
			return building.getCoordinates();
		}
		
		return null;
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
	
	//======================================================================================================================================
	//											USER QUERIES
	//======================================================================================================================================
	public void delete(int studentNo) {
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
			if(user.checkMatch(password))
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
	//										Navigation QUERIES
	//======================================================================================================================================
	public Navigation getNav(String studentNo)
	{
		Navigation user = null;
		
		try
		{
			Query query = em.createQuery("SELECT c FROM com.The_Inevitables.NavUP.model.navigation.Navigation c WHERE c.locationName LIKE :custName");
			query.setParameter("custName", studentNo);
			user = (Navigation) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	//======================================================================================================================================
	//										Entrance QUERIES
	//======================================================================================================================================
	public void deleteEntranceByName(String name) {
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.Entrance u WHERE u.name = :p");
			query.setParameter("p", name).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Entrance> findAllEntrances() {
		CriteriaQuery<Entrance> criteria = em.getCriteriaBuilder().createQuery(Entrance.class); 
        criteria.from(Entrance.class); 
        return em.createQuery(criteria).getResultList(); 
	}
	
	public Entrance getEntranceByName(String name) {
		Entrance entrance = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Entrance u WHERE u.name = :p");
			query.setParameter("p", name);
			entrance = (Entrance) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return entrance;
	}
	
	public ArrayList<Float> getEntranceCoordiantes(String name) {
		Entrance entrance = getEntranceByName(name);
		
		if(entrance != null) {
			return entrance.getCoordinates();
		}
		
		return null;
	}
	
	//======================================================================================================================================
	//										LectureHall QUERIES
	//======================================================================================================================================
	public void deleteLectureHallByRoomName(String name) {
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.LectureHall u WHERE u.name = :p");
			query.setParameter("p", name).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<LectureHall> getAllLectureHalls() {
		CriteriaQuery<LectureHall> criteria = em.getCriteriaBuilder().createQuery(LectureHall.class); 
        criteria.from(LectureHall.class); 
        return em.createQuery(criteria).getResultList(); 
	}
	
	public LectureHall getByName(String name) {
		LectureHall lectureHall = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.LectureHall u WHERE u.name = :p");
			query.setParameter("p", name);
			lectureHall = (LectureHall) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lectureHall;
	}
	
	//======================================================================================================================================
	//										Stairs QUERIES
	//======================================================================================================================================
	
	public void deleteByStairName(String name) {
		try
		{
			Query query = em.createQuery("DELETE FROM com.The_Inevitables.NavUP.model.Stairs u WHERE u.name = :p");
			query.setParameter("p", name).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Stairs> getAllStairs() {
		CriteriaQuery<Stairs> criteria = em.getCriteriaBuilder().createQuery(Stairs.class); 
        criteria.from(Stairs.class); 
        return em.createQuery(criteria).getResultList();
	}
	
	public Stairs getByStairsName(String name) {
		Stairs stairs = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Stairs u WHERE u.name = :p");
			query.setParameter("p", name);
			stairs = (Stairs) query.getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return stairs;
	}
	
}
