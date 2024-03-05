package assignment7_000903500;

import java.util.Scanner;

/**
 * This is the product which is made by myself.
 *
 * @author Agam Singh, 000903500
 */

public class WoodenPlanks extends TimsProduct {
    private double length;
    private double width;
    private double thickness;

    /**
     * Constructor to make the class
     * @param name
     * @param cost
     * @param price
     * @param length
     * @param width
     * @param thickness
     */
    private WoodenPlanks(String name, double cost, double price, double length, double width, double thickness) {
        super(name, cost, price);
        this.length = length;
        this.width = width;
        this.thickness = thickness;
    }

    /**
     * This is a method that fills all the arguments of constructor,
     * after taking input from user and returns the product
     * @return
     */
    public static WoodenPlanks create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Wooden Planks Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Wooden Planks Cost: $");
        double cost = sc.nextDouble();

        System.out.print("Enter Wooden Planks Price: $");
        double price = sc.nextDouble();

        System.out.print("Enter Wooden Planks Length: ");
        double length = sc.nextDouble();

        System.out.print("Enter Wooden Planks Width: ");
        double width = sc.nextDouble();

        System.out.print("Enter Wooden Planks Thickness: ");
        double thickness = sc.nextDouble();

        return new WoodenPlanks(name, cost, price, length, width, thickness);
    }

    /**
     * This is the toString method which calls the super
     * toString and also adds particular details of this product to it
     * and then prints it.
     * @return
     */

    @Override
    public String toString() {
        return super.toString() + "\n\tWoodenPlanks[Length: " + length + ", Width: " + width + ", Thickness: " + thickness + "]";
    }
}
