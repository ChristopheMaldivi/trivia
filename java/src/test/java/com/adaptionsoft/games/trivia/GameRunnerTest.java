package com.adaptionsoft.games.trivia;

import org.apache.commons.io.FileUtils;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRunnerTest {

  @Test
  public void game_runner_can_be_instantiated() throws IOException {
    // Given
    Players players = Players.builder()
      .addPlayer("Chet")
      .addPlayer("Pat")
      .addPlayer("Sue")
      .build();

    GameRunner gameRunner = new GameRunner(new Dice(true));

    // When
    gameRunner.run(players);

    // Then

    //Approvals.verify(Console.allContent());
  }
}
