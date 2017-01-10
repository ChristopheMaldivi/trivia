package com.adaptionsoft.games.trivia;

import java.util.Arrays;

public class Game {
  private final Questions questions = new Questions();
  private final Players players;
  private final int[] places;
  private final int[] purses;
  private final boolean[] inPenaltyBox;

  int currentPlayer;
  boolean isGettingOutOfPenaltyBox;

  public Game(Players players) {
    this.players = players;
    places = new int[players.number()];
    purses  = new int[players.number()];
    inPenaltyBox  = new boolean[players.number()];
  }

  public void roll(int roll) {
    Console.println(nameOfPlayer() + " is the current player\nThey have rolled a " + roll);

    if (playerInPenalty()) {
      rollFromPenaltyBox(roll);
    } else {
      rollFromRegularPlace(roll);
    }
  }

  public void correctAnswer() {
    if (!playerInPenalty() || isGettingOutOfPenaltyBox) {
      incrementPlayerPurse();
      Console.println("Answer was correct!!!!\n" + nameOfPlayer() + " now has " + playerPurse() + " Gold Coins.");
    }
    nextPlayer();
  }

  public void wrongAnswer(){
    Console.println("Question was incorrectly answered\n" + nameOfPlayer() + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;
    nextPlayer();
  }

  private void rollFromRegularPlace(int roll) {
    updatePlayerPosition(roll);

    Console.println(nameOfPlayer() + "'s new location is " + currentPosition());
    Console.println("The category is " + questions.categoryAtPosition(currentPosition()));

    askQuestion();
  }

  private void rollFromPenaltyBox(int roll) {
    isGettingOutOfPenaltyBox = roll % 2 != 0;

    Console.println(nameOfPlayer() + " is " + (isGettingOutOfPenaltyBox ? "" : "not") + "getting out of the penalty box");

    if (isGettingOutOfPenaltyBox) {
      updatePlayerPosition(roll);

      Console.println(nameOfPlayer() + "'s new location is " + currentPosition());
      Console.println("The category is " + questions.categoryAtPosition(currentPosition()));
      askQuestion();
    }
  }

  private void askQuestion() {
    Console.println(questions.forPosition(currentPosition()));
  }

  private boolean playerInPenalty() {
    return inPenaltyBox[currentPlayer];
  }

  private void updatePlayerPosition(int roll) {
    places[currentPlayer] = (currentPosition() + roll) % 12;
  }

  private int playerPurse() {
    return purses[currentPlayer];
  }

  private void incrementPlayerPurse() {
    purses[currentPlayer]++;
  }

  private void nextPlayer() {
    currentPlayer = (currentPlayer + 1) % players.number();
  }

  public boolean finished() {
    return Arrays.stream(purses)
      .filter(purse -> purse == 6)
      .findFirst()
      .isPresent();
  }

  private String nameOfPlayer() {
    return players.list.get(currentPlayer).name;
  }

  private int currentPosition() {
    return places[currentPlayer];
  }
}
