package com.adaptionsoft.games.trivia;

public class App {
	public static void main(String[] args) {
    Players players = Players.builder()
      .addPlayer("Chet")
      .addPlayer("Pat")
      .addPlayer("Sue")
      .build();

	  GameRunner gameRunner = new GameRunner();
    gameRunner.run(players);
  }
}
