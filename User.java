
// User.java
import java.util.ArrayList;
import java.util.List;

abstract class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    abstract void viewTransactionHistory();
}

class Admin extends User {
    Admin(String username, String password) {
        super(username, password);
    }

    void viewAllCustomerInfo(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    @Override
    void viewTransactionHistory() {
        System.out.println("Admin view transaction history");
    }
}

class Customer extends User {
    double balance;
    List<Transaction> transactions;

    Customer(String username, String password) {
        super(username, password);
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    void deposit(double amount) {
        this.balance += amount;
        transactions.add(new DepositTransaction(amount));
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            transactions.add(new WithdrawTransaction(amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    void transfer(double amount, Customer recipient) {
        if (amount <= balance) {
            this.balance -= amount;
            recipient.balance += amount;
            transactions.add(new TransferTransaction(amount, recipient));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    void viewTransactionHistory() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}