package com.The_Inevitables.NavUP.service.lectureHall;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.The_Inevitables.NavUP.dao.impl.lectureHall.LectureHallDao;
import com.The_Inevitables.NavUP.model.GeoJSONFormatter.LectureHallGeoJson.LectureHallGeoJson;
import com.The_Inevitables.NavUP.model.LectureHall.LectureHall;
import com.google.gson.JsonObject;


@Stateless
public class LectureHallService {

	@EJB
	LectureHallDao lectureHallDao;
	
	LectureHallGeoJson geoJson = new LectureHallGeoJson();
	
	public LectureHall createLectureHall(LectureHall lectureHall) {
		return lectureHallDao.create(lectureHall);
	}
	
	public LectureHall updateLectureHall(LectureHall lectureHall) {
		return lectureHallDao.update(lectureHall);
	}
	
	public void deleteLectureHallByRoomName(String name) {
		lectureHallDao.deleteLectureHallByRoomName(name);
	}
	
	public List<LectureHall> getAllLectureHalls() {
		return lectureHallDao.getAllLectureHalls();
	}
	
	public LectureHall getLectureHallByRoomName(String name) {
		return lectureHallDao.getByName(name);
	}
	
	public JsonObject createLectureHallGeoJSON(LectureHall lectureHall) {
		return geoJson.createGeoJSON(lectureHall);
	}
	
	public JsonObject createLectureHallGeoJSON(Collection<LectureHall> lectureHalls) {
		return geoJson.createGeoJSON(lectureHalls);
	}

}
