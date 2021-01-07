package com.spacey.effective.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyEditor {

	public static void main(String[] args) {
		List<Character> output = new ArrayList<>();
		List<Input> styles = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("ACTION: ");
			while (sc.hasNextLine()) {
				String inputLine = sc.nextLine();
				if (inputLine.equals("COMPOSE"))
					break;
				Input inp = getData(inputLine, sc);
				processInput(inp, output);
				if (inp.getAction().equals("STYLE")) {
					styles.add(inp);
				}
				System.out.print("ACTION: ");
			}
		}
//		char[] output = new char[] { ' ', 'S', 'P', 'A', '\n', 'C', ' ', 'Y' };
		System.out.println("Array Out: " + output);
		StringBuilder sb = new StringBuilder();
		boolean textEncountered = false;
		sb.append("<line>");
		for (Character ch : output) {
			if (ch.equals('\n')) { // new line character
				if (textEncountered) {
					sb.append("</text>");
				}
				sb.append("</line>\n<line>");
				textEncountered = false;
			} else if (!Character.isSpaceChar(ch)) { // non space character
				if (!textEncountered) {
					sb.append("<text>");
					textEncountered = true;
				}
				sb.append(ch);
			} else { // space character
				if (textEncountered) {
					sb.append("</text> ");
				} else {
					sb.append(" ");
				}
				textEncountered = false;
			}
		}
		if (textEncountered) {
			sb.append("</text>");
		}
		sb.append("</line>");
		
		//TODO: handle style in the future
		// ...
		// ...
		
		System.out.println(sb.toString());
	}

	private static void processInput(Input inp, List<Character> output) {
		String action = inp.getAction();
		if (action.equals("INSERT")) {
			char[] dataArr = inp.getData().toCharArray();
			int outPos = inp.getPosition();
			if (inp.getData().contains("<space>")) {
				output.add(outPos, ' ');
			} else {
				for (int dataIdx = inp.getSelectionFrom(); dataIdx <= inp.getSelectionTo(); dataIdx++, outPos++) {
					output.add(outPos, dataArr[dataIdx]);
				}
			}
		} else if (action.equals("NEWLINE")) {
			int outPos = inp.getPosition();
			output.add(outPos, '\n');
		} else if (action.equals("PASTE")) {
			char[] dataArr = inp.getData().toCharArray();
			for (int dataIdx = inp.getSelectionFrom(); dataIdx <= inp.getSelectionTo(); dataIdx++) {
				output.add(dataArr[dataIdx]);
			}
		} else if (action.equals("DELETE")) {
			for (int dataIdx = inp.getSelectionFrom(); dataIdx <= inp.getSelectionTo(); dataIdx++) {
				output.set(dataIdx, ' ');
			}
		} else if (action.equals("STYLE")) {

		}
	}

	private static Input getData(String action, Scanner sc) {
		Input inp = new Input(action);
		switch (action) {
		case "INSERT":
			System.out.print("DATA: ");
			inp.setData(sc.nextLine());
			System.out.print("POSITION: ");
			inp.setPosition(sc.nextInt());
			System.out.print("SELECT_FROM: ");
			inp.setSelectionFrom(sc.nextInt());
			System.out.print("SELECT_TO: ");
			inp.setSelectionTo(sc.nextInt());
			sc.nextLine();
			break;
		case "NEWLINE":
			System.out.print("POSITION: ");
			inp.setPosition(sc.nextInt());
			sc.nextLine();
			break;
		case "PASTE":
			System.out.print("DATA: ");
			inp.setData(sc.nextLine());
			System.out.print("SELECT_FROM: ");
			inp.setSelectionFrom(sc.nextInt());
			System.out.print("SELECT_TO: ");
			inp.setSelectionTo(sc.nextInt());
			sc.nextLine();
			break;
		case "DELETE":
			System.out.print("SELECT_FROM: ");
			inp.setSelectionFrom(sc.nextInt());
			System.out.print("SELECT_TO: ");
			inp.setSelectionTo(sc.nextInt());
			sc.nextLine();
			break;
		case "STYLE":
			System.out.print("FONT_STYLE: ");
			inp.setFontStyle(sc.nextLine());
			System.out.print("FONT_SIZE: ");
			inp.setFontSize(sc.nextLine());
			System.out.print("COLOR: ");
			inp.setColor(sc.nextLine());
			System.out.print("SELECT_FROM: ");
			inp.setSelectionFrom(sc.nextInt());
			System.out.print("SELECT_TO: ");
			inp.setSelectionTo(sc.nextInt());
			sc.nextLine();
			break;
		}
		return inp;
	}

}

class Input {
	String action;
	String data;
	String fontStyle;
	String fontSize;
	String color;
	int position;
	int selectionFrom;
	int selectionTo;

	public Input() {
	}

	public Input(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSelectionFrom() {
		return selectionFrom;
	}

	public void setSelectionFrom(int selectionFrom) {
		this.selectionFrom = selectionFrom;
	}

	public int getSelectionTo() {
		return selectionTo;
	}

	public void setSelectionTo(int selectionTo) {
		this.selectionTo = selectionTo;
	}

	@Override
	public String toString() {
		return "Input [action=" + action + ", data=" + data + ", fontStyle=" + fontStyle + ", fontSize=" + fontSize
				+ ", color=" + color + ", position=" + position + ", selectionFrom=" + selectionFrom + ", selectionTo="
				+ selectionTo + "]";
	}

}
