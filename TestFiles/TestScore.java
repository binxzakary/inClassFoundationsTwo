package TestFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestScore {

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Scores.txt"));
//		BufferedReader reader = new BufferedReader(new FileReader("Scores.txt"));
		Scanner scnr = new Scanner(System.in);

		int[] hsScore = new int[5];
		String[] hsNames = new String[5];

		System.out.println("enter your name");
		String name = scnr.nextLine();
		System.out.println("enter your score");
		String score = scnr.nextLine();

		load(hsNames, hsScore);
		showHighScores(hsNames, hsScore);

	}

	public static void load(String[] names, int[] scores) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Scores.txt"));
		String line = "";
		String[] parts;

		int highScoreCount = 0;

		line = reader.readLine();
		while (line != null && highScoreCount < 5) {
			parts = line.split(" ");

			if (parts.length >= 2) {
				scores[highScoreCount] = Integer.parseInt(parts[0]);
				names[highScoreCount] = parts[1];
				highScoreCount++;
			}
			line = reader.readLine();
		}
		reader.close();
	}

	public static void showHighScores(String[] hsName, int[] hsScore) {
		int index;
		int highScoreCount = 0;

		System.out.println("\nHigh Scores");
		System.out.println("---------------");

		if (highScoreCount == 0) {
			System.out.println("No high scores yet");
		}
		for (index = 0; index < highScoreCount; index++) {
			System.out.println(hsScore[index] + " - " + hsName[index]);
		}
	}

}
