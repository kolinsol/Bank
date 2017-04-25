package database.pojo;

/**
 * Created by kolinsol on 3/27/17.
 */
public class Deposit extends Transaction {

    public Deposit(String code, int period, int personId,
                   int currencyId, int depositTypeId,
                   DepositStatus depositStatus, double amount) {
        this.code = code;
        this.period = period;
        this.personId = personId;
        this.currencyId = currencyId;
        this.depositTypeId = depositTypeId;
        this.status = depositStatus.getStatusValue();
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public int getPeriod() {
        return period;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public int getDepositTypeId() {
        return depositTypeId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}
