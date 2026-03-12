// Generic Methods and Varargs | pg 7
package com.oreilly.naftalinwadlermarks.generics.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Lists1 {
	/*
	- This method accepts an array of type T[] and returns a list of type List<T>, 
		and does so for any reference type T.
	- This is indicated by writing <T> at the beginning of the method declaration, 
		which declares T as a new type variable.
	- The variable T can be any legal Java identifier, but by convention, type variable 
		identifiers are usually single uppercase letters: T for Type, R for ReturnType, 
		U for a second type parameter if required, and so on.
	- A method that declares a type variable in this way is called a generic method.
	- The scope of the type variable T is local to the method itself; 
		it may appear in the method declaration, but not outside the method.			
	*/
	public static <T> List<T> toList(T[] arr) {
		List<T> list = new ArrayList<T>();

		for(T element : arr) {
			list.add(element);
		}

		return list;
	}

	public static void main(String[] args) {
		// boxing converts the int values 1, 2 & 3 to Integers
		List<Integer> integers = Lists1.toList(new Integer[] {1, 2, 3});
		List<String> words = Lists1.toList(new String[] {"eyyo", "wassup"});
		System.out.println("integers: " + integers + " | words: " + words);
	}
}
