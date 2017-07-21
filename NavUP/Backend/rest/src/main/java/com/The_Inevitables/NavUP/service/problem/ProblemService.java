package com.The_Inevitables.NavUP.service.problem;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.ProblemStatementDao;
import com.The_Inevitables.NavUP.model.ProblemStatement;

@Stateless
public class ProblemService  {
	
	@EJB
	ProblemStatementDao problemStatementDao;

	public ProblemStatement createProblemStatement(ProblemStatement entity) {
		return problemStatementDao.create(entity);
	}
	
	public ProblemStatement updateProblemStatement(ProblemStatement entity) {
		return problemStatementDao.update(entity);
	}
	
}
