package com.adaptionsoft.games.trivia;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRunnerTest {

  @Test
  public void game_runner_can_be_instantiated() throws IOException {
    // Given
    String res = getClass().getResource("/golden-master.txt").getFile();
    String goldenMaster = FileUtils.readFileToString(new File(res), "utf-8");

    Players players = Players.builder()
      .addPlayer("Chet")
      .addPlayer("Pat")
      .addPlayer("Sue")
      .build();

    GameRunner gameRunner = new GameRunner(new Dice(true));

    // When
    gameRunner.run(players);

    // Then
    assertThat(Console.allContent()).isEqualTo(goldenMaster);
  }
}
