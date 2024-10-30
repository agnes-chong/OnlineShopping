package onlineshopping;

public class StandardShipping extends Shipping {
    
    public StandardShipping() {
    }
    
    private double standardRate;
    private int estimatedDays;

    public StandardShipping(double baseRate, String deliveryTime, double standardRate, int estimatedDays) {
        super(baseRate, deliveryTime);
        this.standardRate = standardRate;
        this.estimatedDays = estimatedDays;
    }

    @Override
    public double calculateShippingCost(double totalAmount) {
        return standardRate;
    }

    @Override
    public String getEstimatedDeliveryTime() {
        return "\n\t" + estimatedDays + " days";
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Standard Shipping { " +
                "standardRate=RM" + standardRate +
                ", estimatedDays=" + estimatedDays +
                ", baseRate=RM" + baseRate +
                ", deliveryTime='" + deliveryTime + '\'' +
                " }";
    }
}
