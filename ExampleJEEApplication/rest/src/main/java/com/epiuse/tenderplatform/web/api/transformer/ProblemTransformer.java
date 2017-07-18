package com.epiuse.tenderplatform.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.epiuse.tenderplatform.model.ProblemStatement;
import com.epiuse.tenderplatform.web.api.dto.problem.ProblemStatementDTO;

@LocalBean
@Stateless
public class ProblemTransformer implements ApiTransformer{

	public ProblemStatementDTO toDTO(ProblemStatement problemStatement){
		ProblemStatementDTO problemDTO = new ProblemStatementDTO();

		problemDTO.setDescription(problemStatement.getDescription());
		problemDTO.setId(problemStatement.getId());
		
		return problemDTO;
	}

	public ProblemStatement toEntity(ProblemStatementDTO request) {
		ProblemStatement problemStatement = new ProblemStatement();
		
		problemStatement.setDescription(request.getDescription());
		
		return problemStatement;
	}
}
