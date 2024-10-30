package onlineshopping;

public class ExpressShipping extends Shipping {
    private final double expressRate;
    private int estimatedDays;

    public ExpressShipping(double baseRate, String deliveryTime, double expressRate, int estimatedDays) {
        super(baseRate, deliveryTime);
        this.expressRate = expressRate;
        this.estimatedDays = estimatedDays;
    }

    @Override
    public double calculateShippingCost(double totalAmount) {
        return expressRate;
    }

    @Override
    public String getEstimatedDeliveryTime() {
        return "\n\t" + estimatedDays + " days (Express)";
    }

    // Override the toString method to display shipping details
    @Override
    public String toString() {
        return "Express Shipping [Rate: RM" + expressRate + ", Estimated Delivery Time: " + estimatedDays + " days]";
    }
}