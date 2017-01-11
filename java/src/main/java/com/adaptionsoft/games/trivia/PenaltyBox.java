package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

public class PenaltyBox {
  private final List<Integer> playersId = new ArrayList<>();

  public void addPlayer(int playerId) {
    if (!contains(playerId)) {
      playersId.add(playerId);
    }
  }

  public boolean contains(int playerId) {
    return playersId.contains(playerId);
  }

  public void playerRoll(int playerId, int roll) {
    boolean odd = roll % 2 != 0;
    if (odd) {
      removePlayer(playerId);
    }
  }

  void removePlayer(int playerId) {
    playersId.remove(new Integer(playerId));
  }
}
