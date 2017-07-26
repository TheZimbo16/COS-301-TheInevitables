package com.The_Inevitables.NavUP.service.user;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.ProblemStatementDao;
import com.The_Inevitables.NavUP.dao.impl.UserDao;
import com.The_Inevitables.NavUP.model.ProblemStatement;
import com.The_Inevitables.NavUP.model.User;

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
	
	public void removeUser(int studentNo) {
		userDAO.delete(studentNo);
	}
	
	public List<User >findAllUsers() {
		return userDAO.findAllCustomers();
	}
	public User getUser(int studentNo){
		return userDAO.getUser(studentNo);
	}
	
}
