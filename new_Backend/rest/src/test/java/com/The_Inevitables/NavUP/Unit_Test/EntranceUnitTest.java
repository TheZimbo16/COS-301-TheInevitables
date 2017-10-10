package com.The_Inevitables.NavUP.Unit_Test;

import java.util.ArrayList;
import org.junit.Test;
import com.The_Inevitables.NavUP.model.Entrances.Entrance;
import junit.framework.Assert;

public class EntranceUnitTest {

	@Test
	public void entranceUnitTest() {
		Entrance entrance = new Entrance();
		
		ArrayList<Float> coordinates = new ArrayList<Float>();
		coordinates.add((float) 28.2331212);
		coordinates.add((float) -25.7558568);
		coordinates.add( (float) 0.0);
		
		entrance.setName("IT Entrance 1");
		entrance.setDescription("null");
		entrance.setCoordinates(coordinates);
		
		Assert.assertEquals("IT Entrance 1", entrance.getName());
		Assert.assertEquals("null", entrance.getDescription());
		Assert.assertEquals(coordinates,entrance. getCoordinates());
	}
}
