package com.adaptionsoft.games.trivia;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayersTest {

  @Test
  public void init_with_several_players() {
    // Given
    String player1 = "Chet";
    String player2 = "Sue";
    List<Players.Player> expected = new ArrayList<>();
    expected.add(new Players.Player(player1));
    expected.add(new Players.Player(player2));

    // When
    Players players = Players.builder()
      .addPlayer(player1)
      .addPlayer(player2)
      .build();

    // Then
    assertThat(players.list).isEqualTo(expected);
  }
}
