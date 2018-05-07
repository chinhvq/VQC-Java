package test.lambda.funtionalprogramming;

import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

public class PredicateDemo {

	public static void main(String[] args) {
		IntPredicate greaterThan15 = i -> i > 15;
		IntPredicate lessThan50 = i -> i < 50;
		//chaining the predicate
		System.out.println(greaterThan15.and(lessThan50).test(45));
		
		System.out.println("Use random < 100");
		Random random = new Random();
		for(int i = 0; i < 10; i++) {
			System.out.print(random.nextInt(100) + "\t");
		}
		
		//using Supplier
		System.out.println("\nUsing supplier for random < 1000");
		Supplier<Integer> supplier = () -> random.nextInt(1000);
		for (int i = 0; i < 5; i++) {
			System.out.print(supplier.get() + "\t");
		}
	}

}
