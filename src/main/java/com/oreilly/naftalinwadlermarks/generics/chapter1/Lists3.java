// Generic Methods and Varargs | pg 8
package com.oreilly.naftalinwadlermarks.generics.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Lists3 {
	@SafeVarargs
	public static <T> void addAll(List<T> list, T... arr) {
		for(T element : arr) {
			list.add(element);
		}
	}

	public static void main(String[] args) {
		List<Integer> integers = new ArrayList<Integer>(); 
		Lists3.addAll(integers, 1, 2); 
		Lists3.addAll(integers, new Integer[] { 3, 4 });
		System.out.println("integers: " + integers);
	}
}
