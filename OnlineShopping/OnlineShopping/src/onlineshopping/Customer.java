package onlineshopping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private final String name;
    private final String email;
    private final String password;
    private final LocalDate birthday;
    private int numberOfPurchases;
    private final List<Order> orderHistory;

    // List to store all registered customers
    private static List<Customer> registeredCustomers = new ArrayList<>();

    public Customer(String name, String email, String password, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.numberOfPurchases = 0;
        this.orderHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public void incrementNumberOfPurchases() {
        this.numberOfPurchases++;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    // Method to register a customer
    public static void register(String name, String email, String password, LocalDate birthday) {
        Customer newCustomer = new Customer(name, email, password, birthday);
        registeredCustomers.add(newCustomer);
        System.out.println("\n\tRegistration successful for " + name + " with email " + email);
    }

    // Method to login a customer
    public static boolean login(String email, String password) {
        // Check if the customer exists in the registered customers list
        for (Customer customer : registeredCustomers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                System.out.println("\n\tLogin successful for email " + email);
                return true;
            }
        }
        System.out.println("\n\tLogin failed. Invalid email or password.");
        return false;
    }

    // toString method to provide a string representation of the Customer object
    @Override
    public String toString() {
        return String.format("Customer[Name: %s, Email: %s, Birthday: %s, Number of Purchases: %d]", 
                             name, email, birthday.toString(), numberOfPurchases);
    }
}