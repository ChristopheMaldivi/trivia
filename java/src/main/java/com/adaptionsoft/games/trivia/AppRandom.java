package com.adaptionsoft.games.trivia;

import java.util.Random;

public class AppRandom {
  private final Random random;
  private final boolean deterministSequence;
  private int simulatedRandomNumber;

  public AppRandom() {
    this(false);
  }

  public AppRandom(boolean deterministSequence) {
    this.deterministSequence = deterministSequence;
    random = deterministSequence ? null : new Random();
  }

  public int nextInt(int exclusiveUpperBound) {
    return deterministSequence ?
            newSimulatedRandomNumber(exclusiveUpperBound) :
            random.nextInt(exclusiveUpperBound);
  }

  private int newSimulatedRandomNumber(int exclusiveUpperBound) {
    simulatedRandomNumber += 1 + 2 * simulatedRandomNumber;
    simulatedRandomNumber %= exclusiveUpperBound;
    return simulatedRandomNumber;
  }
}
