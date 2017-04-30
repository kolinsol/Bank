package database.pojo;

/**
 * Created by kolinsol on 4/25/17.
 */
public class DepositType extends TransactionType{

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
}