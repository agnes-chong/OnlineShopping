package onlineshopping;

import java.util.Scanner;

public class PaymentUtility {
    
    public PaymentUtility() {
    }

    private static Scanner scanner = new Scanner(System.in);

    public static Payment selectPaymentMethod(double totalAmountWithShipping) {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t************************************* Payment Method ************************************");
        System.out.println("\t                                  1. Credit Card ");
        System.out.println("\t                                  2. Bank Transfer");
        System.out.println("\t*****************************************************************************************");
        System.out.print("\tEnter your choice (1/2): ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Payment payment = null;

        switch (paymentChoice) {
            case 1:
                // Credit Card Payment
                System.out.print("\tEnter credit card holder's name: ");
                String cardHolderName = scanner.nextLine();
                String cardNumber;
                // Validate that the card number is 16 digits
                while (true) {
                    System.out.print("\tEnter credit card number (16 digits): ");
                    cardNumber = scanner.nextLine();
                    
                    if (cardNumber.matches("\\d{16}")) {
                        break; // Valid card number, break the inner loop
                    } else {
                        System.out.println("\n\tInvalid input! Please enter exactly 16 digits.");
                    }
                }   // Create a CreditCardPayment object
                payment = new CreditCardPayment(totalAmountWithShipping, cardNumber, cardHolderName);
                break;
            case 2:
                // Bank Transfer Payment
                String bankAccountNumber;
                // Validate that the bank account number is 10 digits
                while (true) {
                    System.out.print("\tEnter bank account number (10 digits): ");
                    bankAccountNumber = scanner.nextLine();
                    
                    if (bankAccountNumber.matches("\\d{10}")) {
                        break; // Valid account number, break the inner loop
                    } else {
                        System.out.println("\n\tInvalid input! Please enter exactly 10 digits.");
                    }
                }   // Loop until a valid bank choice is made
                String bankName = "";
                while (true) {
                    System.out.println("\n\tChoose the bank: ");
                    System.out.println("\t1. Public Bank");
                    System.out.println("\t2. Maybank");
                    System.out.println("\t3. Hong Leong Bank");
                    System.out.println("\t4. OCBC Bank");
                    System.out.println("\t5. UOB Bank");
                    System.out.print("\tPlease enter your choice (1-5): ");
                    
                    int bankChoice = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    
                    switch (bankChoice) {
                        case 1 -> bankName = "Public Bank";
                        case 2 -> bankName = "Maybank";
                        case 3 -> bankName = "Hong Leong Bank";
                        case 4 -> bankName = "OCBC Bank";
                        case 5 -> bankName = "UOB Bank";
                        default -> {
                            System.out.println("\n\tInvalid Choice. Please select a valid bank.");
                            continue; // Invalid choice, retry
                        }
                    }
                    break; // Valid choice, exit loop
                }   // Create a BankTransferPayment object
                payment = new BankTransferPayment(totalAmountWithShipping, bankAccountNumber, bankName);
                break;
            default:
                System.out.println("\n\tInvalid Choice. Please select a valid payment method.");
                return null; // Invalid choice, restart checkout process
        }
        return payment;
    }

    // Overriding toString method to return a description of the PaymentUtility
    @Override
    public String toString() {
        return "Payment Utility facilitates selecting and processing different payment methods.";
    }
}