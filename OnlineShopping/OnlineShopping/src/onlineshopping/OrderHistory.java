package onlineshopping;

import java.util.ArrayList;

public class OrderHistory {
    
    public OrderHistory() {
    }
    
    public static ArrayList<Order> orderHistory = new ArrayList<>(); // Stores the order history

    // Method to add an order to the history
    public static void addOrder(Order order) {
        orderHistory.add(order);
    }

    // Method to display the order history
    public static void showOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("\n\tNo orders have been placed yet.");
        } else {
            System.out.println("\n\t************************************* Order History *************************************\n");
            for (Order order : orderHistory) {
                System.out.println("\tOrder ID: " + order.getOrderId());
                System.out.println("\tOrder Date: " + order.getOrderDate());
                System.out.println("\tOrder Total: RM" + order.getOrderTotal());
                System.out.println("\tOrdered Items:");
                for (Commodity item : order.getOrderItems()) {
                    System.out.println("\t\t" + item.getName() + " - Quantity: " + item.getAmount() + " - Total: RM" + item.getTotal());
                }
            }
            System.out.println("\n\t*****************************************************************************************");
        }
    }

    // Overriding toString method to return a string representation of the order history
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (orderHistory.isEmpty()) {
            sb.append("\n\tNo orders have been placed yet.");
        } else {
            sb.append("\n\t***************************** Order History ***************************************");
            for (Order order : orderHistory) {
                sb.append("\n\tOrder ID: ").append(order.getOrderId())
                  .append("\n\tOrder Date: ").append(order.getOrderDate())
                  .append("\n\tOrder Total: RM").append(order.getOrderTotal())
                  .append("\n\tOrdered Items:");

                for (Commodity item : order.getOrderItems()) {
                    sb.append("\n\t\t").append(item.getName())
                      .append(" - Quantity: ").append(item.getAmount())
                      .append(" - Total: RM").append(item.getTotal());
                }
            }
            sb.append("\n\t**********************************************************************************");
        }
        return sb.toString();
    }
}