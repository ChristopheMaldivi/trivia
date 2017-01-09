package com.adaptionsoft.games.trivia;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class Questions {

  enum Category {
    Pop {
      @Override
      public List<Integer> positions() {
        Integer[] positions = {0, 4, 8};
        return Arrays.asList(positions);
      }
    }, Science {
      @Override
      public List<Integer> positions() {
        Integer[] positions = {1, 5, 9};
        return Arrays.asList(positions);
      }
    }, Sports {
      @Override
      public List<Integer> positions() {
        Integer[] positions = {2, 6, 10};
        return Arrays.asList(positions);
      }
    }, Rock{
      @Override
      public List<Integer> positions() {
        Integer[] positions = {3, 7, 11};
        return Arrays.asList(positions);
      }
    };

    public abstract List<Integer> positions();
  }

  private final int[] questionsCountPerCategory = new int[Category.values().length];

  public String forPosition(int position) {
    Category category = categoryAtPosition(position);
    int count = questionsCountPerCategory[category.ordinal()];
    questionsCountPerCategory[category.ordinal()] = count + 1;
    return category + " Question " + count;
  }


  public Questions.Category categoryAtPosition(int position) {
    Optional<Category> category = Arrays.stream(Category.values())
      .filter(cat -> cat.positions().contains(position))
      .findAny();
    if (category.isPresent()) {
      return category.get();
    }
    throw new IllegalStateException("Did not found a category for position: " + position);
  }
}
