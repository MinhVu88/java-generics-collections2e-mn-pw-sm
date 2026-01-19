// pg 5
package com.oreilly.naftalinwadlermarks.generics.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Program2 {
	static String greet(List<String> words) {
		return ((String) words.get(0)) + ((String) words.get(1));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		List words = new ArrayList();
		words.add("hello ");
		words.add("world!");
		System.out.println(greet(words));
	}
}
