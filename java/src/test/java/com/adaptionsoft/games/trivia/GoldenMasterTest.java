package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.Console;
import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GoldenMasterTest {

  @Before
  @After
  public void cleanup() {
    Console.reset();
  }

  @Test
  public void same_output_as_golden_master() throws IOException {
    // Given
    String res = getClass().getResource("/golden-master.txt").getFile();
    String goldenMaster = FileUtils.readFileToString(new File(res), "utf-8");

    // When
    GameRunner.main(null);

    // Then
    assertThat(Console.allContent()).isEqualTo(goldenMaster);
  }
}
