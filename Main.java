
// Main.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("customer1", "customer_123"));
        customers.add(new Customer("customer2", "customer_321"));

        Admin admin = new Admin("admin", "admin_123");

        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.next();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.next();

                    if (adminUsername.equals(admin.username) && adminPassword.equals(admin.password)) {
                        adminMenu(admin, customers);
                    } else {
                        System.out.println("Invalid admin credentials");
                    }
                    break;
                case 2:
                    System.out.print("Enter customer username: ");
                    String customerUsername = scanner.next();
                    System.out.print("Enter customer password: ");
                    String customerPassword = scanner.next();

                    Customer customer = findCustomer(customerUsername, customers);

                    if (customer != null && customerPassword.equals(customer.password)) {
                        customerMenu(customer, customers);
                    } else {
                        System.out.println("Invalid customer credentials");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void adminMenu(Admin admin, List<Customer> customers) {
        while (true) {
            System.out.println("1. View all customer information");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    admin.viewAllCustomerInfo(customers);
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void customerMenu(Customer customer, List<Customer> customers) {
        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = getDoubleInput();
                    customer.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = getDoubleInput();
                    customer.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter transfer amount: $");
                    double transferAmount = getDoubleInput();
                    System.out.print("Enter recipient username: ");
                    String recipientUsername = scanner.next();
                    Customer recipient = findCustomer(recipientUsername, customers);
                    if (recipient != null) {
                        customer.transfer(transferAmount, recipient);
                    } else {
                        System.out.println("Recipient not found");
                    }
                    break;
                case 4:
                    customer.viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid double.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextDouble();
    }

    private static Customer findCustomer(String username, List<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.username.equals(username)) {
                return customer;
            }
        }
        return null;
    }
}
