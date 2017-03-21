package com.adaptionsoft.games.trivia;

import com.github.approval.Approval;
import com.github.approval.Approvals;
import com.github.approval.Reporter;
import com.github.approval.reporters.Reporters;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class GameRunnerTest {

  Approval<String> approval = Approval.of(String.class)
    .withReporter(Reporters.console())
    .build();


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
    //String path = getClass().getResource("/GameRunnerTest.game_runner_can_be_instantiated.approved.txt").getPath();
    approval.verify(Console.allContent(), Paths.get("src/test/resources/GameRunnerTest.game_runner_can_be_instantiated.approved.txt"));
  }
}
