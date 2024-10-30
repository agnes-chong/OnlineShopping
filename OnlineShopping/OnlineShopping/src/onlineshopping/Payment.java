package onlineshopping;

public abstract class Payment {
    
    public Payment() {
    }
    
    protected double amount;
    protected boolean paymentStatus;

    public Payment(double amount) {
        this.amount = amount;
        this.paymentStatus = false;
    }

    public abstract boolean processPayment();

    public boolean isPaymentSuccessful() {
        return paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\tAmount: " + amount + ", Payment Status: " + (paymentStatus ? "Success" : "Failed");
    }
}