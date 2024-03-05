package assignment7_000903500;

import java.util.Scanner;

/**
 * This is one of the new Tim's product which is crested by myself.
 * This class also inherits RentableHardware class.
 * @author Agam Singh, 000903500
 */
public class BenchPlane extends RentableHardware {
    private double length;

    /**
     * This is the constructor for this class or product.
     * @param name
     * @param cost
     * @param price
     * @param rentalCost
     * @param length
     */
    private BenchPlane(String name, double cost, double price, double rentalCost, double length) {
        super(name, cost, price, rentalCost);
        this.length = length;
    }

    /**
     * This is a method that fills all the arguments of constructor,
     * after taking input from user and returns the product
     * @return
     */
    public static BenchPlane create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Bench Plane Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Bench Plane Cost: $");
        double cost = sc.nextDouble();

        System.out.print("Enter Bench Plane Price: $");
        double price = sc.nextDouble();

        System.out.print("Enter Bench Plane Rental Cost: $");
        double rentalCost = sc.nextDouble();

        boolean rented = false;
        System.out.print("Enter Electric Drill Status (rent/buy):");
        String rentChoice = sc.next().toLowerCase();
        if (rentChoice.equals("rent")) {
            rented = true;
        }

        System.out.print("Enter Bench Plane Length: ");
        double length = sc.nextDouble();

        BenchPlane benchPlane =  new BenchPlane (name, cost, price, rentalCost, length);

        if (rented) {
            benchPlane.rent(); // Call the rent() method to set rented to true
        }

        return benchPlane;
    }


    /**
     * This is the toString method which calls the super
     * toString and also adds particular details of this product to it
     * and then prints it.
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "\n\tBenchPlane[Length: " + length + "]";
    }
}

