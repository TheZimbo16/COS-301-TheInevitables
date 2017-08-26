package com.epiuse.tenderplatform.service.user;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.epiuse.tenderplatform.dao.impl.ProblemStatementDao;
import com.epiuse.tenderplatform.dao.impl.UserDao;
import com.epiuse.tenderplatform.model.ProblemStatement;
import com.epiuse.tenderplatform.model.User;

@Stateless
public class UserService  {
	
	@EJB
	UserDao userDAO;

	public User createUser(User entity) {
		return userDAO.create(entity);
	}
	
	public User updateUser(User entity) {
		return userDAO.update(entity);
	}
	
	public User removeUser(User entity) {
		return userDAO.delete(entity);
	}
	
	public List<User >findAllUsers() {
		return userDAO.findAllCustomers();
	}
	
}
