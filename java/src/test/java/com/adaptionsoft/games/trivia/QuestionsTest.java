package com.adaptionsoft.games.trivia;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class QuestionsTest {

  @Test
  public void question_number_at_given_position() {
    // Given
    Questions questions = new Questions();

    // When
    // Then
    Assertions.assertThat(questions.forPosition(0)).isEqualTo("Pop Question 0");
    Assertions.assertThat(questions.forPosition(4)).isEqualTo("Pop Question 1");
    Assertions.assertThat(questions.forPosition(8)).isEqualTo("Pop Question 2");

    Assertions.assertThat(questions.forPosition(1)).isEqualTo("Science Question 0");
    Assertions.assertThat(questions.forPosition(5)).isEqualTo("Science Question 1");
    Assertions.assertThat(questions.forPosition(9)).isEqualTo("Science Question 2");

    Assertions.assertThat(questions.forPosition(2)).isEqualTo("Sports Question 0");
    Assertions.assertThat(questions.forPosition(6)).isEqualTo("Sports Question 1");
    Assertions.assertThat(questions.forPosition(10)).isEqualTo("Sports Question 2");

    Assertions.assertThat(questions.forPosition(3)).isEqualTo("Rock Question 0");
    Assertions.assertThat(questions.forPosition(7)).isEqualTo("Rock Question 1");
    Assertions.assertThat(questions.forPosition(11)).isEqualTo("Rock Question 2");
  }
}
