package database.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by kolinsol on 3/27/17.
 */
public abstract class Transaction {
    protected String code;
    protected int period;
    protected int personId;
    protected int currencyId;
    protected int depositTypeId;
    protected String status;
    protected double amount;
}
