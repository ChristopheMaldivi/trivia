package com.adaptionsoft.games.trivia;

import com.github.approval.Approval;
import com.github.approval.converters.Converters;
import com.github.approval.converters.ListConverter;
import com.github.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ApprovalTest {

  @Test
  public void approval_can_verify_a_collection() {
    // Given
    Approval<List<String>> approval = Approval.of(String.class)
      .withConveter(new ListConverter(Converters.STRING))
      .withReporter(Reporters.console())
      .build();

    // When
    List<String> actual = Arrays.asList(new String[] { "a", "b"});

    // Then
    approval.verify(actual, Paths.get("src/test/resources/collection.approved.txt"));
  }
}
