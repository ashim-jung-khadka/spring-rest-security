package com.github.ashim.test.util;

import org.junit.Assert;
import org.junit.Test;

import com.github.ashim.persistence.common.utility.ServiceHelper;

public class ServiceTest {

	@Test
	public void testPasswordEncoder() {
		String password = ServiceHelper.passwordEncoder("ashim");

		Assert.assertNotNull(password);
		Assert.assertEquals(60, password.length());
	}

	@Test
	public void testRandomString() {
		String randomStr = ServiceHelper.randomString();

		Assert.assertNotNull(randomStr);
		Assert.assertEquals(36, randomStr.length());
	}
}
