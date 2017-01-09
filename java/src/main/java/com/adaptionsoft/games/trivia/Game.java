package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {
  ArrayList players = new ArrayList();
  int[] places = new int[6];
  int[] purses  = new int[6];
  boolean[] inPenaltyBox  = new boolean[6];

  LinkedList<String> popQuestions = new LinkedList();
  LinkedList<String> scienceQuestions = new LinkedList();
  LinkedList<String> sportsQuestions = new LinkedList();
  LinkedList<String> rockQuestions = new LinkedList();

  int currentPlayer = 0;
  boolean isGettingOutOfPenaltyBox;

  public  Game(Players players) {
    players.list.stream()
      .map(player -> player.name)
      .forEach(name -> add(name));

    for (int i = 0; i < 50; i++) {
      popQuestions.addLast("Pop Question " + i);
      scienceQuestions.addLast(("Science Question " + i));
      sportsQuestions.addLast(("Sports Question " + i));
      rockQuestions.addLast(createRockQuestion(i));
    }
  }

  public String createRockQuestion(int index){
    return "Rock Question " + index;
  }

  public boolean add(String playerName) {


    players.add(playerName);
    places[howManyPlayers()] = 0;
    purses[howManyPlayers()] = 0;
    inPenaltyBox[howManyPlayers()] = false;

    Console.println(playerName + " was added");
    Console.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    Console.println(players.get(currentPlayer) + " is the current player");
    Console.println("They have rolled a " + roll);

    if (inPenaltyBox[currentPlayer]) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        Console.println(players.get(currentPlayer) + " is getting out of the penalty box");
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        Console.println(players.get(currentPlayer)
          + "'s new location is "
          + places[currentPlayer]);
        Console.println("The category is " + currentCategory());
        askQuestion();
      } else {
        Console.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      places[currentPlayer] = places[currentPlayer] + roll;
      if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

      Console.println(players.get(currentPlayer)
        + "'s new location is "
        + places[currentPlayer]);
      Console.println("The category is " + currentCategory());
      askQuestion();
    }

  }

  private void askQuestion() {
    if (currentCategory() == "Pop")
      Console.println(popQuestions.removeFirst());
    if (currentCategory() == "Science")
      Console.println(scienceQuestions.removeFirst());
    if (currentCategory() == "Sports")
      Console.println(sportsQuestions.removeFirst());
    if (currentCategory() == "Rock")
      Console.println(rockQuestions.removeFirst());
  }


  private String currentCategory() {
    if (places[currentPlayer] == 0) return "Pop";
    if (places[currentPlayer] == 4) return "Pop";
    if (places[currentPlayer] == 8) return "Pop";
    if (places[currentPlayer] == 1) return "Science";
    if (places[currentPlayer] == 5) return "Science";
    if (places[currentPlayer] == 9) return "Science";
    if (places[currentPlayer] == 2) return "Sports";
    if (places[currentPlayer] == 6) return "Sports";
    if (places[currentPlayer] == 10) return "Sports";
    return "Rock";
  }

  public void correctAnswer() {
    if (inPenaltyBox[currentPlayer]){
      if (isGettingOutOfPenaltyBox) {
        Console.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        Console.println(players.get(currentPlayer)
          + " now has "
          + purses[currentPlayer]
          + " Gold Coins.");

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

      } else {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
      }



    } else {

      Console.println("Answer was corrent!!!!");
      purses[currentPlayer]++;
      Console.println(players.get(currentPlayer)
        + " now has "
        + purses[currentPlayer]
        + " Gold Coins.");

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
    }
  }

  public void wrongAnswer(){
    Console.println("Question was incorrectly answered");
    Console.println(players.get(currentPlayer)+ " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    currentPlayer++;
    if (currentPlayer == players.size()) currentPlayer = 0;
  }

  public boolean finished() {
    return Arrays.stream(purses)
      .filter(purse -> purse == 6)
      .findFirst()
      .isPresent();
  }
}
