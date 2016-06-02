package com.github.ashim.test.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.ashim.persistence.common.utility.ServiceHelper;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest extends TestModel {

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
