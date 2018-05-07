package test.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("A1", "A10", "A100", "b90", "B1", "B10", "b105", "b100");
		list.forEach(string -> {
			if (string.toUpperCase().startsWith("B")) {
				System.out.print(string + "\t");
			}
		});

		List<String> sortedList = new ArrayList<>();
		list.forEach(string -> {
			if (string.toUpperCase().startsWith("B")) {
				sortedList.add(string);
			}
		});

		Collections.sort(sortedList);
		System.out.println("Sorted List without Streams");
		System.out.println(sortedList);

		System.out.println("Sorted List with Streams");
		list.stream().filter(string -> string.toUpperCase().startsWith("B")).sorted()
				.forEach(string -> System.out.print(string + "\t"));

		System.out.println("\nSorted List with Streams Collectors");
		List<String> listSorted = list.stream().filter(str -> str.toUpperCase().startsWith("B")).sorted()
				.collect(Collectors.toList());
		System.out.println(listSorted);

		System.out.println("\nSorted List with Streams Collectors into ArrayList in specified");
		ArrayList<String> arrayListSorted = list.stream().filter(str -> str.toUpperCase().startsWith("B")).sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		System.out.println(arrayListSorted);

		Stream<String> ioStream = Stream.of("I26", "I17", "I29", "071");
		Stream<String> inStream = Stream.of("N40", "N36", "I26", "I17", "I29", "071");
		Stream<String> concateStream = Stream.concat(ioStream, inStream);
		// System.out.println("\nNumber of items in concateStream = " +
		// concateStream.count());
		System.out.println("\n============================");
		System.out.println("\nNumber of non-duplicate items in concateStream = "
				+ concateStream.distinct().peek(string -> System.out.print(string + "\t")).count());

	}
}

// STREAMS - oracle defines it as a sequence of elements supporting sequential
// and parallel aggregate operations so what does that actually mean well in
// practice a stream is a set of object references the stream method which was
// added to the collections class in Java 8 creates a stream from a collection
// now each object reference in the stream corresponds to an object in the
// collection and the ordering of the object reference matches the ordering of
// the collection now when we want to use a stream that uses a collection as a
// source well as its source the stream method will always be the first call we
// make.
// so note that the source collectionwon't be changed when we work streams in
// fact any stream operations that we use have to meet two requirements first
// they must be non-interfering which means that they don't change the stream
// source in any way and secondly they must be stateless so the result of an
// operation can't depend on any state outside of the operation example of that
// would be that it can't depend on variable values in a previous step so each
// operation should be seen as an independent step that's operating on a stream
// argument

// Collector -> second version of the method which accepts or expects rather
// three arguments a supplier a by consumer accumulator and a by consumer
// combiner so these arguments that must be specific about how we want the items
// to be added to the result of collect the collect method for example let's say
// we wanted to end up with an arraylist rather than a list
// the arraylist colon colon new as the supplier which will construct a new
// arraylist for us by the way I went passing method references we use the name
// of the class arraylist in this case colon colon new when we want to pass the
// constructor the accumulator is the arraylist . add method that's how we'll
// add the items to the arraylist and finally the add all method is the combiner
// so the accumulator is used when the runtime needs to add a single version or
// a single item into the list the accumulator is used when the runtime needs to
// add a single item into a result the combiner is sometimes used to improve the
// efficiency of the operation if and when to do this is really up to the java
// runtime

