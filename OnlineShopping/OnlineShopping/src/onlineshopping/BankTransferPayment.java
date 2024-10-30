package onlineshopping;

public class BankTransferPayment extends Payment {
    
    public BankTransferPayment() {
    }
    
    private String bankAccountNumber;
    private String bankName;
    
    public BankTransferPayment(double amount, String bankAccountNumber, String bankName) {
        super(amount);
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }

    @Override
    public boolean processPayment() {
        // Simulate payment processing
        System.out.println("\n\tProcessing bank transfer payment...");
        this.paymentStatus = true; // Assume payment is successful
        return this.paymentStatus;
    }

    @Override
    public String toString() {
        return "BankTransferPayment{" +
                "amount=" + getAmount() +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}