package com.oreilly.naftalinwadlermarks.generics.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Tests for Program3")
public class Program3Test {
	@Nested
	@DisplayName("Tests for sum0 (returns int) & sum1 (returns Integer)")
	class SumTests {
		@ParameterizedTest
    @MethodSource("provideSumInput")
    @DisplayName("sum0 | Happy path: Sum various lists correctly")
		void testSum0ViaVariousLists(List<Integer> input, int expectedResult) {
			int actualResult = Program3.sum0(input);
			assertEquals(expectedResult, actualResult);
		}

		@Test
		@DisplayName("sum0 | Edge case: Null list throws NullPointerException (NPE)")
		void testSum0ViaNullList() {
			assertThrows(NullPointerException.class, () -> Program3.sum0(null));
		}

		@Test
    @DisplayName("sum0 | Edge case: List with null element throws NPE on unboxing")
		void testSum0ViaListContainingNullElement() {
			assertThrows(
				NullPointerException.class, 
				() -> Program3.sum0(List.of(1, null, 3))
			);
		}

		@Test
		@DisplayName("sum0 | Boundary: Sum causing integer overflow wraps around (Java behavior)")
		void testSum0ViaIntegerOverflow() {
			List<Integer> input = List.of(Integer.MAX_VALUE, 1);
			int actualResult = Program3.sum0(input);
			int expectedResult = Integer.MIN_VALUE;
			assertEquals(expectedResult, actualResult); // Overflow: MAX + 1 -> MIN
		}

		@Test
		@DisplayName("sum0 | Implication: Does not modify input list")
		void testSum0ViaUnmodifiableInputList() {
			List<Integer> actualInput = new ArrayList<>(List.of(1, 2, 3));
			Program3.sum0(actualInput);
			assertEquals(List.of(1, 2, 3), actualInput);
		}

		@ParameterizedTest
    @MethodSource("provideSumInput")
    @DisplayName("sum1 | Happy path: Sum various lists correctly")
		void testSum1ViaVariousLists(List<Integer> input, Integer expectedResult) {
			Integer actualResult = Program3.sum1(input);
			assertEquals(expectedResult, actualResult);
		}

		@Test
		@DisplayName("sum1 | Edge case: Null list throws NullPointerException (NPE)")
		void testSum1ViaNullList() {
			assertThrows(NullPointerException.class, () -> Program3.sum1(null));
		}

		@Test
    @DisplayName("sum1 | Edge case: List with null element throws NPE on unboxing")
		void testSum1ViaListContainingNullElement() {
			assertThrows(
				NullPointerException.class, 
				() -> Program3.sum1(List.of(1, null, 3))
			);
		}

		@Test
		@DisplayName("sum1 | Boundary: Sum causing integer overflow wraps around (Java behavior)")
		void testSum1ViaIntegerOverflow() {
			List<Integer> input = List.of(Integer.MAX_VALUE, 1);
			Integer actualResult = Program3.sum1(input);
			assertEquals(Integer.valueOf(Integer.MIN_VALUE), actualResult);
		}

		@Test
		@DisplayName("sum1 | Implication: Does not modify input list")
		void testSum1ViaUnmodifiableInputList() {
			List<Integer> actualInput = new ArrayList<>(List.of(1, 2, 3));
			Program3.sum1(actualInput);
			assertEquals(List.of(1, 2, 3), actualInput);
		}

		static Stream<Arguments> provideSumInput() {
			return Stream.of(
				// Empty
				Arguments.of(new ArrayList<Integer>(), 0),
				// Single positive
				Arguments.of(Arrays.asList(new Integer[] {1}), 1),
				// Single negative
				Arguments.of(Arrays.asList(new Integer[] {-1}), -1),
				// Zero
				Arguments.of(Arrays.asList(new Integer[] {0}), 0),
				// Multiple positive
				Arguments.of(List.of(1, 2, 3), 6),
				// Mixed
				Arguments.of(Arrays.asList(new Integer[] {-1, 0, 1}), 0),
				// Min + Max = -1
				Arguments.of(List.of(Integer.MIN_VALUE, Integer.MAX_VALUE), -1)
			);
		}
	}

	@Nested
	@DisplayName("Tests for behaviors in main method: List operations")
	class ListBehaviorsInMainMethod {
		@Test
		@DisplayName("Behavior: Add int (autoboxed) and getFirst()")
		void testAddAutoboxedInt() {
			List<Integer> integers0 = new ArrayList<>();
			integers0.add(8);
			assertEquals(8, integers0.getFirst().intValue());
			assertEquals(Integer.valueOf(8), integers0.getFirst());
		}

		@Test
		@DisplayName("Behavior: Add Integer.valueOf and get(0).intValue()")
		void testAddIntegerValueOf() {
			List<Integer> integers1 = new ArrayList<>();
			integers1.add(Integer.valueOf(-7));
			assertEquals(-7, integers1.get(0).intValue());
		}

		@Test
		@DisplayName("Edge case: getFirst() on empty list throws NoSuchElementException")
		void testGetFirstViaEmptyList() {
			List<Integer> emptyList = new ArrayList<>();
			assertThrows(NoSuchElementException.class, emptyList::getFirst);
		}

		@Test
		@DisplayName("Nuance: intValue() on null Integer throws NullPointerException")
		void testIntValueViaNullInteger() {
			Integer nullInteger = null;
			assertThrows(NullPointerException.class, () -> nullInteger.intValue());
		}
	}

	@Nested
  @DisplayName("Tests for assertions in main method: Integer caching")
  class AssertionsInMainMethod {
		@Test
    @DisplayName("Assertion: Integer.valueOf(5) == Integer.valueOf(5) (cached, same object)")
		void testAssertIntegerValueOfViaSmallValueSameReference() {
			Integer value0 = Integer.valueOf(5);
			Integer value1 = Integer.valueOf(5);
			assertSame(value0, value1); // same due to caching
			assertEquals(value0, value1); // value equality always remains true
		}

		@Test
    @DisplayName("Assertion: Integer.valueOf(500) != Integer.valueOf(500) (not cached, different objects)")
		void testAssertIntegerValueOfViaLargeValueDifferentReferences() {
			Integer value0 = Integer.valueOf(500);
			Integer value1 = Integer.valueOf(500);
			assertNotSame(value0, value1); // not same due to being outside cache
			assertEquals(value0, value1); // but values are still equal to each other
		}

		@ParameterizedTest
    @MethodSource("provideCachingBoundaries")
    @DisplayName("Boundaries: Check caching for edge values")
		void testIntegerCachingWithBoundaries(int value, boolean isSameValue) {
			Integer val0 = Integer.valueOf(value);
			Integer val1 = Integer.valueOf(value);

			if(isSameValue) {
				assertSame(val0, val1);
			} else {
				assertNotSame(val0, val1);
			}
		}

		static Stream<Arguments> provideCachingBoundaries() {
			return Stream.of(
				Arguments.of(-128, true), // Cache min
        Arguments.of(127, true), // Cache max
        Arguments.of(-129, false), // Below
        Arguments.of(128, false), // Above
        Arguments.of(0, true), // Zero
        Arguments.of(-1, true) // Negative in range
			);
		}
	}
}
