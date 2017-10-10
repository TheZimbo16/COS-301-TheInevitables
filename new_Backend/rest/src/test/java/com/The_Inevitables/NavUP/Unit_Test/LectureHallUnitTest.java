package com.The_Inevitables.NavUP.Unit_Test;

import java.util.ArrayList;
import org.junit.Test;
import com.The_Inevitables.NavUP.model.LectureHall.LectureHall;
import junit.framework.Assert;

public class LectureHallUnitTest {
	
	@Test
	public void lectureHallUnitTest() {
		LectureHall lectureHall = new LectureHall();
		ArrayList<ArrayList<ArrayList<Float>>> coordinates = new ArrayList<ArrayList<ArrayList<Float>>>();
		ArrayList<ArrayList<Float>> temp = new ArrayList<ArrayList<Float>>();
		ArrayList<Float> coords = new ArrayList<Float>();
		
		coords.add((float) 28.2257196);
		coords.add((float) -25.7555512);
		coords.add((float) 0.0);

		temp.add(coords);
		coordinates.add(temp);
		
		lectureHall.setBuilding("Zoology");
		lectureHall.setLevel(2);
		lectureHall.setRoom_name("Room 2-15");
		lectureHall.setCoordinates(coordinates);
		
		Assert.assertEquals("Zoology", lectureHall.getBuilding());
		Assert.assertEquals(2, lectureHall.getLevel());
		Assert.assertEquals("Room 2-15", lectureHall.getRoom_name());
		Assert.assertEquals(coordinates, lectureHall.getCoordinates());
	}
}
