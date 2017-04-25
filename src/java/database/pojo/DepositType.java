package database.pojo;

/**
 * Created by kolinsol on 4/25/17.
 */
public class DepositType {
    private int id;
    private String name;
    private double percentage;
    private double minAmount;
    private double maxAmount;
    private int minPeriod;
    private int maxPeriod;

    public DepositType(int id, String name, double percentage,
                       double minAmount, double maxAmount,
                       int minPeriod, int maxPeriod) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.minPeriod = minPeriod;
        this.maxPeriod = maxPeriod;
    }

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
