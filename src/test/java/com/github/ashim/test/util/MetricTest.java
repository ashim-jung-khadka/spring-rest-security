package com.github.ashim.test.util;

import org.junit.Test;

public class MetricTest {

	@Test
	public void testURL() {
		String url = "localhost:8080/spring-rest-security/user";
		// String url1 = "localhost:8080/spring-rest-security/user/test";

		String projectName = "spring-rest-security";

		Integer indexOf = url.indexOf(projectName) + projectName.length();
		String path = url.substring(indexOf);

		Integer lastIndexOf = path.indexOf("/", 1);

		path = (lastIndexOf > 0) ? path.substring(0, lastIndexOf) : path;

		System.out.println(path);
	}

}
