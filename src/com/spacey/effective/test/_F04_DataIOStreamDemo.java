package com.spacey.effective.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class _F04_DataIOStreamDemo {
	private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
	static {
		// read from file into hashmap
		try (DataInputStream locFile = new DataInputStream(
				new BufferedInputStream(new FileInputStream("locations4.dat")))) {
			boolean eof = false;
			while (!eof) {
				try {
					Map<String, Integer> exits = new LinkedHashMap<>();
					int locationId = locFile.readInt();
					String description = locFile.readUTF();
					int numExits = locFile.readInt();
					System.out.println("Read Location " + locationId + " : " + description);
					System.out.println("Found " + numExits + " exits.");
					for (int i = 0; i < numExits; i++) {
						String direction = locFile.readUTF();
						int destination = locFile.readInt();
						exits.put(direction, destination);
						System.out.println("\t\t" + direction + "," + destination);
					}
					locations.put(locationId, new Location(locationId, description, exits));
				} catch (EOFException e) {
					eof = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}

	public static void main(String[] args) {
		// write into file from hashmap
		try (DataOutputStream locFile = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("locations4.dat")))) {
			for (Location location : locations.values()) {
				locFile.writeInt(location.getLocationID());
				locFile.writeUTF(location.getDescription());
				System.out.println("Writing Location " + location.getLocationID() + " : " + location.getDescription());
				System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
				locFile.writeInt(location.getExits().size() - 1);
				for (String direction : location.getExits().keySet()) {
					if (!direction.equalsIgnoreCase("Q")) {
						System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
						locFile.writeUTF(direction);
						locFile.writeInt(location.getExits().get(direction));
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}
}
