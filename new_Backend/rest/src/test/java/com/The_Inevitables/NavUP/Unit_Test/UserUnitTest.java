package com.The_Inevitables.NavUP.Unit_Test;

import junit.framework.Assert;
import org.junit.Test;

import com.The_Inevitables.NavUP.model.user.User;

public class UserUnitTest {

	@Test
	public void userUnitTest() {
		User user = new User();
		
		user.setStudentName("Rian");
		user.setUserSurname("van der Merwe");
		user.setStudentNumber(12158512);
		user.setUserDisabled(false);
		user.setUserPassword("182AnV&J");
		
		Assert.assertEquals("Rian", user.getStudentName());
		Assert.assertEquals("van der Merwe", user.getUserSurname());
		Assert.assertEquals(12158512, user.getStudentNumber());
		Assert.assertEquals(false, user.getUserDisabled());
		Assert.assertEquals(true, user.checkMatch("182AnV&J"));
	}
}
