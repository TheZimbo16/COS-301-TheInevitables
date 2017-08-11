package com.The_Inevitables.NavUP.Unit_Test;

import org.junit.Assert;
import org.junit.Test;

import com.The_Inevitables.NavUP.model.Building;

public class BuildingTest {
	
	@Test
	public void buildingTest()
	{
		Building building = new Building();
		
		building.setName("IT");
		
		Assert.assertEquals("IT", building.getName());
	}
}
