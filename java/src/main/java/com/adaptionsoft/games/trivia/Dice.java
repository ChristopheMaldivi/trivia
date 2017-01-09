package com.adaptionsoft.games.trivia;

import java.util.Random;

public class Dice {
  private final Random random;
  private final boolean deterministSequence;
  private int simulatedRandomNumber;

  public Dice() {
    this(false);
  }

  public Dice(boolean deterministSequence) {
    this.deterministSequence = deterministSequence;
    random = deterministSequence ? null : new Random();
  }

  public int rollForGame() {
    return roll(5);
  }

  public boolean rollForWongAnswer() {
    return 7 == roll(9);
  }

  private int roll(int exclusiveUpperBound) {
    return 1 + (
      deterministSequence ?
        newSimulatedRandomNumber(exclusiveUpperBound) :
        random.nextInt(exclusiveUpperBound)
    );
  }

  private int newSimulatedRandomNumber(int exclusiveUpperBound) {
    simulatedRandomNumber += 1 + 2 * simulatedRandomNumber;
    simulatedRandomNumber %= exclusiveUpperBound;
    return simulatedRandomNumber;
  }
}
