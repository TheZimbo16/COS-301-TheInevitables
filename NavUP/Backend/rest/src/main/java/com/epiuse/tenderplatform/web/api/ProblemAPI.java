package com.epiuse.tenderplatform.web.api;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.epiuse.tenderplatform.model.ProblemStatement;
import com.epiuse.tenderplatform.service.problem.ProblemService;
import com.epiuse.tenderplatform.web.api.dto.problem.ProblemStatementDTO;
import com.epiuse.tenderplatform.web.api.transformer.ProblemTransformer;

@Path("problems")
@RequestScoped
public class ProblemAPI {
	
	@EJB
	ProblemService problemService;
	
	@EJB
	ProblemTransformer problemStatementTransformer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProblemStatementDTO createProblem(ProblemStatementDTO request){
		ProblemStatement problemStatement = problemService.createProblemStatement(problemStatementTransformer.toEntity(request));
		return problemStatementTransformer.toDTO(problemStatement);
	}
	
}
