package test.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaChallenge {

	public static void main(String[] args) {
		new Thread(() -> {
			String myString = "Let's split it up into an array";
			String[] parts = myString.split(" ");
			for (String str : parts) {
				System.out.print(str + "\t");
			}
		}).start();
		;
		System.out.println();
		System.out.println(everySecondChar("HelloWorld"));
		// final String source = "HelloWorld";
		Function<String, String> everySecondCharLambda = source -> {
			StringBuilder returnVal = new StringBuilder();
			for (int i = 0; i < source.length(); i++) {
				if (i % 2 == 1) {
					returnVal.append(source.charAt(i));
				}
			}
			return returnVal.toString();
		};
		System.out.println();
		System.out.println(everySecondCharLambda.apply("HelloWorld"));
		System.out.println(everySecondCharacter(everySecondCharLambda, "123456789"));

		Supplier<String> iLoveJava = () -> "I love Java";
		Supplier<String> iLoveJava2 = () -> {
			return "I love Java 2";
		};
		String supplierResult = iLoveJava.get();
		System.out.println(supplierResult);

		List<String> topName2015 = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack",
				"Charlie", "harry", "Jacob");
		System.out.println("============================");
		System.out.println("Use lambda only");
		List<String> upperFirstLetter = new ArrayList<>();
		topName2015.forEach(name -> upperFirstLetter.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
		upperFirstLetter.sort((name1, name2) -> name1.compareTo(name2));
		upperFirstLetter.forEach(name -> System.out.print(name + "\t"));

		System.out.println("\n============================");
		System.out.println("Using reference method Class:Method");
		upperFirstLetter.clear();
		topName2015.forEach(name -> upperFirstLetter.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
		upperFirstLetter.sort(String::compareTo);
		upperFirstLetter.forEach(System.out::print);

		System.out.println("\n============================");
		System.out.println("Using Stream");
		topName2015.stream().map(name -> name.substring(0, 1).toUpperCase() + name.substring(1)).sorted()
				.forEach(name -> System.out.print(name + "\t"));
		System.out.println("\n============================");
		System.out.print("Using Stream - Number of name with A character = \t");
		System.out.println(topName2015.stream().filter(name -> name.toUpperCase().startsWith("A")).count());
	}

	private static String everySecondCharacter(Function<String, String> everySecondCharLambda, String sourceString) {
		return everySecondCharLambda.apply(sourceString);
	}

	public static String everySecondChar(String source) {
		StringBuilder returnVal = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
			if (i % 2 == 1) {
				returnVal.append(source.charAt(i));
			}
		}
		return returnVal.toString();
	}
}
