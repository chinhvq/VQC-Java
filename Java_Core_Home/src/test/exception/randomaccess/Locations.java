package test.exception.randomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
	private static Map<Integer, Location> locations = new LinkedHashMap<>();
	private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
	private static RandomAccessFile ra;

	public static void main(String[] args) throws IOException {
		// write location in random access mode
		try (RandomAccessFile rao = new RandomAccessFile("locations_ran.dat", "rwd")) {
			// 1. the first four bytes will contain the number of location (bytes 0-3)
			rao.writeInt(locations.size());
			// 2. the next four bytes will contain the start offset of the location section
			// (bytes 4 - 7) in this case is locationStart
			// + so each index record, will contain three integers. The location ID, the
			// offset for the location, and the size or length of the location record.
			// + we're calculating the index size, by multiplying the number of locations by
			// the number of INT contained in each record, in this case three. By the
			// number of bytes contained in an integer.
			int indexSize = locations.size() * 3 * Integer.BYTES;

			// we then calculate the current position of the file pointer to the index size
			// the end of that line will give us the offset for the location section which
			// is (locationStart). Here Integer.BYTES is the 1 byte off the ITEM2 - the
			// start offset of the location section
			int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
			rao.writeInt(locationStart);

			// We will load all the index of location into the memory in advance to reduce
			// the disk access in future. This way only efficient if the size of all index
			// is smaller than the size of location itself

			// ***what we're gonna do is write all the locations and then we'll write the
			// index as a whole.
			long indexStart = rao.getFilePointer();
			// we have calculate the offset of the locations data section we're gonna assign
			// that to a variable startPointer, which will update after writing each
			// location.
			int startPointer = locationStart;
			rao.seek(startPointer);
			// + then we'll look through the locations, write out each location. Create an
			// index for it. And add an index record to a map.
			for (Location location : locations.values()) {
				rao.writeInt(location.getLocationID());
				rao.writeUTF(location.getDescription());
				StringBuilder builder = new StringBuilder();
				for (String direction : location.getExists().keySet()) {
					builder.append(direction);
					builder.append(",");
					builder.append(location.getExists().get(direction));
					builder.append(",");
				}
				rao.writeUTF(builder.toString());

				// create IndexRecord in memory before write the IndexRecord
				IndexRecord record = new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));
				index.put(location.getLocationID(), record);

				// update start pointer.
				startPointer = (int) rao.getFilePointer();
			}

			// we seek for the offset that we save previously before we write the location
			// data then we start write all index to the random file
			rao.seek(indexStart);
			for (Integer locationID : index.keySet()) {
				rao.writeInt(locationID);
				rao.writeInt(index.get(locationID).getStartByte());
				rao.writeInt(index.get(locationID).getLength());
			}

		}
	}

	// Random Access File so we can load part of location into memory and we will
	// load more location when user reach to new location
	// 1. the first four bytes will contain the number of location (bytes 0-3)
	// 2. the next four bytes will contain the start offset of the location section
	// (bytes 4 - 7)
	// 3. the next section of file will contain the index the index is 1692 bytes
	// long
	// 4. the final section of file will contain the location record (the data)

	static {
		try {
			ra = new RandomAccessFile("locations_ran.dat", "rwd");
			int numLocations = ra.readInt();
			int locationStartPoint = ra.readInt();

			// we load the all of index from ra file into MEMORY
			while (ra.getFilePointer() < locationStartPoint) {
				int locationID = ra.readInt();
				int locationStart = ra.readInt();
				int locationLength = ra.readInt();

				IndexRecord record = new IndexRecord(locationStart, locationLength);
				index.put(locationID, record);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// whenever user select the location, our program will return location from
	// randomfile for user
	public Location getLocation(int locationID) throws IOException {
		IndexRecord record = index.get(locationID);
		System.out.println("Index record : " + record);
		ra.seek(record.getStartByte());
		int id = ra.readInt();
		String description = ra.readUTF();
		String exists = ra.readUTF();
		String[] existPart = exists.split(",");
		Location location = new Location(locationID, description, null);

		if (locationID != 0) {
			for (int i = 0; i < existPart.length; i++) {
				System.out.println("existPart = " + existPart[i]);
				System.out.println("existPart[+ 1] = " + existPart[i + 1]);
				String direction = existPart[i];
				int destination = Integer.parseInt(existPart[i + 1]);
				location.addExit(direction, destination);
			}
		}
		return location;
	}

	public void close() throws IOException {
		ra.close();
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
