package onlineshopping;

import java.util.Scanner;

public class ProductCategory {
    
    public ProductCategory() {
    }

    static Scanner sc = new Scanner(System.in);

    public static void displayCategoryMenu() {
        System.out.println("\n____________________________________________________________________________________________________________________\n");
        System.out.println("\t**************************************** Category ***************************************");
        System.out.println("\t                                  1. Tops & T-shirt");
        System.out.println("\t                                  2. Shirts & Blouses");
        System.out.println("\t                                  3. Jeans");
        System.out.println("\t                                  4. Back to Cart");
        System.out.println("\t*****************************************************************************************");

        System.out.print("\tEnter your choice (1-4): ");
        int choice = sc.nextInt();
        while (choice > 4 || choice < 1) {  // Fixed condition to properly handle input range
            System.out.print("\n\tInvalid choice. Please enter again (1-4): ");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                Product product = new Product();
                product.displayProductMenu(); // Call the product menu here
                break;
            case 2:
                Product2 product2 = new Product2();
                product2.displayProductMenuP2(); // Call the product menu here
                break;
            case 3:
                Product3 product3 = new Product3();
                product3.displayProductMenuP3(); // Call the product menu here
                break;
            default:
                System.out.println("____________________________________________________________________________________________________________________\n");
                System.out.println("\nReturning to Cart...");
                Cart.cartMenu(); // Call the Cart menu
                break;
        }
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "Categories=[" +
                "1. Tops & T-shirt, " +
                "2. Shirts & Blouses, " +
                "3. Jeans, " +
                "4. Back to Cart" +
                "]" +
                '}';
    }
}