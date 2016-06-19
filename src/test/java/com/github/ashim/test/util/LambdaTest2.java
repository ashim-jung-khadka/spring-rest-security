package com.github.ashim.test.util;

import org.junit.Test;

public class LambdaTest2 {

	@Test
	public void main() {
		Runner runner = new Runner();
		runner.execute(new Executable() {
			@Override
			public void run(int num) {
				System.out.println("Inside Executable Interface");
				System.out.println("Number : " + num);
			}
		});

		System.out.println("---------------------------");

		String test = "Hello! World";
		test = "Testing";

		String hello = test;

		runner.execute((num) -> {
			System.out.println("Inside Executable Interface ");
			System.out.println("using Lambda Expression");
			System.out.println("Number : " + num);
			System.out.println(hello);
		});
	}
}

interface Executable {
	void run(int a);
}

class Runner {
	public void execute(Executable e) {
		System.out.println("Inside Runner");
		int num = 5;
		e.run(num);
	}
}