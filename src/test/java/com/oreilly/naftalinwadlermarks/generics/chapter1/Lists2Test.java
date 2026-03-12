package com.oreilly.naftalinwadlermarks.generics.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DisplayName("Tests for Lists2.toList")
public class Lists2Test {
	@Test
	@DisplayName("Happy path: Varargs with integers")
	void testToListViaIntegerVarargs() {
		List<Integer> actualResult = Lists2.toList(1, 2, 3);
		assertEquals(Arrays.asList(1, 2, 3), actualResult);
	}

	@Test
	@DisplayName("Happy path: Varargs with strings")
	void testToListViaStringVarargs() {
		List<String> actualResult = Lists2.toList("hello", "world");
		assertEquals(Arrays.asList("hello", "world"), actualResult);
	}

	@Test
	@DisplayName("Edge case: No varargs (empty) returns empty list")
	void testToListViaNoArguments() {
		List<Double> result = Lists2.toList();
		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Edge case: Passing null as array throws NullPointerException")
	void testToListViaNullArray() {
		assertThrows(
			NullPointerException.class, 
			() -> Lists2.toList((Short[]) null)
		);
	}

	@Test
	@DisplayName("Nuance: Single null vararg treated as list with null")
	void testToListViaSingleNullArg() {
		List<Long> actualResult = Lists2.toList((Long) null);
		assertEquals(Arrays.asList((Long) null), actualResult);
	}

	@Test
	@DisplayName("Nuance: Passing array explicitly via varargs")
	void testToListViaExplicitArray() {
		Character[] charArr = {'f', 's', 'o', 'c', 'i', 'e', 't', 'y'};
		List<Character> actualResult = Lists2.toList(charArr);
		assertEquals(Arrays.asList('f', 's', 'o', 'c', 'i', 'e', 't', 'y'), actualResult);
	}

	@Test
	@DisplayName("Implication: Returned list is mutable and independent")
	void testToListReturningMutableIndependentList() {
		List<Float> actualResult = Lists2.toList(23.7f, -8.91f);
		actualResult.add(15.6f);
		assertEquals(3, actualResult.size()); // mutability verified
	}

	@ParameterizedTest
	@MethodSource("provideVarargsScenarios")
	@DisplayName("Various varargs scenarios with different types")
	void testToListViaVariousVarargsScenarios(Object[] input, List<?> expectedResult) {
		// Using raw types for parameterization flexibility; in practice, parameterize generically if possible
		List<?> actualResult;

		if(input instanceof Byte[]) {
			actualResult = Lists2.toList((Byte[]) input);
		} else if(input instanceof Boolean[]) {
			actualResult = Lists2.toList((Boolean[]) input);
		} else {
			actualResult = Lists2.toList();
		}

		assertEquals(expectedResult, actualResult);
	}

	private static Stream<Arguments> provideVarargsScenarios() {
		return Stream.of(
			Arguments.of(
				new Byte[] {Byte.MAX_VALUE, Byte.MIN_VALUE}, 
				Arrays.asList(Byte.MAX_VALUE, Byte.MIN_VALUE)
			),
			Arguments.of(new Boolean[] {false, null, true}, Arrays.asList(false, null, true)),
			Arguments.of(new Object[] {}, new ArrayList<>())
		);
	}
}
