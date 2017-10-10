package com.The_Inevitables.NavUP.Unit_Test;

import junit.framework.Assert;
import java.util.ArrayList;
import org.junit.Test;

import com.The_Inevitables.NavUP.model.stairs.Stairs;

public class StairsUnitTest {

	@Test
	public void stairsUnitTest() {
		Stairs stair = new Stairs();
		
		ArrayList<Float> coordinates = new ArrayList<Float>();
		coordinates.add((float) 28.2331212);
		coordinates.add((float) -25.7558568);
		coordinates.add( (float) 0.0);
		
		stair.setBuilding("Geography");
		stair.setLevel(1);
		stair.setPosition("Back");
		stair.setCoordinates(coordinates);
		
		Assert.assertEquals("Geography", stair.getBuilding());
		Assert.assertEquals(1, stair.getLevel());
		Assert.assertEquals("Back", stair.getPosition());
		Assert.assertEquals(coordinates, stair.getCoordinates());
	}
}
