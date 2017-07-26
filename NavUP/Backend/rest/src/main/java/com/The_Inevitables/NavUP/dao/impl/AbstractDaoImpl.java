package com.The_Inevitables.NavUP.dao.impl;

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
	
	public User verifyUser(int studentNo, String password)
	{
		User user = getUser(studentNo);
//		if(user != null)
//		{
//			if(user.getUserPassword().equals(password))
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		}
		
		return user;
	}
	
	public User getUser(int studentNo)
	{
		User user = null;
		
		try
		{
			Query query = em.createQuery("SELECT u FROM com.The_Inevitables.NavUP.model.Users u WHERE c.studentNumber = :p");
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
	
	
}
