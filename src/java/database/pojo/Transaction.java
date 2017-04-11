package database.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by kolinsol on 3/27/17.
 */
public abstract class Transaction {
    private LocalDateTime createTime;
    private double amount;
    private float percentage;
    private LocalDate operationPeriod;


}
