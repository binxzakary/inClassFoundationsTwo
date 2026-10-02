package highScoreClass_Driver;

import java.io.IOException;
import java.util.Scanner;

public class HSRecord {
	Scanner scnr = new Scanner(System.in);

	private String userName;

	private String strTurns;
	private int turns;

	HSRecord() {
	}

	public HSRecord(String strTurns, String userString) {
		name(userString);
		turns(strTurns);
	}

	public void run() throws IOException {
		askUserName();
		System.out.println("How many turns did it take?");
		turns(scnr.nextLine().trim());
		setScore(userName, turns);
	}

	public int getTurns() {
		return turns;
	}

	public void turns(String strTurns) {
		this.strTurns = strTurns;
		this.turns = Integer.parseInt(strTurns);
	}

	public String getName() {
		return userName;
	}

	public void askUserName() {
		System.out.print("Enter your name: ");
		name(scnr.nextLine().trim());
	}

	public void name(String userName) {
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

	private boolean isValidName(String name) {
		int index;

		for (index = 0; index < name.length(); index++) {
			if (!Character.isLetter(name.charAt(index))) {
				return false;
			}
		}
		return true;
	}

	public void setScore(String name, int turns) throws IOException {
		this.userName = name;
		this.turns = turns;

		HSSave save = new HSSave(name, turns);
		save.loadScores();
		save.addHighScore();
		save.saveScores();
		save.displayScores();
	}

}