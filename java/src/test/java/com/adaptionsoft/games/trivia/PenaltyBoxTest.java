package com.adaptionsoft.games.trivia;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PenaltyBoxTest {

  @Test
  public void player_enter_penalty_box() {
    // Given
    PenaltyBox penaltyBox = new PenaltyBox();
    int playerId = 0;

    // When
    penaltyBox.addPlayer(playerId);
    boolean playerInPenaltyBox = penaltyBox.contains(playerId);

    // Then
    Assertions.assertThat(playerInPenaltyBox).isTrue();
  }

  @Test
  public void player_exit_penalty_box() {
    // Given
    PenaltyBox penaltyBox = new PenaltyBox();
    penaltyBox.addPlayer(12);

    // When
    penaltyBox.removePlayer(12);
    boolean playerInPenaltyBox = penaltyBox.contains(12);

    // Then
    Assertions.assertThat(playerInPenaltyBox).isFalse();
  }

  @Test
  public void player_exits() {
    // Given
    PenaltyBox penaltyBox = new PenaltyBox();
    penaltyBox.addPlayer(12);

    // When
    penaltyBox.playerRoll(12, 3);
    boolean playerInPenaltyBox = penaltyBox.contains(12);

    // Then
    Assertions.assertThat(playerInPenaltyBox).isFalse();
  }

  @Test
  public void player_stays() {
    // Given
    PenaltyBox penaltyBox = new PenaltyBox();
    penaltyBox.addPlayer(12);

    // When
    penaltyBox.playerRoll(12, 2);
    boolean playerInPenaltyBox = penaltyBox.contains(12);

    // Then
    Assertions.assertThat(playerInPenaltyBox).isTrue();
  }
}
