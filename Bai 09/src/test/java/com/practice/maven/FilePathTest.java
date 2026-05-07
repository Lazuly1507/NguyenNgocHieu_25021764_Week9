package com.practice.maven;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathTest {
  @Test
  void testWorksEverywhere() {
    Path path = Paths.get("pom.xml");
    assertTrue(Files.exists(path));
  }
}