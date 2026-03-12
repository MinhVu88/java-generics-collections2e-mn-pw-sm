// Generic Types | pg 4
package com.oreilly.naftalinwadlermarks.generics.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Program1 {
	static String greet(List<String> words) {
		return words.get(0) + words.get(1);
	}

	public static void main(String[] args) {
		List<String> words = new ArrayList<String>();
		words.add("hello ");
		words.add("world!");
		System.out.println(greet(words));
	}
}