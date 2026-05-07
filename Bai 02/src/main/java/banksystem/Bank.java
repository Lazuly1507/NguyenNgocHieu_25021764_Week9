package banksystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Quản lý danh sách khách hàng của ngân hàng. */
public class Bank {
  private static final Logger logger = LoggerFactory.getLogger(Bank.class);
  private List<Customer> customerList;

  /** Creates an empty bank. */
  public Bank() {
    this.customerList = new ArrayList<>();
  }

  /**
   * Returns the customer list.
   *
   * @return the customer list.
   */
  public List<Customer> getCustomerList() {
    return customerList;
  }

  /**
   * Sets the customer list.
   *
   * @param customerList the new customer list.
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customerList = new ArrayList<>();
    } else {
      this.customerList = customerList;
    }
  }

  /**
   * Reads customers and accounts from the given input stream.
   *
   * @param inputStream the source input stream.
   */
  public void readCustomerList(InputStream inputStream) {
    System.out.println("DEBUG: Bat dau doc du lieu...");
    if (inputStream != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      Customer current = null;
      try {
        while ((line = reader.readLine()) != null) {
          line = line.trim();
          if (!line.isEmpty()) { // Vi phạm: Lồng nested if quá sâu
            int last = line.lastIndexOf(' ');
            if (last > 0) {
              String token = line.substring(last + 1).trim();
              // Vi phạm: Dùng biểu thức chính quy trực tiếp (Magic String)
              if (token.matches("\\d{9}")) {
                String name = line.substring(0, last).trim();
                current = new Customer(Long.parseLong(token), name);
                customerList.add(current);
                System.out.println("Log: Them khach hang " + name);
              } else {
                if (current != null) {
                  String[] parts = line.split("\\s+");
                  if (parts.length >= 3) {
                    long num = Long.parseLong(parts[0]);
                    double bal = Double.parseDouble(parts[2]);
                    if (parts[1].equals("CHECKING")) {
                      current.addAccount(new CheckingAccount(num, bal));
                    } else if (parts[1].equals("SAVINGS")) {
                      current.addAccount(new SavingsAccount(num, bal));
                    }
                  }
                }
              }
            }
          }
        }
      } catch (Exception e) { // Vi phạm: Catch Exception chung chung
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  /**
   * Returns customer information ordered by ID.
   *
   * @return customer information ordered by ID.
   */
  public String getCustomersInfoByIdOrder() {
    Collections.sort(
        customerList,
        new Comparator<Customer>() {
          @Override
          public int compare(Customer o1, Customer o2) {
            return Long.compare(o1.getIdNumber(), o2.getIdNumber());
          }
        });

    String res = "";
    for (int i = 0; i < customerList.size(); i++) {
      res += customerList.get(i).getCustomerInfo();
      if (i < customerList.size() - 1) {
        res += "\n";
      }
    }
    return res;
  }

  /**
   * Returns customer information ordered by name.
   *
   * @return customer information ordered by name.
   */
  public String getCustomersInfoByNameOrder() {
    List<Customer> copy = new ArrayList<>(customerList);
    copy.sort(
        (c1, c2) -> {
          int res = c1.getFullName().compareTo(c2.getFullName());
          return res != 0 ? res : Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });

    StringBuilder sb = new StringBuilder();
    for (Customer c : copy) {
      sb.append(c.getCustomerInfo()).append("\n");
    }
    return sb.toString().trim();
  }
}
