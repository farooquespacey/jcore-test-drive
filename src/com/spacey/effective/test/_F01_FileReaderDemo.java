package com.spacey.effective.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class _F01_FileReaderDemo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try (FileReader locFile = new FileReader("locations_big.txt");
				FileWriter newLocFile = new FileWriter("locations1.txt")) {
			int i;
			while ((i = locFile.read()) != -1) {
				newLocFile.write(i);
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
