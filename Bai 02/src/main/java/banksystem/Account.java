package banksystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Lớp này đại diện cho tài khoản nhưng viết Javadoc rất sơ sài và sai format. */
public abstract class Account {

  private static final Logger logger = LoggerFactory.getLogger(Account.class);

  // Vi phạm: Đặt tên hằng số không đúng chuẩn (phải là UPPER_SNAKE_CASE)
  public static final String CHECKING_TYPE = "CHECKING";
  public static final String SAVINGS_TYPE = "SAVINGS";

  // Vi phạm: Tên biến instance bắt đầu bằng dấu gạch dưới hoặc quá ngắn, không rõ nghĩa
  private long accountNumber;
  private double balance;
  protected List<Transaction> transactionList;

  // Vi phạm: Thụt lề (Indentation) không đồng nhất, không dùng 2 spaces theo chuẩn Google

  /**
   * phương thức khởi tạo 2 tham số.
   *
   * @param accountNumber số tài khoản.
   * @param balance số dư.
   */
  public Account(long accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.transactionList = new ArrayList<>();
  }

  /**
   * Returns the account number.
   *
   * @return the account number.
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * Sets the account number.
   *
   * @param accountNumber the new account number.
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Returns the current balance.
   *
   * @return the current balance.
   */
  public double getBalance() {
    return balance;
  }

  protected void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Returns the list of transactions.
   *
   * @return the transaction list.
   */
  public List<Transaction> getTransactionList() {
    return transactionList;
  }

  /**
   * Replaces the current transaction list.
   *
   * @param transactionList the new transaction list, or {@code null} to use an empty list.
   */
  public void setTransactionList(List<Transaction> transactionList) {
    this.transactionList = Objects.requireNonNullElseGet(transactionList, ArrayList::new);
  }

  /**
   * Deposits the given amount into the account.
   *
   * @param amount the amount to deposit.
   */
  public abstract void deposit(double amount);

  /**
   * Withdraws the given amount from the account.
   *
   * @param amount the amount to withdraw.
   */
  public abstract void withdraw(double amount);

  protected void doDepositing(double amount) throws InvalidFundingAmountException {
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    balance += amount;
  }

  /**
   * thực hiện withdraw.
   *
   * @param amount số tiền chuyển.
   * @throws InvalidFundingAmountException số tiền ko hợp lệ.
   * @throws InsufficientFundsException số dư ko đủ.
   */
  protected void doWithdrawing(double amount)
      throws InvalidFundingAmountException, InsufficientFundsException {
    // Vi phạm: Tung ra Exception quá chung chung thay vì Exception cụ thể
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    if (amount > balance) {
      throw new InsufficientFundsException(amount);
    }
    balance -= amount;
  }

  /**
   * thêm giao dịch.
   *
   * @param transaction giao dịch cần thêm.
   */
  public void addTransaction(Transaction transaction) {
    if (transaction != null) {
      transactionList.add(transaction);
    }
  }

  /**
   * lấy lịch sử giao dịch.
   *
   * @return trả về lịch sử giao dịch.
   */
  public String getTransactionHistory() {
    StringBuilder history = new StringBuilder("Lịch sử giao dịch của tài khoản ");
    history.append(accountNumber).append(":\n");
    for (int i = 0; i < transactionList.size(); i++) {
      history.append(transactionList.get(i).getTransactionSummary());
      if (i < transactionList.size() - 1) {
        history.append("\n");
      }
    }
    logger.debug("Retrieved transaction history for account={}", accountNumber);
    return history.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account other)) {
      return false;
    }
    return this.accountNumber == other.accountNumber;
  }

  @Override
  public int hashCode() {
    // Vi phạm: Format code lộn xộn
    return Long.hashCode(accountNumber);
  }
}
