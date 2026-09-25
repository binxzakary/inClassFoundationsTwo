package whatsNextGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NextGame {

	private File playerInfo = new File("highscores.txt");
	private Scanner scnr = new Scanner(System.in);

	private int[] userSequence = new int[5];
	private int[] keySequence = new int[5];

	// score keeping
	private String[] highScoreNames = new String[5];
	private int[] highScores = new int[5];
	private int highScoreCount = 0;

	// solo fields
	private int numbersRandomModifier = 5;
	private int startIndexRandom = 1;
	private static int noGame = 0;
	private int userScore;
	private boolean userWon;

	// solo string fields
	private String userName;
	private String objective;

	public NextGame() {
		noGame++;
		this.userName = "user";
		this.userScore = 0;
		this.userWon = false;
	}

	public void displayObjective() {
		this.objective = "Game: Who's Next \nObjective: Identify the Sequence of 5 numbers between 1 and 5 using the fewest turns. If \n"
				+ "you wish to quit guessing and give up, enter a ZERO for one of your guesses and the game \n"
				+ "will display the solution and quit. " + "\n GOOD LUCK!!!\n";
		System.out.println(this.objective);
	}

	public String getObjective() {
		return this.objective;
	}

	// fills the key with 1-5, each number used exactly once, in random order
	public void loadArray() {
		Random rand = new Random();
		int index;
		int randomNumber;
		boolean[] numberUsed = { false, false, false, false, false };

		for (index = 0; index < keySequence.length; index++) {
			randomNumber = rand.nextInt(numbersRandomModifier) + startIndexRandom;

			// number is already in the key, pick again for this same spot
			if (numberUsed[randomNumber - 1]) {
				index--;
				continue;
			}
			keySequence[index] = randomNumber;
			numberUsed[randomNumber - 1] = true;
		}
	}

	// returns false if the user entered a 0 (gave up), true otherwise
	// returns false if the user entered a 0 (gave up), true otherwise
	// works with or without spaces, so "12345" and "1 2 3 4 5" both work
	public boolean userGuessArrayFill() {
		int index;
		int count;
		int userNumber;
		char userChar;
		String userLine;
		boolean validGuess = false;
		boolean[] numberUsed = { false, false, false, false, false };

		while (validGuess != true) {
			userLine = scnr.nextLine();
			count = 0;
			validGuess = true;

			// start fresh each try
			for (index = 0; index < numberUsed.length; index++) {
				numberUsed[index] = false;
			}

			for (index = 0; index < userLine.length(); index++) {
				userChar = userLine.charAt(index);

				// skip spaces
				if (userChar == ' ') {
					continue;
				}

				if (userChar == '0') {
					return false;
				}

				// validate the range (this also catches letters)
				if (userChar < '1' || userChar > '5') {
					System.out.println("Your numbers can only be 1-5, Try again");
					validGuess = false;
					break;
				}

				userNumber = Character.getNumericValue(userChar);

				if (numberUsed[userNumber - 1]) {
					System.out.println("Already Guessed " + userNumber + ", Try again");
					validGuess = false;
					break;
				}

				// only store it if there is still room
				if (count < 5) {
					userSequence[count] = userNumber;
				}
				numberUsed[userNumber - 1] = true;
				count++;
			}

			if (validGuess == true && count != 5) {
				System.out.println("You need exactly 5 numbers, Try again\n");
				validGuess = false;
			}
		}
		return true;
	}

	// counts how many spots in the guess match the key
	public int countCorrect() {
		int index;
		int correct = 0;

		for (index = 0; index < keySequence.length; index++) {
			if (userSequence[index] == keySequence[index]) {
				correct++;
			}
		}
		return correct;
	}

	public void playGame() {
		int turn = 0;
		int correct;
		boolean keepPlaying = true;

		loadArray();

		while (keepPlaying) {
			turn++;
			System.out.print("== Turn " + turn + " == Number Sequence: \n");

			if (!userGuessArrayFill()) {
				System.out.println("\nYou gave up. Better luck next time!");
				keepPlaying = false;
				break;
			}

			correct = countCorrect();

			if (correct == keySequence.length) {
				this.userWon = true;
				this.userScore = turn;
				System.out.println("\nYou guessed the sequence in " + turn + " turns");
				keepPlaying = false;
			} else {
				System.out.println("Your placement of " + correct + " numbers are correct\n");
			}
		}
	}

	public void askUserName() {
		System.out.print("Enter your name: ");
		setUserName(scnr.nextLine().trim());
	}

	public void setUserName(String userName) {
		boolean validUserName = isValidName(userName);

		while (validUserName != true) {
			System.out.println("Your user name must only have letters, try again:");
			userName = scnr.nextLine().trim();
			validUserName = isValidName(userName);
		}

		if (userName.equals("")) {
			userName = "user";
		}
		this.userName = userName;
	}

	// checks every character, only true if ALL of them are letters
	private boolean isValidName(String name) {
		int index;

		for (index = 0; index < name.length(); index++) {
			if (!Character.isLetter(name.charAt(index))) {
				return false;
			}
		}
		return true;
	}

	// reads the saved top 5 from the file (file is saved already sorted)
	// reads the saved top 5 from the file (file is saved already sorted)
	public void loadScores() throws IOException {
		String line;
		String[] parts;

		highScoreCount = 0;

		if (!playerInfo.exists()) {
			return;
		}

		BufferedReader br = new BufferedReader(new FileReader(playerInfo));

		line = br.readLine();
		while (line != null && highScoreCount < 5) {
			parts = line.split(" ");

			// skip blank lines
			if (parts.length >= 2) {
				highScores[highScoreCount] = Integer.parseInt(parts[0]);
				highScoreNames[highScoreCount] = parts[1];
				highScoreCount++;
			}
			line = br.readLine();
		}
		br.close();
	}

	// puts the player's score into the top 5 if it belongs there (lower is better)
	public void addHighScore() {
		int index;
		int insertAt;
		int lastSpot;

		if (!userWon) {
			return;
		}

		// find the first score that is worse than the new one
		insertAt = highScoreCount;
		for (index = 0; index < highScoreCount; index++) {
			if (userScore < highScores[index]) {
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

		highScores[insertAt] = userScore;
		highScoreNames[insertAt] = userName;

		if (highScoreCount < 5) {
			highScoreCount++;
		}
	}

	public void saveScores() throws IOException {
		int index;
		BufferedWriter bw = new BufferedWriter(new FileWriter(playerInfo));

		for (index = 0; index < highScoreCount; index++) {
			bw.write(String.format("%d %s", highScores[index], highScoreNames[index]));
			bw.newLine();
		}

		bw.close();
	}

	public void showSolution() {
		int index;

		System.out.println("\nGame Number Sequence");
		System.out.println("---------------------");
		System.out.print("|");
		for (index = 0; index < keySequence.length; index++) {
			System.out.print(" " + keySequence[index] + " |");
		}
		System.out.println();
		System.out.println("---------------------");
	}

	public void showHighScores() {
		int index;

		System.out.println("\nHigh Scores");
		System.out.println("---------------");

		if (highScoreCount == 0) {
			System.out.println("No high scores yet");
		}
		for (index = 0; index < highScoreCount; index++) {
			System.out.println(highScores[index] + " - " + highScoreNames[index]);
		}
	}

	public String getName() {
		return this.userName;
	}

	public int getUserScore() {
		return this.userScore;
	}

	public boolean getUserWon() {
		return this.userWon;
	}

	public static int getNoGame() {
		return noGame;
	}

}