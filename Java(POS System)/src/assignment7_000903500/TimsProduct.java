package assignment7_000903500;

/**
 * This is the parent class for all the products.
 * It contains the common attributes of the products.
 * @author Agam Singh, 000903500
 */

public class TimsProduct implements Commodity {
    private String name;
    private double cost;
    private double price;

    /**
     * Constructor for the class
     * @param name
     * @param cost
     * @param price
     */
    public TimsProduct(String name, double cost, double price) {
        this.name = name;
        this.cost = cost;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }


    @Override
    public double getProductionCost() {
        return cost;
    }

    @Override
    public double getRetailCost() {
        return price;
    }

    @Override
    public String toString() {
        return "TimsProduct[Name: \"" + name + "\", Cost: $" + cost + ", Price: $" + price + "]";
    }
}
