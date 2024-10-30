package onlineshopping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    
    public Main() {
    }
    
    static Scanner scanner = new Scanner(System.in);
    static Customer loggedInCustomer;

    public static void main(String[] args) {
        try {
            customerPage(); // Handle login/registration
            boolean isRunning = true;
            while (isRunning) {
                displayMainMenu();
                int choice = getValidIntegerInput();
                switch (choice) {
                    case 1:
                        ProductCategory.displayCategoryMenu();
                        break;
                    case 2:
                        Cart.cartMenu();
                        break;
                    case 3:
                        OrderHistory.showOrderHistory(); // Display order history here
                        break;
                    case 4:
                        processCheckout();
                        break;
                    case 5:
                        System.out.println("\n____________________________________________________________________________________________________________________\n");
                        System.out.println("\n\tExiting the system...");
                        System.out.println("\tThank you :)");
                        System.out.println("\t ()_____()");
                        System.out.println("\t | ^   ^ |");
                        System.out.println("\t |_______|");
                        System.out.println("\n____________________________________________________________________________________________________________________\n");

                        isRunning = false;
                        break;
                    default:
                        System.out.println("\n\tInvalid Choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void customerPage() {
        boolean isLoggedIn = false;
        String name = "";
        String email = "";
        String password = "";
        LocalDate birthday = null;

        while (!isLoggedIn) {
            System.out.println("\n____________________________________________________________________________________________________________________\n");
            System.out.println("\t*************************************** Login Page **************************************");
            System.out.println("\t                                     1. Register");
            System.out.println("\t                                     2. Login");
            System.out.println("\t                                     3. Exit");
            System.out.println("\t*****************************************************************************************");
            System.out.print("\tEnter your choice (1-3): ");
            int option = getValidIntegerInput();

            if (option == 1) {
                // Registration process
                System.out.println("\n____________________________________________________________________________________________________________________\n");
                System.out.println("\t******************************** Register as a new member! ******************************");
                System.out.print("\tEnter your name: ");
                name = scanner.nextLine();

                // Email validation
                email = getValidEmail();

                // Password validation
                password = getValidPassword();

                // Birthday validation
                birthday = getValidBirthday();

                Customer.register(name, email, password, birthday);
            } else if (option == 2) {
                // Login process
                System.out.println();
                email = getValidEmail();
                password = getValidPassword();

                isLoggedIn = Customer.login(email, password);

                if (isLoggedIn) {
                    System.out.println("\n\tProceeding to Main Menu...");
                    loggedInCustomer = new Customer(name, email, password, birthday); // Initialize the logged-in customer
                }
            } else if (option == 3) {
                System.out.println("\n____________________________________________________________________________________________________________________\n");
                System.out.println("\n\tExiting the system...");
                System.out.println("\tPlease Welcome Again :)");
                System.out.println("\n____________________________________________________________________________________________________________________\n");
                System.exit(0);
            } else {
                System.out.println("\n\tInvalid Choice. Please try again.");
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t*************************************** Main Menu ***************************************");
        System.out.println("\t                                  1. Product Categories");
        System.out.println("\t                                  2. Cart");
        System.out.println("\t                                  3. Order History"); // Option to view order history
        System.out.println("\t                                  4. Checkout");
        System.out.println("\t                                  5. Exit");
        System.out.println("\t*****************************************************************************************");
        System.out.print("\tEnter your choice (1-5): ");
    }

    public static void processCheckout() {
        Cart.list();  // Display cart contents

        if (Cart.calculateTotal() <= 0) {
            System.out.println("\n\tYour cart is empty. Add products before proceeding to checkout.");
            return;
        }

        double totalAmount = Cart.calculateTotal();

        // Select shipping method
        Shipping shipping = selectShippingMethod(totalAmount);
        double shippingCost = shipping.calculateShippingCost(totalAmount);
        String estimatedDeliveryTime = shipping.getEstimatedDeliveryTime();

        // Calculate total amount including shipping
        double totalAmountWithShipping = totalAmount + shippingCost;
        System.out.println("\n\tShipping Method: " + shipping.getShippingMethod());
        System.out.printf("\tShipping Cost: RM%.2f%n", shippingCost);
        System.out.printf("\tTotal Amount (including shipping): RM%.2f%n\n", totalAmountWithShipping);

        // Calculate discount
        double totalDiscountRate = Discount.calculateTotalDiscount(loggedInCustomer, totalAmountWithShipping);
        double discountAmount = totalAmountWithShipping * totalDiscountRate;
        double finalAmount = totalAmountWithShipping - discountAmount;

        if (totalDiscountRate > 0) {
            System.out.printf("\tYou saved RM%.2f with your discounts!%n", discountAmount);
        }

        System.out.printf("\tFinal amount to pay (after discounts): RM%.2f%n", finalAmount);

        try {
            Payment payment = PaymentUtility.selectPaymentMethod(finalAmount);
            if (payment != null && payment.processPayment()) {
                System.out.println("\n\tPayment Successful for amount: RM" + finalAmount);

                // Create order and add to order history
                Order order = new Order(OrderHistory.orderHistory.size() + 1, new Date(), finalAmount, new ArrayList<>(Cart.list));
                OrderHistory.addOrder(order); // Add order to history
                System.out.println("\tOrder placed successfully. Order ID: " + order.getOrderId());

                // Generate the receipt
                printReceipt(order, totalAmount, discountAmount, finalAmount);

                // Clear the cart
                Cart.list.clear();
                System.out.println("\tCart cleared after successful payment.\n");

                // Ask for a review
                handleReviewSubmission();
                
                System.out.println("____________________________________________________________________________________________________________________\n");
                System.out.println("\nReturning to Cart...");
            } else {
                System.out.println("\n\tPayment Failed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during payment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printReceipt(Order order, double subTotal, double discount, double finalAmount) {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t*****************************************************************************************");
        System.out.println("\t                                          RECEIPT");
        System.out.println("\t*****************************************************************************************\n");
        System.out.println("\tCustomer: " + loggedInCustomer.getEmail());
        System.out.println("\tDate: " + new Date());

        System.out.println("\n\tProducts:");
        for (Commodity product : Cart.list) {
            System.out.printf("\t\t%s (x%d): RM%.2f%n\n", product.getName(), product.getAmount(), product.getTotal());
        }

        System.out.printf("\tSub-total: RM%.2f%n", subTotal);
        System.out.printf("\tDiscount: RM%.2f%n", discount);
        System.out.printf("\tTotal: RM%.2f%n", finalAmount);

        System.out.println("\n\t*****************************************************************************************");
        System.out.println("\t                              Thank you for shopping with us!");
        System.out.println("\t*****************************************************************************************");
        System.out.println("___________________________________________________________________________________________________________________\n");
    }
    
    static ReviewModule reviewModule = new ReviewModule();
    public static void handleReviewSubmission() {
        boolean exit = false;
        String customerName = "";
        String comment = "";
        int rating = -1;

        while (!exit) {
            // Ask customer for a review
            System.out.println("How was your experience with the purchase?");
            comment = scanner.nextLine();

            // Ask customer for a rating (1-5)
            System.out.println("How would you rate your experience? (1-5)");
            rating = -1;
            while (rating < 1 || rating > 5) {
                try {
                    rating = Integer.parseInt(scanner.nextLine());
                    if (rating < 1 || rating > 5) {
                        System.out.println("Please enter a valid rating between 1 and 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for the rating (1-5).");
                }
            }

            // Ask for the customer name
            System.out.println("Please enter your name:");
            customerName = scanner.nextLine();

            // Submit the review
            reviewModule.submitReview(customerName, comment, rating);

            // Display the comment the user wrote
            System.out.println("\nHere is the review you wrote:");
            System.out.println("Customer: " + customerName);
            System.out.println("Rating: " + rating + "/5");
            System.out.println("Comment: " + comment + "\n");

            // Ask user if they want to delete the review (with validation for "yes", "y", "Y", "no", "n", "N")
            String decision = "";
            while (!isYesNoValid(decision)) {
                System.out.println("Would you like to delete your review? (yes/no)");
                decision = scanner.nextLine().trim().toLowerCase();
                if (!isYesNoValid(decision)) {
                    System.out.println("Please enter a valid response: yes, y or no, n.");
                }
                // Only show the thank you message if the review wasn't deleted
                if (isNo(decision)) {
                System.out.println("Thank you for your comment!");
                }
            }

            if (isYes(decision)) {
                // Call deleteReview only if the decision is "yes"
                reviewModule.deleteReview(customerName);
            }

            // Ask if the user wants to continue (with validation for "yes", "y", "Y", "no", "n", "N")
            String continueDecision = "";
            while (!isYesNoValid(continueDecision)) {
                System.out.println("Do you want to submit another review? (yes/no)");
                continueDecision = scanner.nextLine().trim().toLowerCase();
                if (!isYesNoValid(continueDecision)) {
                    System.out.println("Please enter a valid response: yes, y or no, n.");
                }
            }

            if (isNo(continueDecision)) {
                exit = true;
            }
        }

        System.out.println("\n\tAll reviews:");
        reviewModule.showReviews();
    }

    // Helper Methods
    public static int getValidIntegerInput() {
        int input = -1;
        while (input < 0) {
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("\nInvalid Number. Please enter a valid number.");
            }
        }
        return input;
    }

    public static int getValidRating() {
        int rating = -1;
        while (rating < 1 || rating > 5) {
            try {
                System.out.print("\tHow would you rate your experience (1-5)? ");
                rating = Integer.parseInt(scanner.nextLine());
                if (rating < 1 || rating > 5) {
                    System.out.print("\n\tInvalid Number. Please enter a valid number between 1 and 5.\n");
                }
            } catch (NumberFormatException e) {
                System.out.print("\n\tInvalid Number. Please enter a valid number for the rating (1-5).\n");
            }
        }
        return rating;
    }
    
    public static boolean isYesNoValid(String input) {
    return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y") ||
           input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n");
}

public static boolean isYes(String input) {
    return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
}

public static boolean isNo(String input) {
    return input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n");
}

    public static String getValidEmail() {
        String email = "";
        while (!isValidEmail(email)) {
            System.out.print("\tEnter your email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("\n\tInvalid email format! Please try again.\n");
            }
        }
        return email;
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static String getValidPassword() {
        String password = "";
        while (!isValidPassword(password)) {
            System.out.print("\tEnter your password (min 6 characters): ");
            password = scanner.nextLine();
            if (!isValidPassword(password)) {
                System.out.println("\n\tPassword must be at least 6 characters long! Please try again.\n");
            }
        }
        return password;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    public static LocalDate getValidBirthday() {
        String birthdayInput = "";
        while (!isValidDate(birthdayInput)) {
            System.out.print("\tEnter your birthday (yyyy-MM-dd): ");
            birthdayInput = scanner.nextLine();
            if (!isValidDate(birthdayInput)) {
                System.out.println("\n\tInvalid date format! Please try again.\n");
            }
        }
        return LocalDate.parse(birthdayInput);
    }

    public static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static Shipping selectShippingMethod(double totalAmount) {
        Shipping shipping = null;

        while (true) {
            System.out.println("\n____________________________________________________________________________________________________________________\n");
            System.out.println("\t************************************ Shipping method ************************************");
            System.out.println("\t                              1. Standard Shipping (RM5.00)");
            System.out.println("\t                              2. Express Shipping (RM10.00)");
            System.out.println("\t                              3. Free Shipping (Minimum spend RM100.00)");
            System.out.println("\t*****************************************************************************************");
            System.out.print("\tEnter your choice (1-3): ");
            int shippingChoice = getValidIntegerInput();

            if (shippingChoice == 1) {
                shipping = new StandardShipping(5.00, "3-5 business days", 5.00, 5);
                break;
            } else if (shippingChoice == 2) {
                shipping = new ExpressShipping(10.00, "1-2 business days", 10.00, 2);
                break;
            } else if (shippingChoice == 3 && totalAmount >= 100.00) {
                shipping = new FreeShipping(0.00, "5-7 business days", 100.00, 7);
                break;
            } else {
                System.out.println("\n\tInvalid choice or insufficient amount for free shipping. Please select a valid shipping method.");
            }
        }
        return shipping;
    }

    public static void returnToMainMenu() {
        System.out.println("\n\tReturning to Main Menu...");
    }
}