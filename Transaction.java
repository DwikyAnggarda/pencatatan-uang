
// Transaction.java
interface Transaction {
    void execute();
}

class DepositTransaction implements Transaction {
    double amount;

    DepositTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute() {
        System.out.println("Deposit: $" + amount);
    }

    @Override
    public String toString() {
        return "DepositTransaction{" +
                "amount=" + amount +
                '}';
    }
}

class WithdrawTransaction implements Transaction {
    double amount;

    WithdrawTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute() {
        System.out.println("Withdraw: $" + amount);
    }

    @Override
    public String toString() {
        return "WithdrawTransaction{" +
                "amount=" + amount +
                '}';
    }
}

class TransferTransaction implements Transaction {
    double amount;
    Customer recipient;

    TransferTransaction(double amount, Customer recipient) {
        this.amount = amount;
        this.recipient = recipient;
    }

    @Override
    public void execute() {
        System.out.println("Transfer: $" + amount + " to " + recipient.username);
    }

    @Override
    public String toString() {
        return "TransferTransaction{" +
                "amount=" + amount +
                ", recipient=" + recipient.username +
                '}';
    }
}
