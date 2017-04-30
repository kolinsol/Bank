package database.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by kolinsol on 3/27/17.
 */
public abstract class Transaction {
    protected Integer id;
    protected String code;
    protected int period;
    protected int personId;
    protected int currencyId;
    protected int transactionTypeId;
    protected String status;
    protected double amount;

    public Integer getId() {
        return id;
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

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}