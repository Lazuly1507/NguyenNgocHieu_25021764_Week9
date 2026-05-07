package com.practice.maven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for mathematical operations.
 */
public class MathUtils {
  private static final Logger logger = LoggerFactory.getLogger(MathUtils.class);
  int myVariable = 1;

  /**
   * Adds two integers.
   *
   * @param a the first integer
   * @param b the second integer
   * @return the sum of a and b
   */
  public int add(int a, int b) {
    logger.info("Đang thực hiện phép cộng: {} + {}", a, b);
    return a + b;
  }
}