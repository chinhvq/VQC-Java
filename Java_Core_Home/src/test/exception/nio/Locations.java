package test.exception.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import test.exception.Location;

public class Locations implements Map<Integer, Location> {
	private static Map<Integer, Location> locations = new LinkedHashMap<>();

	public static void main(String[] args) throws IOException {
		// write binary file using Java.nio package, we will write both of location and
		// direct into 01 file locations.data and we write object via Serializable
		//however we still use newInputStream the Stream of Java.io package
		Path locPath = FileSystems.getDefault().getPath("locations_nio.dat");
		try (ObjectOutputStream locFile = new ObjectOutputStream(
				new BufferedOutputStream(Files.newOutputStream(locPath)))) {
			for (Location location : locations.values()) {
				locFile.writeObject(location);
			}
		}

		// write text file with Path instance in Java.nio package
		// Path locPath = FileSystems.getDefault().getPath("locations_big.txt");
		// Path dirPath = FileSystems.getDefault().getPath("directions_big.txt");
		// try (BufferedWriter locFile = Files.newBufferedWriter(locPath);
		// BufferedWriter dirFile = Files.newBufferedWriter(dirPath)) {
		// for (Location location : locations.values()) {
		// locFile.write(location.getLocationID() + "," + location.getDescription() +
		// "\n");
		// for (String direction : location.getExists().keySet()) {
		// dirFile.write(location.getLocationID() + "," + direction + "," +
		// location.getExists().get(direction)
		// + "\n");
		// }
		// }
		// }
	}

	static {
		// read from binary file (.dat) using java.nio package and ObjectInputStream
		// however we still use newInputStream the Stream of Java.io package
		Path locPath = FileSystems.getDefault().getPath("locations_nio.dat");
		try (ObjectInputStream locFile = new ObjectInputStream(
				new BufferedInputStream(Files.newInputStream(locPath)))) {
			boolean eof = true;
			while (eof) {
				try {
					Location location = (Location) locFile.readObject();
					locations.put(location.getLocationID(), location);
				} catch (IOException e) {
					eof = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// read text file using Java.NIO package with Path instance instead of File
		// instance in Java.io package
		// Path locPath = FileSystems.getDefault().getPath("locations_big.txt");
		// Path dirPath = FileSystems.getDefault().getPath("directions_big.txt");
		//
		// try (Scanner scanner = new Scanner(Files.newBufferedReader(locPath))) {
		// scanner.useDelimiter(",");
		// while (scanner.hasNext()) {
		// int loc = scanner.nextInt();
		// scanner.skip(scanner.delimiter());
		// String description = scanner.nextLine();
		// System.out.println("Imported loc: " + loc + " , " + description);
		// locations.put(loc, new Location(loc, description, null));
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// try (BufferedReader dirFile = Files.newBufferedReader(dirPath)) {
		// String input;
		// while ((input = dirFile.readLine()) != null) {
		// String[] data = input.split(",");
		// int loc = Integer.parseInt(data[0]);
		// String direction = data[1];
		// int destination = Integer.parseInt(data[2]);
		// System.out.println(loc + " , " + direction + " , " + destination);
		// Location location = locations.get(loc);
		// location.addExit(direction, destination);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// old code for initialization using ObjectInputStream in Java.io package
		// try (ObjectInputStream locFile = new ObjectInputStream(new
		// BufferedInputStream(new FileInputStream(
		// "locations_big_serializable.dat")))) {
		// boolean eof = true;
		// while (eof) {
		// try {
		// Location location = (Location) locFile.readObject();
		// System.out.println(
		// "Reading location " + location.getLocationID() + " , " +
		// location.getDescription());
		// System.out.println("Found " + location.getExists().size() + " exists");
		// locations.put(location.getLocationID(), location);
		// } catch (EOFException e) {
		// eof = false;
		// } catch (ClassNotFoundException e) {
		// System.out.println(e.getMessage());
		// }
		// }
		// } catch (IOException e) {
		// System.out.println(e.getMessage());
		// }
	}

	@Override
	public int size() {
		return locations.size();
	}

	@Override
	public boolean isEmpty() {
		return locations.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return locations.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return locations.containsValue(value);
	}

	@Override
	public Location get(Object key) {
		return locations.get(key);
	}

	@Override
	public Location put(Integer key, Location value) {
		return locations.put(key, value);
	}

	@Override
	public Location remove(Object key) {
		return locations.remove(key);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Location> m) {

	}

	@Override
	public void clear() {
		locations.clear();
	}

	@Override
	public Set<Integer> keySet() {
		return locations.keySet();
	}

	@Override
	public Collection<Location> values() {
		return locations.values();
	}

	@Override
	public Set<Entry<Integer, Location>> entrySet() {
		return locations.entrySet();
	}

	@Override
	public String toString() {
		return "Locations []";
	}

}
