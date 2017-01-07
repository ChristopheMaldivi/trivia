package com.adaptionsoft.games.trivia;

import org.junit.Test;

public class GameRunnerTest {

  @Test
  public void game_runner_can_be_instantiated() {
    // Given
    Players players = Players.builder()
      .addPlayer("Chet")
      .addPlayer("Pat")
      .addPlayer("Sue")
      .build();

    GameRunner gameRunner = new GameRunner();

    // When
    gameRunner.run(players);
  }
}
