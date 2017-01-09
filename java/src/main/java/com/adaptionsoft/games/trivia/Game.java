package com.adaptionsoft.games.trivia;

import java.util.Arrays;

public class Game {
  private final Questions questions = new Questions();
  private final Players players;
  private final int[] places;
  private final int[] purses;
  private final boolean[] inPenaltyBox;

  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

  public Game(Players players) {
    this.players = players;
    places = new int[players.number()];
    purses  = new int[players.number()];
    inPenaltyBox  = new boolean[players.number()];
  }

  public void roll(int roll) {
    Console.println(nameOfPlayer() + " is the current player");
    Console.println("They have rolled a " + roll);

    if (inPenaltyBox[currentPlayer]) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        Console.println(nameOfPlayer() + " is getting out of the penalty box");
        places[currentPlayer] = currentPosition() + roll;
        if (currentPosition() > 11) places[currentPlayer] = currentPosition() - 12;

        Console.println(nameOfPlayer()
          + "'s new location is "
          + currentPosition());
        Console.println("The category is " + questions.categoryAtPosition(currentPosition()));
        askQuestion();
      } else {
        Console.println(nameOfPlayer() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {
      places[currentPlayer] = currentPosition() + roll;
      if (currentPosition() > 11) places[currentPlayer] = currentPosition() - 12;

      Console.println(nameOfPlayer()
        + "'s new location is "
        + currentPosition());
      Console.println("The category is " + questions.categoryAtPosition(currentPosition()));
      askQuestion();
    }

  }

  private void askQuestion() {
    Console.println(questions.forPosition(currentPosition()));
  }

  public void correctAnswer() {
    if (inPenaltyBox[currentPlayer]){
      if (isGettingOutOfPenaltyBox) {
        Console.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        Console.println(nameOfPlayer()
          + " now has "
          + purses[currentPlayer]
          + " Gold Coins.");

        currentPlayer++;
        if (currentPlayer == players.number()) currentPlayer = 0;

      } else {
        currentPlayer++;
        if (currentPlayer == players.number()) currentPlayer = 0;
      }

    } else {
      Console.println("Answer was corrent!!!!");
      purses[currentPlayer]++;
      Console.println(nameOfPlayer()
        + " now has "
        + purses[currentPlayer]
        + " Gold Coins.");

      currentPlayer++;
      if (currentPlayer == players.number()) currentPlayer = 0;
    }
  }

  public void wrongAnswer(){
    Console.println("Question was incorrectly answered");
    Console.println(nameOfPlayer() + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    currentPlayer++;
    if (currentPlayer == players.number()) currentPlayer = 0;
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
