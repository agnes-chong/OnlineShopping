package onlineshopping;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    
    private final int orderId;
    private Date orderDate;
    private double orderTotal;
    private ArrayList<Commodity> orderItems;

    public Order(int orderId, Date orderDate, double orderTotal, ArrayList<Commodity> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public ArrayList<Commodity> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderId)
                     .append("\nOrder Date: ").append(orderDate)
                     .append("\nOrder Total: RM").append(orderTotal)
                     .append("\nOrder Items:\n");

        for (Commodity item : orderItems) {
            orderDetails.append("\t").append(item.toString()).append("\n");
        }

        return orderDetails.toString();
    }
}