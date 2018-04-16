package test.exception;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
	private static Map<Integer, Location> locations = new LinkedHashMap<>();

	public static void main(String[] args) throws IOException {
		// write file with ObjectOutputStream & writeObject() to write the Serializable
		// location class/object as the whole instead of write each field of Location
		// class one by one
		try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
				"D:\\LAP TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations_big_serializable.dat")))) {
			for (Location location : locations.values()) {
				locFile.writeObject(location);
			}
		}

		// using FileOutputStream to read text file as binary data
		// write both of location and exits at the same time to binary file (.dat)
		// DataOutputStream will auto handle the raw binary data for us
		// try (DataOutputStream locFile = new DataOutputStream(new
		// BufferedOutputStream(new FileOutputStream(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations_big.dat"))))
		// {
		//
		// for (Location location : locations.values()) {
		// locFile.writeInt(location.getLocationID());
		// locFile.writeUTF(location.getDescription());
		// System.out.println("Writing location: " + location.getLocationID() + " : " +
		// location.getDescription());
		// System.out.println("Writing " + location.getExists().size() + " exits.");
		// locFile.writeInt(location.getExists().size());
		// // writing the exits to binary file
		// for (String direction : location.getExists().keySet()) {
		// System.out.println("\t\t" + direction + " , " +
		// location.getExists().get(direction));
		// locFile.writeUTF(direction);
		// locFile.writeInt(location.getExists().get(direction));
		// }
		// }
		// }

		// try with resource J7
		// using FileWrite to work with text file as Text Stream (Unicode character)
		// try (BufferedWriter locFile = new BufferedWriter(new FileWriter(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations.txt"));
		// BufferedWriter directionFile = new BufferedWriter(new FileWriter(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\directions.txt")))
		// {
		// for (Location location : locations.values()) {
		// locFile.write(location.getLocationID() + " , " + location.getDescription() +
		// "\n");
		// for (String direction : location.getExists().keySet()) {
		// directionFile.write(location.getLocationID() + " , " + direction + " , "
		// + location.getExists().get(direction) + "\n");
		// }
		// }
		// }

		// try with finally before J7 and use FileWriter only, not use BufferedWriter
		// yet
		// FileWriter locFile = null;
		// try {
		// locFile = new FileWriter(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations.txt");
		// for (Location location : locations.values()) {
		// locFile.write(location.getLocationID() + " , " + location.getDescription() +
		// "\n");
		// }
		// } finally {
		// if (locFile != null) {
		// locFile.close();
		// }
		// }
	}

	static {
		// read binary of Serializable object with ObjectInputStream
		try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(
				"D:\\LAP TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations_big_serializable.dat")))) {
			boolean eof = true;
			while (eof) {
				try {
					Location location = (Location) locFile.readObject();
					System.out.println(
							"Reading location " + location.getLocationID() + " , " + location.getDescription());
					System.out.println("Found " + location.getExists().size() + " exists");
					locations.put(location.getLocationID(), location);
				} catch (EOFException e) {
					eof = false;
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// read binary file with DataInputStream
		// try (DataInputStream locFile = new DataInputStream(new
		// BufferedInputStream(new FileInputStream(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations_big.dat"))))
		// {
		// boolean eof = true;
		// while (eof) {
		// try {
		// Map<String, Integer> exists = new LinkedHashMap<>();
		// int locID = locFile.readInt();
		// String description = locFile.readUTF();
		// int numExits = locFile.readInt();
		// System.out.println("Reading location " + locID + " , " + description);
		// System.out.println("Found " + numExits + " exists");
		// for (int i = 0; i < numExits; i++) {
		// String direction = locFile.readUTF();
		// int destination = locFile.readInt();
		// exists.put(direction, destination);
		// System.out.println("\t\t" + direction + " , " + destination);
		// }
		// locations.put(locID, new Location(locID, description, exists));
		// } catch (EOFException e) {
		// eof = false;
		// }
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// read text file with BufferedReader
		// try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\locations_big.txt"))))
		// {
		// scanner.useDelimiter(",");
		// while (scanner.hasNextLine()) {
		// int loc = scanner.nextInt();
		// scanner.skip(scanner.delimiter());
		// String description = scanner.nextLine();
		// System.out.println("Imported location : " + loc + " : " + description);
		// Map<String, Integer> tempExit = new HashMap<>();
		// locations.put(loc, new Location(loc, description, tempExit));
		// }
		// System.out.println(locations);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//

		// read exits with BufferReader, default size of Buffer Reader is 8k
		// try (BufferedReader directionFile = new BufferedReader(new FileReader(
		// "D:\\LAP
		// TRINH\\code\\git_home\\Java-Core\\VQC-Java\\Java_Core_Home\\src\\test\\exception\\directions_big.txt")))
		// {
		//
		// String input;
		// while ((input = directionFile.readLine()) != null) {
		// String[] strData = input.split(",");
		// int loc = Integer.parseInt(strData[0]);
		// String direction = strData[1];
		// int destination = Integer.parseInt(strData[2]);
		// System.out.println(loc + " : " + direction + " : " + destination);
		// Location location = locations.get(loc);
		// location.addExit(direction, destination);
		// }
		// System.out.println(locations);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// Map<String, Integer> tempExists = new HashMap<>();
		// locations.put(0, new Location(0, "Java computer", tempExists));
		//
		// tempExists.put("W", 2);
		// tempExists.put("E", 3);
		// tempExists.put("S", 4);
		// tempExists.put("N", 5);
		// locations.put(1, new Location(1, "brick building", tempExists));
		//
		// tempExists = new HashMap<>();
		// tempExists.put("N", 5);
		// locations.put(2, new Location(2, "top of the hill", tempExists));
		//
		// tempExists = new HashMap<>();
		// tempExists.put("W", 1);
		// tempExists.put("W", 1);
		// locations.put(3, new Location(3, "small spring", tempExists));
		//
		// tempExists = new HashMap<>();
		// tempExists.put("N", 1);
		// tempExists.put("W", 2);
		// locations.put(4, new Location(4, "beside the stream", tempExists));
		//
		// tempExists = new HashMap<>();
		// tempExists.put("S", 1);
		// tempExists.put("W", 2);
		// locations.put(5, new Location(5, "in the forest", tempExists));
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
