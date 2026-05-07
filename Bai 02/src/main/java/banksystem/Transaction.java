package banksystem;

/** Đại diện cho một giao dịch. */
public class Transaction {
  public static final int TYPE_DEPOSIT_CHECKING = 1;
  public static final int TYPE_WITHDRAW_CHECKING = 2;
  public static final int TYPE_DEPOSIT_SAVINGS = 3;
  public static final int TYPE_WITHDRAW_SAVINGS = 4;

  private int type;
  private double amount;
  private double initialBalance;
  private double finalBalance;

  /**
   * Creates a transaction.
   *
   * @param type the transaction type.
   * @param amount the transaction amount.
   * @param initialBalance the balance before the transaction.
   * @param finalBalance the balance after the transaction.
   */
  public Transaction(int type, double amount, double initialBalance, double finalBalance) {
    this.type = type;
    this.amount = amount;
    this.initialBalance = initialBalance;
    this.finalBalance = finalBalance;
  }

  /**
   * Returns the transaction type.
   *
   * @return the transaction type.
   */
  public int getType() {
    return type;
  }

  /**
   * Sets the transaction type.
   *
   * @param type the new transaction type.
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * Returns the transaction amount.
   *
   * @return the transaction amount.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets the transaction amount.
   *
   * @param amount the new transaction amount.
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Returns the initial balance.
   *
   * @return the initial balance.
   */
  public double getInitialBalance() {
    return initialBalance;
  }

  /**
   * Sets the initial balance.
   *
   * @param initialBalance the new initial balance.
   */
  public void setInitialBalance(double initialBalance) {
    this.initialBalance = initialBalance;
  }

  /**
   * Returns the final balance.
   *
   * @return the final balance.
   */
  public double getFinalBalance() {
    return finalBalance;
  }

  /**
   * Sets the final balance.
   *
   * @param finalBalance the new final balance.
   */
  public void setFinalBalance(double finalBalance) {
    this.finalBalance = finalBalance;
  }

  /**
   * Returns the transaction type description.
   *
   * @param type the transaction type.
   * @return the transaction type description.
   */
  public static String getTypeString(int type) {
    switch (type) {
      case TYPE_DEPOSIT_CHECKING:
        return "Nạp tiền vãng lai";
      case TYPE_WITHDRAW_CHECKING:
        return "Rút tiền vãng lai";
      case TYPE_DEPOSIT_SAVINGS:
        return "Nạp tiền tiết kiệm";
      case TYPE_WITHDRAW_SAVINGS:
        return "Rút tiền tiết kiệm";
      default:
        return "Không rõ";
    }
  }

  /**
   * Returns a formatted transaction summary.
   *
   * @return the transaction summary.
   */
  public String getTransactionSummary() {
    System.out.println(">>> [LOGGING] summary process started for type: " + this.type);

    return "- Kiểu giao dịch: "
        + getTypeString(type)
        + ". Số dư ban đầu: $"
        + String.format(java.util.Locale.US, "%.2f", initialBalance)
        + ". Số tiền: $"
        + String.format(java.util.Locale.US, "%.2f", amount)
        + ". Số dư cuối: $"
        + String.format(java.util.Locale.US, "%.2f", finalBalance)
        + ".";
  }
}
