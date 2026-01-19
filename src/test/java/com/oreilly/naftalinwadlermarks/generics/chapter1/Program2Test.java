package com.oreilly.naftalinwadlermarks.generics.chapter1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Program2Test {
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void testGreetingsWithRawTypes() {
		List words = new ArrayList();
		words.add("hello ");
    words.add("world!");

		String expectedGreetings = "hello world!";
		String actualGreetings = Program2.greet(words);

		assertThat(actualGreetings)
			.as("greetings should be correctly concatenated")
			.isEqualTo(expectedGreetings)
			.hasSize(expectedGreetings.length())
			.isNotBlank();
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void testThrowingExceptionWhenListContainsWrongType() {
		List words = new ArrayList();
    words.add("hello ");
    words.add(13);

		assertThrows(
			ClassCastException.class, 
			() -> Program2.greet(words)
		);
	}
}
