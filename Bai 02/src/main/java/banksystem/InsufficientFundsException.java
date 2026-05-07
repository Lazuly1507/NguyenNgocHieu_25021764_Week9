package banksystem;

import java.util.Locale;

/** Ngoại lệ khi số dư tài khoản không đủ. */
public class InsufficientFundsException extends BankException {
  /**
   * Creates an exception for an insufficient account balance.
   *
   * @param amount the requested amount.
   */
  public InsufficientFundsException(double amount) {
    super(
        "Số dư tài khoản không đủ $"
            + String.format(Locale.US, "%.2f", amount)
            + " để thực hiện giao dịch");
  }
}
