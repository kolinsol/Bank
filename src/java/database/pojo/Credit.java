package database.pojo;

/**
 * Created by kolinsol on 3/27/17.
 */
public class Credit extends Transaction {

    public Credit(String code, int period, int personId,
                   int currencyId, int transactionTypeId,
                   TransactionStatus transactionStatus, double amount) {
        this.code = code;
        this.period = period;
        this.personId = personId;
        this.currencyId = currencyId;
        this.transactionTypeId = transactionTypeId;
        this.status = transactionStatus.getStatusValue();
        this.amount = amount;
    }

    public Credit(Integer id, String code, int period, int personId,
                   int currencyId, int transactionTypeId,
                   String status, double amount) {
        this.id = id;
        this.code = code;
        this.period = period;
        this.personId = personId;
        this.currencyId = currencyId;
        this.transactionTypeId = transactionTypeId;
        this.status = status;
        this.amount = amount;
    }
}