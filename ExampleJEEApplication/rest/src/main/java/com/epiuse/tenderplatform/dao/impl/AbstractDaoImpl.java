package com.epiuse.tenderplatform.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import com.epiuse.tenderplatform.model.SuperEntity;
import com.epiuse.tenderplatform.model.User;

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
	
	public E delete(E entity){
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		return entity;
	}
	

	 public List<User> findAllCustomers() { 
	        CriteriaQuery<User> criteria = em.getCriteriaBuilder().createQuery(User.class); 
	        criteria.from(User.class); 
	        return em.createQuery(criteria).getResultList(); 
	 }
	
	
}
