package com.epiuse.tenderplatform.service.problem;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.epiuse.tenderplatform.dao.impl.ProblemStatementDao;
import com.epiuse.tenderplatform.model.ProblemStatement;

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
