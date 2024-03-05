package assignment7_000903500;

/**
 * Thsi is teh parent class for both NailGun and BenchPlane.
 * this class tells that teh product is rented or not and also tells
 * rental cost.
 * @author Agam Singh, 000903500
 */

public class RentableHardware extends TimsProduct implements Rentable {
    private boolean rented;
    private double rentalCost;

    /**
     * Constructor for the class
     * @param name
     * @param cost
     * @param price
     * @param rentalCost
     */
    public RentableHardware(String name, double cost, double price, double rentalCost) {
        super(name, cost, price);
        this.rented = false;
        this.rentalCost = rentalCost;
    }

    /**
     * getter for rented
     */
    @Override
    public void rent() {
        this.rented = true;
    }

    /**
     * getter for isRented
     * @return
     */
    @Override
    public boolean isRented() {
        return rented;
    }

    /**
     * getter for returned
     */
    @Override
    public void returned() {
        this.rented = false;
    }

    /**
     * getter for rental cost
     * @return
     */
    public double getRentalCost() {
        return rentalCost;
    }

    /**
     * toString to dispaly result
     * @return
     */

    @Override
    public String toString() {
        String rentalStatus;
        if (rented) {
            rentalStatus = "Rented";
        } else {
            rentalStatus = "Not Rented";
        }
        return super.toString() + "\n\tRentableHardware[Rental Cost: $" + rentalCost + ", " + rentalStatus + "]";
    }
}
