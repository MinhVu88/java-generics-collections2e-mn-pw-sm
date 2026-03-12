package com.oreilly.naftalinwadlermarks.generics.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Tests for Lists3.addAll")
public class Lists3Test {
	@Test
	@DisplayName("Happy path: Add integers to empty list")
	void testAddAllViaPassingIntegersToEmptyList() {
		List<Integer> actualList = new ArrayList<>();
		Lists3.addAll(actualList, 1, 2, 3);
		assertEquals(Arrays.asList(1, 2, 3), actualList);
	}

	@Test
	@DisplayName("Happy path: Add strings to non-empty list")
	void testAddAllViaAppendingStringsToNonEmptyList() {
		List<String> actuaList = new ArrayList<>(Arrays.asList("control"));
		Lists3.addAll(actuaList, "is", "an", "illusion");
		assertEquals(Arrays.asList("control", "is", "an", "illusion"), actuaList);
	}

	@Test
	@DisplayName("Edge case: No varargs (empty) does nothing")
	void testAddAllWithNoVarargs() {
		List<Double> actuaList = new ArrayList<>(Arrays.asList(Math.PI));
		Lists3.addAll(actuaList);
		assertEquals(Arrays.asList(Math.PI), actuaList);
	}

	@Test
	@DisplayName("Edge case: Null list throws NullPointerException")
	void testAddAllViaNullList() {
		assertThrows(
			NullPointerException.class, 
			() -> Lists3.addAll(null, 15.6f)
		);
	}

	@Test
	@DisplayName("Edge case: Unmodifiable list throws UnsupportedOperationException")
	void testAddAllViaUnmodifiableList() {
		List<Byte> unmodifiableList = Collections.unmodifiableList(new ArrayList<>());
		assertThrows(
			UnsupportedOperationException.class, 
			() -> Lists3.addAll(unmodifiableList, Byte.MAX_VALUE)
		);
	}

	@Test
	@DisplayName("Nuance: Adding null varargs elements is allowed")
	void testAddAllViaNullVarargs() {
		List<Character> actualList = new ArrayList<>();
		Lists3.addAll(actualList, 't', null, 'm');
		assertEquals(Arrays.asList('t', null, 'm'), actualList);
	}

	@Test
	@DisplayName("Nuance: Passing array explicitly via varargs")
	void testAddAllViaPassingExplicitArrayAsVarargs() {
		List<Short> actualList = new ArrayList<>();
		Short[] shortArr = {Short.MIN_VALUE, Short.MAX_VALUE};
		Lists3.addAll(actualList, shortArr);
		assertEquals(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), actualList);
	}

	@Test
	@DisplayName("Implication: Works with chaining method calls")
	void testAddAllViaChainedMethodCalls() {
		List<Long> actualList = new ArrayList<>();
		Lists3.addAll(actualList, -1506L, 1997L);
		Lists3.addAll(actualList, 290988L);
		assertEquals(Arrays.asList(-1506L, 1997L, 290988L), actualList);
	}

	@ParameterizedTest
	@MethodSource("provideAddAllScenarios")
  @DisplayName("Various addAll scenarios with different types and states")
	void testAddAllViaVariousScenarios(
		List<Object> actualResult, 
		Object[] varargs, 
		List<Object> expectedResult
	) {
		// Raw types for flexibility
		if(actualResult instanceof List && varargs instanceof Boolean[]) {
			Lists3.addAll(actualResult, (Boolean[]) varargs);
			assertEquals(expectedResult, actualResult);
		} else if(actualResult instanceof List && varargs instanceof String[]) {
			Lists3.addAll(actualResult, (String[]) varargs);
			assertEquals(expectedResult, actualResult);
		}
	}

	private static Stream<Arguments> provideAddAllScenarios() {
		return Stream.of(
			Arguments.of(
				new ArrayList<>(Arrays.asList(true)), 
				new Boolean[] {false}, 
				Arrays.asList(true, false)
			),
			Arguments.of(
				new ArrayList<String>(), 
				new String[] {"look", "closer"}, 
				Arrays.asList("look", "closer")
			),
			Arguments.of(
				new ArrayList<>(), 
				new Object[] {}, 
				new ArrayList<>()
			)
		);
	}
}
