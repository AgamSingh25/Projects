package assignment7_000903500;

/**
 * This is the testing class, which runs the whole code
 * @author Agam Singh, 000903500
 */

public class TestClass {
    public static void main(String[] args) {
        TimsOrder t = TimsOrder.create();
        System.out.println(t);
        System.out.printf("Total Price: $%.2f\n", t.getAmountDue());
    }
}
