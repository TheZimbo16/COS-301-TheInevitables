package com.The_Inevitables.NavUP.Unit_Test;

import java.util.ArrayList;
import org.junit.Test;
import com.The_Inevitables.NavUP.model.building.Building;
import junit.framework.Assert;

public class BuildingUnitTest {

	@Test
	public void buildingTest()
	{
		Building building = new Building();
		ArrayList<ArrayList<ArrayList<Float>>> coordinates = new ArrayList<ArrayList<ArrayList<Float>>>();
		ArrayList<ArrayList<Float>> temp = new ArrayList<ArrayList<Float>>();
		ArrayList<Float> coords = new ArrayList<Float>();
		
		coords.add((float) 28.2257196);
		coords.add((float) -25.7555512);
		coords.add((float) 0.0);

		temp.add(coords);
		coordinates.add(temp);
		
		building.setName("IT");
		building.setDescription("Information Technology Building");
		building.setCoordinates(coordinates);
		
		Assert.assertEquals("IT", building.getName());
		Assert.assertEquals("Information Technology Building", building.getDescription());
		Assert.assertEquals(coordinates, building.getCoordinates());
	}
}
