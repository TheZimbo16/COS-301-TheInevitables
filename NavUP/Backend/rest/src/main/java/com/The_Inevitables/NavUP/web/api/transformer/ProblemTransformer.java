package com.The_Inevitables.NavUP.web.api.transformer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.ProblemStatement;
import com.The_Inevitables.NavUP.web.api.dto.problem.ProblemStatementDTO;

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
