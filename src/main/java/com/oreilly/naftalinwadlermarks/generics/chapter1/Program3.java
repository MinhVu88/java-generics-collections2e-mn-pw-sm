// Primitive and Reference Types | pg 9
package com.oreilly.naftalinwadlermarks.generics.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Program3 {
	public static int sum0(List<Integer> integers) { 
		int total = 0;
		
		for(int number : integers) { 
			total += number; 
		} 
		
		return total;
	}

	public static Integer sum1(List<Integer> integers) { 
		Integer total = 0;
		
		for(Integer number : integers) { 
			total += number; 
		} 
		
		return total;
	}

	public static void main(String[] args) {
		List<Integer> integers0 = new ArrayList<>();
		integers0.add(8);
		System.out.println("integers0.getFirst() => " + integers0.getFirst());

		List<Integer> integers1 = new ArrayList<>();
		integers1.add(Integer.valueOf(7));
		System.out.println("integers1.get(0).intValue() => " + integers1.get(0).intValue());

		assert Integer.valueOf(5) == Integer.valueOf(5);
		assert Integer.valueOf(500) == Integer.valueOf(500);
	}
}
