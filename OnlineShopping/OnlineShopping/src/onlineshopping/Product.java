package onlineshopping;

import java.util.Scanner;

public class Product extends ProductCategory {
    
    public Product() {
    }

    private static Scanner sc = new Scanner(System.in);

    private int amount;
    private String again;

    private final int product1 = 50;
    private final int product2 = 100;
    private final int product3 = 150;
    private final int product4 = 200;

    private final String productName1 = "SizeXL T-shirt";
    private final String productName2 = "Fitted T-shirt";
    private final String productName3 = "Long T-shirt";
    private final String productName4 = "Short T-shirt";

    private Cart cart = new Cart(); // Create an instance of Cart
    ProductCategory backCategory = new ProductCategory();

    public void displayProductMenu() {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t************************************* Tops & T-shirt ************************************");
        System.out.println("\t                         Code          Name                       Price");
        System.out.println("\t                          1            SizeXL T-shirt             50/pcs");
        System.out.println("\t                          2            Fitted T-shirt             100/pcs");
        System.out.println("\t                          3            Long T-shirt               150/pcs");
        System.out.println("\t                          4            Short T-shirt              200/pcs");
        System.out.println("\t                          5            Back to Category");
        System.out.println("\t*****************************************************************************************");
        System.out.println("\tWhat do you want to order today?");

        System.out.print("\tEnter Your Code: ");
        int codeId = sc.nextInt();

        while (codeId > 5 || codeId < 1) {
            System.out.println("\n\tInvalid Choice. Please enter (1-5).");
            System.out.print("\tEnter Your Code: ");
            codeId = sc.nextInt();
        }

        switch (codeId) {
            case 1:
                System.out.println("\n\tYou have selected SizeXL T-shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                double totalPrice = amount * product1;
                cart.add(codeId, productName1, product1, amount); // Add to Cart
                System.out.println();
                break;

            case 2:
                System.out.println("\n\tYou have selected Fitted T-shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product2;
                cart.add(codeId, productName2, product2, amount); // Add to Cart
                System.out.println();
                break;

            case 3:
                System.out.println("\n\tYou have selected Long T-shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product3;
                cart.add(codeId, productName3, product3, amount); // Add to Cart
                System.out.println();
                break;

            case 4:
                System.out.println("\n\tYou have selected Short T-shirt.\n");
                System.out.print("\tEnter Quantity: ");
                amount = sc.nextInt();
                totalPrice = amount * product4;
                cart.add(codeId, productName4, product4, amount); // Add to Cart
                System.out.println();
                break;

            case 5:
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
            displayProductMenu();
        } else {
            System.out.println("\n____________________________________________________________________________________________________________________\n");
            System.out.println("\nReturning to Product Category...");
            ProductCategory.displayCategoryMenu(); // back to category
        }
    }

    public static void displayCategoryMenu() {
        Product product = new Product();
        product.displayProductMenu(); // Call the product menu here
    }

    @Override
    public String toString() {
        return "Product{" +
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