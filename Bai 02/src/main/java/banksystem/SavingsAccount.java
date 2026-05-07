package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Tài khoản tiết kiệm với các quy định riêng về nạp và rút tiền. */
public class SavingsAccount extends Account {

  private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);

  /** Creates a savings account. */
  public SavingsAccount(long n, double b) {
    super(n, b);
  }

  @Override
  public void deposit(double a) {
    logger.debug(
        "Starting savings deposit: account={}, amount={}", getAccountNumber(), a);
    double initialBalance = getBalance();
    try {
      doDepositing(a);
      double finalBalance = getBalance();
      Transaction transaction =
          new Transaction(Transaction.TYPE_DEPOSIT_SAVINGS, a, initialBalance, finalBalance);
      addTransaction(transaction);
      logger.info(
          "Savings deposit successful: account={}, amount={}, before={}, after={}",
          getAccountNumber(),
          a,
          initialBalance,
          finalBalance);
    } catch (InvalidFundingAmountException e) {
      logger.warn(
          "Savings deposit rejected: account={}, amount={}, reason={}",
          getAccountNumber(),
          a,
          e.getMessage());
    }
  }

  @Override
  public void withdraw(double a) {
    logger.debug(
        "Starting savings withdrawal: account={}, amount={}", getAccountNumber(), a);
    double initialBalance = getBalance();
    try {
      if (a > 1000.0) {
        throw new InvalidFundingAmountException(a);
      }
      if (initialBalance - a < 5000.0) {
        throw new InsufficientFundsException(a);
      }

      doWithdrawing(a);
      double finalBalance = getBalance();

      Transaction transaction =
          new Transaction(Transaction.TYPE_WITHDRAW_SAVINGS, a, initialBalance, finalBalance);
      addTransaction(transaction);

      logger.info(
          "Savings withdrawal successful: account={}, amount={}, before={}, after={}",
          getAccountNumber(),
          a,
          initialBalance,
          finalBalance);
    } catch (InvalidFundingAmountException | InsufficientFundsException e) {
      logger.warn(
          "Savings withdrawal rejected: account={}, amount={}, reason={}",
          getAccountNumber(),
          a,
          e.getMessage());
    }
  }
}
