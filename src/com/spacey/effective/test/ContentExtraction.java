package com.spacey.effective.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ContentExtraction {
	private File inputFile;
	private String startTag;
	private String endTag;
	private Set<String> results = new HashSet<String>();

	public ContentExtraction(File inputFile) {
		this.inputFile = inputFile;
	}

	private void calculateTagsRequired(String tag) {
		System.out.println("Root tag is set as " + tag);
		startTag = tag;
		endTag = tag.replaceFirst("<", "</");
	}

	public String getDataByLineNumber(int lineNo) throws FileNotFoundException {
		int currLine = 0;
		String data = "";
		StringBuilder dataToWorkOn = new StringBuilder();
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(inputFile)))) {
			while (sc.hasNextLine()) {
				data = sc.nextLine().trim();
				currLine += 1; // trace lines
				if (currLine < lineNo) {
					continue;
				}
				if (currLine == lineNo) {
					calculateTagsRequired(data);
				}
				dataToWorkOn.append(data);
				if (data.contains(endTag)) {
					break;
				}
			}
		}
		return dataToWorkOn.toString();
	}

	public String getDataByTag(String tag) throws FileNotFoundException {
		StringBuilder dataToWorkOn = new StringBuilder();
		String data = "";
		boolean validDataStarted = false;
		calculateTagsRequired("<" + tag + ">");
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(inputFile)))) {
			while (sc.hasNextLine()) {
				data = sc.nextLine().trim();
				if (data.contains(startTag) || validDataStarted) {
					validDataStarted = true;
					dataToWorkOn.append(data);
					if (data.contains(endTag)) {
						validDataStarted = false;
						break;
					}
				} else {
					continue;
				}
			}
		}
		return dataToWorkOn.toString();
	}

	public Set<String> extractFirstChild(String dataToExtractChild) {
		String tmp = "";
		Queue<String> allTags = new LinkedList<String>();
		StringBuilder completeTag = new StringBuilder();
		// exclude the start and end tag since we don't need it
		String finalData = dataToExtractChild.replace(startTag, "").replace(endTag, "");
		// exclude the complex children(child-with-children) after recording it
		try (Scanner sc = new Scanner(finalData)) {
			sc.useDelimiter("");
			while (sc.hasNext()) {
				String ch = sc.next();
				if (ch.equals(">")) {
					completeTag.append(ch);
					allTags.add(completeTag.toString());
					// emptying completeTag
					completeTag.setLength(0);
				} else {
					completeTag.append(ch);
				}
			}
		}
		while (!allTags.isEmpty()) {
			String data = allTags.remove();
			if (!tmp.isEmpty()) {
				String dataToPeekTheEnd = data.replaceAll("/", "");
				if (tmp.equals(dataToPeekTheEnd)) {
					tmp = "";
				}
				continue;
			}
			results.add(data);
			if (!data.contains("/>")) {
				tmp = data;
			}
		}
		return results;
	}

	public void displayChildren(String input) throws NumberFormatException, FileNotFoundException {
		String dataToWorkOn = "";
		if (input.matches("\\d+")) {
			System.out.println("Line number is the input");
			dataToWorkOn = getDataByLineNumber(Integer.parseInt(input));
		} else {
			System.out.println("Tag is the input");
			dataToWorkOn = getDataByTag(input);
		}
		System.out.println("Data to work on: " + dataToWorkOn);
		Set<String> results = extractFirstChild(dataToWorkOn);
		System.out.println("Children: ");
		System.out.println("=========");
		for (String ch : results) {
			System.out.print(ch.replaceAll("<", "").replaceAll(">", "").replaceAll("/", "") + ", ");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ContentExtraction xmlXtr = new ContentExtraction(new File("input.dat"));
		System.out.println("Enter the tag name OR line number to extract first children set: ");
		Scanner inp = new Scanner(System.in);
		String input = inp.nextLine();// input can be line number(2,8,1,etc) or tag(parent2, parent1, etc)
		inp.close();
		xmlXtr.displayChildren(input);
	}
}
