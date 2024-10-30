package onlineshopping;

import java.time.LocalDate;

public class Discount {
    
    public Discount() {
    }

    // Constants for thresholds and discount rates
    private static final double BIRTHDAY_DISCOUNT_RATE = 0.10; // 10% birthday discount
    private static final double SPENDING_THRESHOLD = 100.00;   // RM100 threshold for spending discount
    private static final double SPENDING_DISCOUNT_RATE = 0.05; // 5% discount for spending above threshold
    private static final int LOYALTY_PURCHASES_THRESHOLD = 10; // 10 purchases for loyalty discount
    private static final double LOYALTY_DISCOUNT_RATE = 0.05;  // 5% loyalty discount
    
    // Method to calculate the birthday discount
    public static double calculateBirthdayDiscount(Customer customer) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = customer.getBirthday();
        
        // Compare only the month and day of the birthdate with today's month and day
        if (today.getMonth() == birthday.getMonth() && today.getDayOfMonth() == birthday.getDayOfMonth()) {
            System.out.println("\tHappy Birthday, " + customer.getName() + "! You get a 10% discount!");
            return BIRTHDAY_DISCOUNT_RATE;
        }
        return 0.0; // No birthday discount
    }

    // Method to calculate spending-based discount
    public static double calculateSpendingDiscount(double amount) {
        if (amount > SPENDING_THRESHOLD) {
            System.out.printf("\tYou qualify for a 5%% discount for spending over RM%.2f!%n", SPENDING_THRESHOLD);
            return SPENDING_DISCOUNT_RATE;
        }
        return 0.0; // No spending discount
    }

    // Method to calculate loyalty discount based on the number of purchases
    public static double calculateLoyaltyDiscount(Customer customer) {
        if (customer.getNumberOfPurchases() >= LOYALTY_PURCHASES_THRESHOLD) {
            System.out.println("Thank you for being a loyal customer! You get a 5% loyalty discount!");
            return LOYALTY_DISCOUNT_RATE;
        }
        return 0.0; // No loyalty discount
    }

    // Method to calculate the total discount
    public static double calculateTotalDiscount(Customer customer, double amount) {
        double birthdayDiscount = calculateBirthdayDiscount(customer);
        double spendingDiscount = calculateSpendingDiscount(amount);
        double loyaltyDiscount = calculateLoyaltyDiscount(customer);

        // Sum of all applicable discounts
        return birthdayDiscount + spendingDiscount + loyaltyDiscount;
    }

    // toString method to provide a string representation of Discount object
    @Override
    public String toString() {
        return String.format("Discount[BIRTHDAY_DISCOUNT_RATE: %.2f, SPENDING_THRESHOLD: RM%.2f, SPENDING_DISCOUNT_RATE: %.2f, LOYALTY_PURCHASES_THRESHOLD: %d, LOYALTY_DISCOUNT_RATE: %.2f]", 
                             BIRTHDAY_DISCOUNT_RATE, SPENDING_THRESHOLD, SPENDING_DISCOUNT_RATE, LOYALTY_PURCHASES_THRESHOLD, LOYALTY_DISCOUNT_RATE);
    }
}