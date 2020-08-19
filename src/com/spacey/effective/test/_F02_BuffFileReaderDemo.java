package com.spacey.effective.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class _F02_BuffFileReaderDemo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try (BufferedReader locFile = new BufferedReader(new FileReader("locations_big.txt"));
				BufferedWriter newLocFile = new BufferedWriter(new FileWriter("locations2.txt"))) {
			String i;
			while ((i = locFile.readLine()) != null) {
				newLocFile.write(i + "\n");
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
