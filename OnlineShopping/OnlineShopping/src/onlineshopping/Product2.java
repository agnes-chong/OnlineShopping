package onlineshopping;

import java.util.Scanner;

public class Product2 extends ProductCategory {
    
    public Product2() {
    }

    private static Scanner sc = new Scanner(System.in);

    private int amount;
    private String again;

    private final int product1 = 150;
    private final int product2 = 120;
    private final int product3 = 130;
    private final int product4 = 200;

    private final String productName1 = "CoatesPin Shirt";
    private final String productName2 = "PleanBoot Shirt";
    private final String productName3 = "Cotton-XL Shirt";
    private final String productName4 = "Balloon blouse";

    ProductCategory backCategory = new ProductCategory();
    private Cart cart = new Cart(); // Create an instance of Cart

    public void displayProductMenuP2() {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t************************************ Shirts & Blouses ***********************************");
        System.out.println("\t                         Code          Name                       Price");
        System.out.println("\t                          6            CoatesPin Shirt            150/pcs");
        System.out.println("\t                          7            PleanBoot Shirt            120/pcs");
        System.out.println("\t                          8            Cotton-XL Shirt            130/pcs");
        System.out.println("\t                          9            Balloon blouse             200/pcs");
        System.out.println("\t                          10           Back to Product Category");
        System.out.println("\t*****************************************************************************************");
        System.out.println("\tWhat do you want to order today?");

        System.out.print("\tEnter Your Code: ");
        int codeId = sc.nextInt();

        while (codeId > 10 || codeId < 6) {
            System.out.println("\n\tInvalid Choice. Please enter (6-10).");
            System.out.print("\tEnter Your Code: ");
            codeId = sc.nextInt();
        }

        switch (codeId) {
            case 6:
                System.out.println("\n\tYou have selected CoatesPin Shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                double totalPrice = amount * product1;
                cart.add(codeId, productName1, product1, amount); // Add to Cart
                System.out.println();
                break;

            case 7:
                System.out.println("\n\tYou have selected PleanBoot Shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product2;
                cart.add(codeId, productName2, product2, amount); // Add to Cart
                System.out.println();
                break;

            case 8:
                System.out.println("\n\tYou have selected Cotton-XL Shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product3;
                cart.add(codeId, productName3, product3, amount); // Add to Cart
                System.out.println();
                break;

            case 9:
                System.out.println("\n\tYou have selected Balloon blouse.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product4;
                cart.add(codeId, productName4, product4, amount); // Add to Cart
                System.out.println();
                break;

            case 10:
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
            displayProductMenuP2();
        } else {
            System.out.println("\n____________________________________________________________________________________________________________________\n");
            System.out.println("\nReturning to Product Category...");
            ProductCategory.displayCategoryMenu(); // back to category
        }
    }

    public static void displayCategoryMenuP2() {
        Product2 product2 = new Product2();
        product2.displayProductMenuP2(); // Call the product menu here
    }

    @Override
    public String toString() {
        return "Product2{" +
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