package onlineshopping;

public class FreeShipping extends Shipping {
    
    public FreeShipping() {
    }
    
    private double freeThreshold;
    private int estimatedDays;

    public FreeShipping(double baseRate, String deliveryTime, double freeThreshold, int estimatedDays) {
        super(baseRate, deliveryTime);
        this.freeThreshold = freeThreshold;
        this.estimatedDays = estimatedDays;
    }

    @Override
    public double calculateShippingCost(double totalAmount) {
        return (totalAmount > freeThreshold) ? 0 : baseRate;
    }

    @Override
    public String getEstimatedDeliveryTime() {
        return "\n\t" + estimatedDays + " days (Free Shipping)";
    }

    // Override the toString method to display shipping details
    @Override
    public String toString() {
        return "Free Shipping [Threshold: RM" + freeThreshold + ", Base Rate: RM" + baseRate + 
               ", Estimated Delivery Time: " + estimatedDays + " days]";
    }
}