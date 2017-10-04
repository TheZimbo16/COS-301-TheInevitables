package com.The_Inevitables.NavUP.web.api.transformer.lectureHall;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.model.LectureHall.LectureHall;
import com.The_Inevitables.NavUP.web.api.dto.lectureHall.LectureHallDTO;
import com.The_Inevitables.NavUP.web.api.transformer.api.ApiTransformer;


@Stateless
@LocalBean
public class LectureHallTransformer implements ApiTransformer {

	public LectureHallDTO toDTO(LectureHall request) {
		LectureHallDTO lectureHallDTO = new LectureHallDTO();
		
		lectureHallDTO.setBuilding(request.getBuilding());
		lectureHallDTO.setLevel(request.getLevel());
		lectureHallDTO.setRoom_name(request.getRoom_name());
		lectureHallDTO.setCoordinates(request.getCoordinates());
		
		return lectureHallDTO;
	}
	
	public LectureHall toEntity(LectureHallDTO request) {
		LectureHall lectureHall = new LectureHall();
		
		lectureHall.setBuilding(request.getBuilding());
		lectureHall.setLevel(request.getLevel());
		lectureHall.setRoom_name(request.getRoom_name());
		lectureHall.setCoordinates(request.getCoordinates());
		
		return lectureHall;
	}
}
