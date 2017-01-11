package com.adaptionsoft.games.trivia;

import java.util.Arrays;

public class Game {
  private final Questions questions = new Questions();
  private final PenaltyBox penaltyBox = new PenaltyBox();
  private final Players players;
  private final int[] places;
  private final int[] purses;

  int currentPlayerId;

  public Game(Players players) {
    this.players = players;
    places = new int[players.number()];
    purses  = new int[players.number()];
  }

  public void roll(int roll) {
    Console.println(nameOfPlayer() + " is the current player\nThey have rolled a " + roll);

    if (penaltyBox.contains(currentPlayerId)) {
      rollFromPenaltyBox(roll);
    } else {
      rollFromRegularPlace(roll);
    }
  }

  public void correctAnswer() {
    if (!penaltyBox.contains(currentPlayerId)) {
      incrementPlayerPurse();
      Console.println("Answer was correct!!!!\n" + nameOfPlayer() + " now has " + playerPurse() + " Gold Coins.");
    }
    nextPlayer();
  }

  public void wrongAnswer(){
    Console.println("Question was incorrectly answered\n" + nameOfPlayer() + " was sent to the penalty box");
    penaltyBox.addPlayer(currentPlayerId);
    nextPlayer();
  }

  private void rollFromRegularPlace(int roll) {
    updatePlayerPosition(roll);

    Console.println(nameOfPlayer() + "'s new location is " + currentPosition());
    Console.println("The category is " + questions.categoryAtPosition(currentPosition()));

    askQuestion();
  }

  private void rollFromPenaltyBox(int roll) {
    penaltyBox.playerRoll(currentPlayerId, roll);
    boolean outOfPenaltyBox = penaltyBox.contains(currentPlayerId);

    Console.println(nameOfPlayer() + " is " + (outOfPenaltyBox ? "" : "not") + "getting out of the penalty box");

    if (outOfPenaltyBox) {
      updatePlayerPosition(roll);

      Console.println(nameOfPlayer() + "'s new location is " + currentPosition());
      Console.println("The category is " + questions.categoryAtPosition(currentPosition()));
      askQuestion();
    }
  }

  private void askQuestion() {
    Console.println(questions.forPosition(currentPosition()));
  }

  private int playerPurse() {
    return purses[currentPlayerId];
  }
  private void incrementPlayerPurse() {
    purses[currentPlayerId]++;
  }

  private void updatePlayerPosition(int roll) {
    places[currentPlayerId] = (currentPosition() + roll) % 12;
  }
  private int currentPosition() {
    return places[currentPlayerId];
  }

  private void nextPlayer() {
    currentPlayerId = (currentPlayerId + 1) % players.number();
  }
  private String nameOfPlayer() {
    return players.list.get(currentPlayerId).name;
  }

  public boolean finished() {
    return Arrays.stream(purses)
      .filter(purse -> purse == 6)
      .findFirst()
      .isPresent();
  }
}
