package com.adaptionsoft.games.trivia;

public class GameRunner {

	private boolean notAWinner;

	public void run() {
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		// determinist mode to build the golden master
		AppRandom rand = new AppRandom(true);

		do {
			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}

		} while (notAWinner);
	}
}
