package com.adaptionsoft.games.trivia;

public class Console {

  private static final StringBuilder stringBuilder = new StringBuilder();

  public static void println(String string) {
    System.out.println(string);

    stringBuilder.append(string + "\n");
  }

  public static String allContent() {
    return stringBuilder.toString();
  }

  public static void reset() {
    stringBuilder.setLength(0);
  }
}
