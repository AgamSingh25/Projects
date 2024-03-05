package assignment7_000903500;

import java.util.Scanner;

/**
 * This is the product that was given in the UMl diagram.
 * This Class inherits TimsProduct class.
 * @author Agam Singh, 000903500
 */

public class BoxOfNails extends TimsProduct {
    private double size;
    private int quantity;

    /**
     * This is the constructor for this class or product.
     * @param name
     * @param cost
     * @param price
     * @param size
     * @param quantity
     */
    private BoxOfNails(String name, double cost, double price, double size, int quantity) {
        super(name, cost, price);
        this.size = size;
        this.quantity = quantity;
    }

    /**
     * This is a method that fills all the arguments of constructor,
     * after taking input from user and returns the product
     * @return
     */
    public static BoxOfNails create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Box of Nails Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Box of Nails Cost: $");
        double cost = sc.nextDouble();

        System.out.print("Enter Box of Nails Price: $");
        double price = sc.nextDouble();

        System.out.print("Enter Box of Nails Size: ");
        double size = sc.nextDouble();

        System.out.print("Enter Box of Nails Quantity Per Box: ");
        int quantity = sc.nextInt();

        return new BoxOfNails(name, cost, price, size, quantity);
    }

    /**
     * getter method to get size of the nails
     * @return
     */
    public double getSize() {
        return size;
    }

    /**
     * getter method to get quantity of th box
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This is the toString method which calls the super
     * toString and also adds particular details of this product to it
     * and then prints it.
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tBoxOfNails[Size: " + size + ", Quantity: " + quantity + "]";
    }
}
