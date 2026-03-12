// Generic Methods and Varargs | pg 8
package com.oreilly.naftalinwadlermarks.generics.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Lists2 {
	public static <T> List<T> toList(@SuppressWarnings("unchecked") T... arr) {
		List<T> list = new ArrayList<T>();

		for(T element : arr) {
			list.add(element);
		}

		return list;
	}

	public static void main(String[] args) {
		List<Integer> integers0 = Lists2.toList(1, 2, 3);
		List<String> words0 = Lists2.toList("f**k", "society");
		System.out.println("integers0: " + integers0 + " | words0: " + words0);

		var integers1 = Lists2.<Integer>toList();
		integers1.add(4);
		integers1.add(5);
		var words1 = Lists2.<Object>toList(6, "se7en");
		System.out.println("integers1: " + integers1 + " | words1: " + words1);
	}
}
