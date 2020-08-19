package com.spacey.effective.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class _F03_Scan_BuffReaderDemo {
	private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
	static {
		// read from file into hashmap
		try (Scanner locFile = new Scanner(new BufferedReader(new FileReader("locations_big.txt")));
				BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
			locFile.useDelimiter(",");
			while (locFile.hasNextLine()) {
				int loc = locFile.nextInt();
				locFile.skip(locFile.delimiter());
				String description = locFile.nextLine();
				System.out.println("Imported loc: " + loc + ": " + description);
				Map<String, Integer> tempExit = new HashMap<>();
				locations.put(loc, new Location(loc, description, tempExit));
			}

			String input;
			while ((input = dirFile.readLine()) != null) {
				String[] data = input.split(",");
				int loc = Integer.parseInt(data[0]);
				String direction = data[1];
				int destination = Integer.parseInt(data[2]);
				System.out.println(loc + ": " + direction + ": " + destination);
				Location location = locations.get(loc);
				location.addExit(direction, destination);
			}
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}

	public static void main(String[] args) {
		// write into file from hashmap
		long start = System.currentTimeMillis();
		try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations3.txt"));
				BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions3.txt"))) {
			for (Location location : locations.values()) {
				locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
				for (String direction : location.getExits().keySet()) {
					if (!direction.equalsIgnoreCase("Q")) {
						dirFile.write(location.getLocationID() + "," + direction + ","
								+ location.getExits().get(direction) + "\n");
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		System.out.println("Written successfully (" + elapsedTime + ")");
	}

}
