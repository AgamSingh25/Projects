package assignment7_000903500;

import java.util.Scanner;

/**
 * This class associates with TimsProduct and makes a list
 * of all the products bought.
 * @author Agam Singh, 000903500
 */

public class TimsOrder {
    private String customerName;
    private TimsProduct[] products;

    /**
     * Constructor for the class
     * @param customerName
     * @param products
     */
    public TimsOrder(String customerName, TimsProduct[] products) {
        this.customerName = customerName;
        this.products = products;
    }

    /**
     * This is a method that fills all the arguments of constructor,
     * after taking input from user and returns the product.
     * Also this checks that what is teh user input.
     * @return
     */
    public static TimsOrder create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter Number of Products: ");
        int numProducts = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        TimsProduct[] products = new TimsProduct[numProducts];

        for (int i = 0; i < numProducts; i++) {
            System.out.println("Enter Product Type:");
            System.out.println("A. Box of Nails");
            System.out.println("B. Nail Gun");
            System.out.println("C. Bench Plane");
            System.out.println("D. Wooden Plank");
            System.out.print("Choice: ");
            String productType = sc.next().toLowerCase();
            sc.nextLine(); // Consume the newline character

            switch (productType) {
                case "a":
                    products[i] = BoxOfNails.create();
                    break;
                case "b":
                    products[i] = NailGun.create();
                    break;
                case "c":
                    products[i] = BenchPlane.create();
                    break;
                case "d":
                    products[i] = WoodenPlanks.create();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    i--; // Decrement i to retry the current product input
            }
        }

        return new TimsOrder(customerName, products);
    }

    public double getAmountDue() {
        double total = 0.0;
        for (TimsProduct product : products) {
            if (product instanceof RentableHardware) {
                RentableHardware rentable = (RentableHardware) product;
                if (rentable.isRented()) {
                    total += rentable.getRentalCost();
                } else {
                    total += rentable.getRetailCost();
                }
            } else {
                total += product.getRetailCost();
            }
        }
        return total;
    }

    /**
     * toString to display Customer name and total amount.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order for: " + customerName + "\n");
        for (TimsProduct product : products) {
            if (product != null) {
                sb.append(product.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
