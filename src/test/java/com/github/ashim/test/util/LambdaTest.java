package com.github.ashim.test.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class LambdaTest {

	public void test() {
		List<String> strList = Arrays.asList("ashim", "niraj", "pukar");

		strList.forEach(str -> System.out.println(str));
		strList.forEach(System.out::println);
	}

	@Test
	public void main() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		System.out.print("Print all numbers : ");
		evaluate(list, (n) -> true);
		System.out.println();

		System.out.print("Print no numbers : ");
		evaluate(list, (n) -> false);
		System.out.println();

		System.out.print("Print even numbers : ");
		evaluate(list, (n) -> n % 2 == 0);
		System.out.println();

		System.out.print("Print odd numbers : ");
		evaluate(list, (n) -> n % 2 == 1);
		System.out.println();

		System.out.print("Print numbers greater than 5 : ");
		evaluate(list, (n) -> n > 5);
	}

	public void evaluate(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.print(n + " ");
			}
		}
	}
}
