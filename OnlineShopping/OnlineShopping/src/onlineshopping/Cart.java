package onlineshopping;

import java.util.ArrayList;
import java.util.Scanner;

public class Cart {
    
    public Cart() {
    }
    
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Commodity> list = new ArrayList<>();  // Holds the list of items in the cart

    // Add a product to the cart
    public void add(int codeId, String name, double pricePerUnit, int amount) {
        Commodity com = new Commodity(codeId, name, pricePerUnit, amount);
        list.add(com);
        System.out.println("\n\tProduct added successfully.");
    }

    // Display the cart menu options
    public static void displayCartMenu() {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t************************************* Shopping Cart *************************************");
        System.out.println("\t                                1. Edit Quantity");
        System.out.println("\t                                2. Delete Product");
        System.out.println("\t                                3. List Out Products");
        System.out.println("\t                                4. Back to Product Category");
        System.out.println("\t                                5. Check out");
        System.out.println("\t                                6. Back to Main Menu");
        System.out.println("\t*****************************************************************************************");
    }

    // Menu method that displays options and takes user input
    public static void cartMenu() {
        boolean isRunning = true;
        while (isRunning) {
            displayCartMenu();

            System.out.print("\tEnter your choice (1-6): ");
            int choice = sc.nextInt();

            // Validate user input
            while (choice > 6 || choice < 1) {
                System.out.println("\n\tInvalid choice. Please enter again (1-6): ");
                choice = sc.nextInt();
            }

            // Handle user choice
            switch (choice) {
                case 1 -> update();  // Edit the quantity of a product
                case 2 -> delete();  // Delete a product
                case 3 -> list();    // List out products
                case 4 -> ProductCategory.displayCategoryMenu();  // Back to Product Category
                case 5 -> Main.processCheckout();  // Proceed to checkout
                case 6 -> {
                    System.out.println("\n____________________________________________________________________________________________________________________\n");
                    System.out.println("\nReturning to Main Menu...");
                    isRunning = false;  // Exit the cart menu and return to the main menu
                }
            }
        }
    }

    // Update the quantity of an existing product in the cart
    public static void update() {
        if (list.isEmpty()) {
            System.out.println("\n\tYour cart is empty. Nothing to update.");
            return;
        }

        System.out.print("\tEnter the product code to update the amount: ");
        int codeId = sc.nextInt();
        System.out.print("\tEnter the new amount: ");
        int amount = sc.nextInt();

        boolean found = false;
        for (Commodity com : list) {
            if (com.getCodeId() == codeId) {
                if (amount <= 0) {
                    System.out.println("\tInvalid amount. Please enter a positive quantity.");
                    return;
                }
                com.setAmount(amount);  // Update the amount and total in the Commodity class
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("\n\tProduct quantity updated successfully.");
        } else {
            System.out.println("\n\tProduct not found.");
        }
    }

    // Delete a product from the cart
    public static void delete() {
        if (list.isEmpty()) {
            System.out.println("\n\tYour cart is empty. Nothing to delete.");
            return;
        }

        System.out.print("\tEnter the product code to delete: ");
        int codeId = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCodeId() == codeId) {
                list.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("\n\tProduct deleted successfully.");
        } else {
            System.out.println("\n\tProduct not found.");
        }
    }

    // List all products in the cart
    public static void list() {
        if (list.isEmpty()) {
            System.out.println("\n\tThe cart is empty.");
        } else {
            double total = 0;
            System.out.println("\n\t*****************************************************************************************");
            System.out.println("\t                                      Cart Items");
            System.out.println("\t*****************************************************************************************");
            System.out.println("\t\tCode\t\t\tName\t\t\tTotal(RM)\t\tQuantity");
            System.out.println("\t*****************************************************************************************");
            for (Commodity com : list) {
                System.out.println("\t\t" + com.getCodeId() + "\t\t\t" + com.getName() + "\t\t" + com.getTotal() + "\t\t\t" + com.getAmount());
                total += com.getTotal();
            }
            System.out.println("\n\tTotal amount: RM " + total);
        }
    }

    public static double calculateTotal() {
        double total = 0;
        for (Commodity com : list) {
            total += com.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder cartDetails = new StringBuilder();
        double total = 0;
        if (list.isEmpty()) {
            cartDetails.append("\n\tThe cart is empty.\n");
        } else {
            cartDetails.append("\t*****************************************************************************************\n");
            cartDetails.append("\n\tCart Items:\n");
            cartDetails.append("\t*****************************************************************************************\n");
            cartDetails.append("\t\tCode\t\t\tName\t\t\tTotal(RM)\t\tQuantity\n");
            cartDetails.append("\t*****************************************************************************************\n");
            for (Commodity com : list) {
                cartDetails.append("\t\t").append(com.getCodeId()).append("\t\t\t").append(com.getName()).append("\t\t").append(com.getTotal()).append("\t\t").append(com.getAmount()).append("\n");
                total += com.getTotal();
            }
            cartDetails.append("\n\tTotal amount: RM ");
        }
        return cartDetails.toString();
    }
}