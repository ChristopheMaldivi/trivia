package com.adaptionsoft.games.trivia;

public class GameRunner {
  private final Dice dice;

  public GameRunner(Dice dice) {
    this.dice = dice;
  }

  public void run(Players players) {
		Game game = new Game(players);

		do {
			game.roll(dice.rollForGame());

			if (forceWrongAnswer()) {
				game.wrongAnswer();
			} else {
				game.correctAnswer();
			}

		} while (!game.finished());
	}

  private boolean forceWrongAnswer() {
    return dice.rollForWongAnswer();
  }
}
