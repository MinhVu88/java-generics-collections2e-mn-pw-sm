package com.oreilly.naftalinwadlermarks.generics.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Program1Test {
	@Test
	void testGreetingsWithGenerics() {
		List<String> words = new ArrayList<>();
		words.add("hello ");
    words.add("world!");

		String expectedGreetings = "hello world!";
		String actualGreetings = Program1.greet(words);

		assertEquals(expectedGreetings, actualGreetings);
	}
}
