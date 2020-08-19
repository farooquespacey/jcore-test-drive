package com.spacey.effective.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class _F05_ObjectIOStreamDemo {

	private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
	static {
		// read from file into hashmap
		try (ObjectInputStream locFile = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("locations5.dat")))) {
			boolean eof = false;
			try {
				while (!eof) {
					Location location = (Location) locFile.readObject();
					System.out.println("Read Location " + location.getLocationID() + " : " + location.getDescription());
					System.out.println("Found " + location.getExits().size() + " exits.");
					locations.put(location.getLocationID(), location);
				}
			} catch (EOFException eofe) {
				eof = true;
				System.out.println("EOF Exception");
			}
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound Exception");
		}
	}

	public static void main(String[] args) {
		// write into file from hashmap
		try (ObjectOutputStream locFile = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("locations5.dat")))) {
			for (Location location : locations.values()) {
				locFile.writeObject(location);
			}
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}

	}

}
