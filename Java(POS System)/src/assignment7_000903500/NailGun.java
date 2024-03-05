package assignment7_000903500;

import java.util.Scanner;

/**
 * This is also a product which is given in teh UML diagram.
 * This class inherits RentableHardware class
 * @author Agam Singh, 000903500
 */

public class NailGun extends RentableHardware {
    private String features;

    /**
     * This is the constructor for this class or product.
     * @param name
     * @param cost
     * @param price
     * @param rentalCost
     * @param features
     */
    private NailGun(String name, double cost, double price, double rentalCost, String features) {
        super(name, cost, price, rentalCost);
        this.features = features;
    }

    /**
     * This is a method that fills all the arguments of constructor,
     * after taking input from user and returns the product
     * @return
     */
    public static NailGun create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Nail Gun Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Nail Gun Cost: $");
        double cost = sc.nextDouble();

        System.out.print("Enter Nail Gun Price: $");
        double price = sc.nextDouble();

        System.out.print("Enter Nail Gun Rental Cost: $");
        double rentalCost = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Nail Gun Features: ");
        String features = sc.nextLine();

        System.out.print("Enter Electric Drill Status (rent/buy): ");
        String rentChoice = sc.next().toLowerCase();
        sc.nextLine();

        boolean rented = false;
        if (rentChoice.equals("rent")) {
            rented = true;
        }

        NailGun nailGun = new NailGun(name, cost, price, rentalCost, features);

        if (rented) {
            nailGun.rent(); // Call the rent() method to set rented to true
        }

        return nailGun;
    }

    /**
     * getter for the features of nail gun
     * @return
     */
    public String getFeatures() {
        return features;
    }

    /**
     * This is the toString method which calls the super
     * toString and also adds particular details of this product to it
     * and then prints it.
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tNailGun[Features: \"" + features + "\"]";
    }
}

