package onlineshopping;

public abstract class Shipping {
    
    public Shipping() {
    }
    
    protected double baseRate;
    protected String deliveryTime;

    public Shipping(double baseRate, String deliveryTime) {
        this.baseRate = baseRate;
        this.deliveryTime = deliveryTime;
    }

    public abstract double calculateShippingCost(double totalAmount);

    public String getShippingMethod() {
        return this.getClass().getSimpleName();  // Returns the type of shipping
    }

    public String getEstimatedDeliveryTime() {
        return deliveryTime;
    }

    @Override
    public String toString() {
        return "\tShipping Method: " + getShippingMethod() + ", Estimated Delivery Time: " + getEstimatedDeliveryTime();
    }
}