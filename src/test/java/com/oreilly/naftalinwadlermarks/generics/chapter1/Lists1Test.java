package com.oreilly.naftalinwadlermarks.generics.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Tests for Lists1.toList")
public class Lists1Test {
	@Test
	@DisplayName("Happy path: Convert non-empty integer array to list")
	void testToListViaIntegerArray() {
		// Arrange
		Integer[] input = {1, 2, 3};

		// Act
		List<Integer> actualResult = Lists1.toList(input);

		// Assert
		assertEquals(3, actualResult.size());
		assertEquals(1, actualResult.get(0));
		assertEquals(3, actualResult.get(2));
		assertEquals(List.of(1, 2, 3), actualResult);
	}

	@Test
	@DisplayName("Happy path: Convert non-empty string array to list")
	void testToListViaStringArray() {
		String[] input = {"eyyo", "wassup"};

		List<String> actualResult = Lists1.toList(input);

		assertEquals(2, actualResult.size());
		assertEquals("wassup", actualResult.get(1));
		assertEquals(List.of("eyyo", "wassup"), actualResult);
	}

	@Test
	@DisplayName("Edge case: Empty array returns empty list")
	void testToListViaEmptyArray() {
		Double[] input = {};

		List<Double> result = Lists1.toList(input);

		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Edge case: Null array throws NullPointerException")
	void testToListViaNullArray() {
		assertThrows(NullPointerException.class, () -> Lists1.toList(null));
	}

	@Test
	@DisplayName("Nuance: Array with null elements is allowed")
	void testToListViaNullElements() {
		Character[] input = {'s', null, 'x'};

		List<Character> actualResult = Lists1.toList(input);

		assertEquals(3, actualResult.size());
		assertEquals(Arrays.asList('s', null, 'x'), actualResult);
		assertNull(actualResult.get(1));
	}

	@Test
	@DisplayName("Implication: Returned list is independent of original array")
	void testToListReturningIndependentList() {
		Byte[] input = {Byte.MIN_VALUE, Byte.MAX_VALUE};

		List<Byte> actualResult = Lists1.toList(input);

		input[1] = 125;

		assertEquals(Byte.MAX_VALUE, actualResult.get(1));
	}

	@ParameterizedTest
	@MethodSource("provideDifferentTypes")
	@DisplayName("Generics: Works with different types and sizes")
	<T> void testToListViaVariousTypesAndSizes(T[] input, List<T> expectedResult) {
		List<T> actualResult = Lists1.toList(input);
		assertEquals(expectedResult, actualResult);
	}

	private static Stream<Arguments> provideDifferentTypes() {
		return Stream.of(
			Arguments.of(new Boolean[] {true, false}, Arrays.asList(true, false)),
			Arguments.of(new Float[] {3.14f, 35e3f}, Arrays.asList(3.14f, 35e3f)),
			Arguments.of(new Object[] {}, new ArrayList<>())
		);
	}
}
