package database.pojo;

/**
 * Created by kolinsol on 4/29/17.
 */
public class TransactionType {
    protected int id;
    protected String name;
    protected double percentage;
    protected double minAmount;
    protected double maxAmount;
    protected int minPeriod;
    protected int maxPeriod;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public int getMinPeriod() {
        return minPeriod;
    }

    public int getMaxPeriod() {
        return maxPeriod;
    }
}
