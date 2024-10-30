package onlineshopping;

import java.util.Scanner;

public class Product3 extends ProductCategory {
    
    public Product3() {
    }

    private static Scanner sc = new Scanner(System.in);

    private int amount;
    private String again;

    private final int product1 = 80;
    private final int product2 = 150;
    private final int product3 = 100;
    private final int product4 = 250;

    private final String productName1 = "Fitted Wid Jean";
    private final String productName2 = "Baggy Wide Jean";
    private final String productName3 = "Wide Norma Jean";
    private final String productName4 = "Skin High Jean";

    ProductCategory backCategory = new ProductCategory();
    private Cart cart = new Cart(); // Create an instance of Cart

    public void displayProductMenuP3() {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t***************************************** Jeans *****************************************");
        System.out.println("\t                         Code           Name                       Price");
        System.out.println("\t                          11            Fitted Wid Jean            80/pcs");
        System.out.println("\t                          12            Baggy Wide Jean            150/pcs");
        System.out.println("\t                          13            Wide Norma Jean            100/pcs");
        System.out.println("\t                          14            Skin High Jean             250/pcs");
        System.out.println("\t                          15            Back to Product Category");
        System.out.println("\t*****************************************************************************************");
        System.out.println("\tWhat do you want to order today?");

        System.out.print("\tEnter Your Code: ");
        int codeId = sc.nextInt();

        while (codeId > 15 || codeId < 11) {
            System.out.println("\n\tInvalid Choice. Please enter (11-15).");
            System.out.print("\tEnter Your Code: ");
            codeId = sc.nextInt();
        }

        switch (codeId) {
            case 11:
                System.out.println("\n\tYou have selected Fitted Wid Jean.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                double totalPrice = amount * product1;
                cart.add(codeId, productName1, product1, amount); // Add to Cart
                System.out.println();
                break;

            case 12:
                System.out.println("\n\tYou have selected Baggy Wide Jean.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product2;
                cart.add(codeId, productName2, product2, amount); // Add to Cart
                System.out.println();
                break;

            case 13:
                System.out.println("\n\tYou have selected Wide Norma Jean.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product3;
                cart.add(codeId, productName3, product3, amount); // Add to Cart
                System.out.println();
                break;

            case 14:
                System.out.println("\n\tYou have selected Skin High Jean.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product4;
                cart.add(codeId, productName4, product4, amount); // Add to Cart
                System.out.println();
                break;

            case 15:
                System.out.println("\n____________________________________________________________________________________________________________________\n");
                System.out.println("\nReturning to Product Category...");
                ProductCategory.displayCategoryMenu(); // back to category
                return;
        }

        System.out.print("\tDo you wish to order anything else? (Y/N): ");
        again = sc.next();

        while (!again.equalsIgnoreCase("Y") && !again.equalsIgnoreCase("N")) {
            System.out.print("\n\tInvalid Choice. Please enter (Y/N): ");
            again = sc.next();
        }

        if (again.equalsIgnoreCase("Y")) {
            displayProductMenuP3();
        } else {
            System.out.println("\n____________________________________________________________________________________________________________________\n");
            System.out.println("\nReturning to Product Category...");
            ProductCategory.displayCategoryMenu(); // back to category
        }
    }

    public static void displayCategoryMenuP3() {
        Product3 product3 = new Product3();
        product3.displayProductMenuP3(); // Call the product menu here
    }

    @Override
    public String toString() {
        return "Product3{" +
                "product1=" + product1 +
                ", productName1='" + productName1 + '\'' +
                ", product2=" + product2 +
                ", productName2='" + productName2 + '\'' +
                ", product3=" + product3 +
                ", productName3='" + productName3 + '\'' +
                ", product4=" + product4 +
                ", productName4='" + productName4 + '\'' +
                '}';
    }
}