package highScoreClass_Driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HSSave {
	private File playerScoresFile = new File("Scores.txt");

	private String name;
	private int turns;
	private int highScoreCount = 0;
	private String[] highScoreNames = new String[5];
	private int[] highScores = new int[5];
	private boolean userWon;

	HSSave() {

	}

	public HSSave(String name, int turns) {
		this.name = name;
		this.turns = turns;
	}

	public void loadScores() throws IOException {
		highScoreCount = 0;

		if (!playerScoresFile.exists()) {
			return; // first run, nothing to load yet
		}

		BufferedReader reader = new BufferedReader(new FileReader(playerScoresFile));
		String line;

		while ((line = reader.readLine()) != null && highScoreCount < 5) {
			String[] parts = line.trim().split(" ");
			if (parts.length == 2) {
				highScores[highScoreCount] = Integer.parseInt(parts[0]);
				highScoreNames[highScoreCount] = parts[1];
				highScoreCount++;
			}
		}
		reader.close();
	}

	public void displayScores() {
		System.out.println("\nHigh Scores");
		System.out.println("---------------");
		if (highScoreCount == 0) {
			System.out.println("No high scores yet");
		}
		for (int i = 0; i < highScoreCount; i++) {
			System.out.println(highScores[i] + " - " + highScoreNames[i]);
		}
	}

	public void addHighScore() {
		int index;
		int insertAt;
		int lastSpot;

		// find the first score that is worse than the new one
		insertAt = highScoreCount;
		for (index = 0; index < highScoreCount; index++) {
			if (turns < highScores[index]) {
				insertAt = index;
				break;
			}
		}

		// list is full and the new score is worse than all of them
		if (insertAt >= 5) {
			return;
		}

		// shift everything below insertAt down one spot (drops #5 if full)
		if (highScoreCount < 5) {
			lastSpot = highScoreCount;
		} else {
			lastSpot = 4;
		}
		for (index = lastSpot; index > insertAt; index--) {
			highScores[index] = highScores[index - 1];
			highScoreNames[index] = highScoreNames[index - 1];
		}

		highScores[insertAt] = turns;
		highScoreNames[insertAt] = name;

		if (highScoreCount < 5) {
			highScoreCount++;
		}
	}

	public void saveScores() throws IOException {
		int index;
		BufferedWriter writer = new BufferedWriter(new FileWriter(playerScoresFile));

		for (index = 0; index < highScoreCount; index++) {
			writer.write(String.format("%d %s", highScores[index], highScoreNames[index]));
			writer.newLine();

		}

		writer.close();
	}

}
