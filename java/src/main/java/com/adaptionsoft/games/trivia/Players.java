package com.adaptionsoft.games.trivia;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class Players {

  @EqualsAndHashCode
  public static class Player {
    public final String name;
    public Player(String name) {
      this.name = name;
    }
  }

  public final List<Player> list;

  public int number() {
    return list.size();
  }

  public static PlayersBuilder builder() {
    return new PlayersBuilder();
  }

  private Players(List<Player> list) {
    this.list = Collections.unmodifiableList(list);
  }

  public static class PlayersBuilder {
    private final List<Player> players = new ArrayList<>();

    public PlayersBuilder addPlayer(String name) {
      players.add(new Player(name));
      return this;
    }

    public Players build() {
      return new Players(players);
    }

    private PlayersBuilder() {
    }
  }
}
