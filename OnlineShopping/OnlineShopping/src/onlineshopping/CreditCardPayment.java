package onlineshopping;

public class CreditCardPayment extends Payment {
    
    public CreditCardPayment() {
    }
    
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(double amount, String cardNumber, String cardHolderName) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean processPayment() {
        // Simulate payment processing
        System.out.println("\n\tProcessing credit card payment...");
        this.paymentStatus = true; // Assume payment is successful
        return this.paymentStatus;
    }

    // toString method to represent the credit card payment in string format
    @Override
    public String toString() {
        return String.format("CreditCardPayment[Amount: RM%.2f, CardHolder: %s, CardNumber: **** **** **** %s]", 
                             getAmount(), cardHolderName, cardNumber.substring(cardNumber.length() - 4));
    }
}