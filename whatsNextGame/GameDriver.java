package whatsNextGame;

import java.io.IOException;

public class GameDriver {

	public static void main(String[] args) throws IOException {
		NextGame game = new NextGame();

		game.loadScores();
		game.askUserName();
		game.displayObjective();
		game.playGame();
		game.showSolution();
		game.addHighScore();
		game.saveScores();
		game.showHighScores();
	}

}