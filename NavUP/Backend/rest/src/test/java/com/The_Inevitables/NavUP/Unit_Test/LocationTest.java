package com.The_Inevitables.NavUP.Unit_Test;

import org.junit.Assert;
import org.junit.Test;

import com.The_Inevitables.NavUP.model.Location;

public class LocationTest {

	@Test
	public void TestLocation()
	{
		Location location = new Location();
		
		location.setBuildingId(1);
		location.setFloorNumber(4);
		location.setLocationId(1);
		location.setLocationTypeId(2);
		location.setRoomNumber(24);
		location.setTagId("tag 1");
		
		
		Assert.assertEquals( 1, location.getBuildingId());
		Assert.assertEquals( 4,location.getFloorNumber());
		Assert.assertEquals( 1, location.getLocationId());
		Assert.assertEquals( 2, location.getLocationTypeId());
		Assert.assertEquals( 24, location.getRoomNumber());
		Assert.assertEquals( "tag 1", location.getTagId());
	}
}
