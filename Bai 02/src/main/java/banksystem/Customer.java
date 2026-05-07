package banksystem;

import java.util.ArrayList;
import java.util.List;

/** Lớp Customer đại diện cho một khách hàng. */
public class Customer {
  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /** Constructor không tham số (phục vụ MyTest). */
  public Customer() {
    this(0L, "");
  }

  /**
   * Creates a customer.
   *
   * @param idNumber the identification number.
   * @param fullName the full name.
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  /**
   * Returns the identification number.
   *
   * @return the identification number.
   */
  public long getIdNumber() {
    return idNumber;
  }

  /**
   * Sets the identification number.
   *
   * @param idNumber the new identification number.
   */
  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  /**
   * Returns the full name.
   *
   * @return the full name.
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Sets the full name.
   *
   * @param fullName the new full name.
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Returns the account list.
   *
   * @return the account list.
   */
  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Sets the account list.
   *
   * @param accountList the new account list.
   */
  public void setAccountList(List<Account> accountList) {
    if (accountList == null) {
      this.accountList = new ArrayList<>();
    } else {
      this.accountList = accountList;
    }
  }

  /** Thêm tài khoản cho khách hàng. */
  public void addAccount(Account account) {
    if (account == null) {
      return;
    }
    if (!accountList.contains(account)) {
      accountList.add(account);
    }
  }

  /** Xóa tài khoản khỏi khách hàng. */
  public void removeAccount(Account account) {
    if (account == null) {
      return;
    }
    accountList.remove(account);
  }

  /** Thông tin khách hàng dạng text. */
  public String getCustomerInfo() {
    return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
  }
}
