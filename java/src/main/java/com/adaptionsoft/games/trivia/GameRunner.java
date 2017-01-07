package com.adaptionsoft.games.trivia;

public class GameRunner {

	private boolean notAWinner;

	public void run(Players players) {
		Game aGame = new Game();

		// FIXME, next iteration : players should be provided to the Game class
		players.list.stream()
      .map(player -> player.name)
      .forEach(name -> aGame.add(name));

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
