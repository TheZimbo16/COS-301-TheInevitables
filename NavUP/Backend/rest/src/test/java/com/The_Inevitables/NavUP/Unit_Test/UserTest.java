package com.The_Inevitables.NavUP.Unit_Test;

import org.junit.Assert;
import org.junit.Test;

import com.The_Inevitables.NavUP.model.User;


public class UserTest {

	@Test
	public void TestUser()
	{
		User user = new User();
		
		user.setStudentNumber(123456);
		user.setStudentName("Rian");
		user.setUserSurname("van der Merwe");
		user.setUserPassword("1234abcd");
		user.setUserDisabled((short) 1);
		
		Assert.assertEquals(123456, user.getStudentNumber());
		Assert.assertEquals("Rian", user.getStudentName());
		Assert.assertEquals("van der Merwe", user.getUserSurname());
		Assert.assertEquals("1234abcd", user.getUserPassword());
		Assert.assertEquals((short) 1, user.getUserDisabled());
	}
	
	
}
