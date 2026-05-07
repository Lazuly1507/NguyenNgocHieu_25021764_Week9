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

  /**
   * Divides two integers.
   *
   * @param a the numerator
   * @param b the denominator
   * @return the result of a divided by b
   * @throws ArithmeticException if b is zero
   */
  public int divide(int a, int b) {
    // Mức INFO: Ghi lại các mốc quan trọng
    logger.info("Thực hiện phép chia: {} / {}", a, b);

    try {
      return a / b;
    } catch (ArithmeticException e) {
      // Mức ERROR: Ghi lại lỗi và stack trace
      logger.error("Lỗi toán học khi chia cho 0: {}", e.getMessage());
      throw e;
    }
  }

  /**
   * Main method to demonstrate the usage of MathUtils.
   *
   * @param args
   *
   */
  public static void main(String[] args) {
    MathUtils mathUtils = new MathUtils();
    int result = mathUtils.add(5, 3);
    System.out.println("Kết quả: " + result);
  }
}