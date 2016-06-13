package com.github.ashim.test.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void regexTesting() {
		// String to be scanned to find the pattern.
		String line = "9841-542010";
		// String pattern = "^[\\w\\s][\\w\\s]+$"; // AlphaNumberic
		// String pattern = "^[a-zA-Z\\s][a-zA-Z\\s]+$"; // Alphabets
		// String pattern = "[<][/]?[\\w\\s]+[>]"; // Non readable character
		// String pattern = "^[\\d]+$"; // Numeric
		String pattern = "^([0-9()-]+){7,12}$";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(line);

		if (m.find()) {
			System.out.println("Found value: " + m.group(0));
			System.out.println("Found value: " + m.group(1));
			System.out.println("Found value: " + m.group(2));
			System.out.println("Found value: " + m.group(3));
			System.out.println("Found value: " + m.group(4));
		} else {
			System.out.println("NO MATCH");
		}
	}

}
