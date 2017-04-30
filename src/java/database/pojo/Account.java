package database.pojo;

import java.util.Random;

/**
 * Created by kolinsol on 4/24/17.
 */
public class Account {
    private String code;
    private String type;
    private Integer personId;
    private Integer currencyId;
    private Integer transactionId;
    private Double amount;

    public Account(AccountType accountType, Person person) {
        this.personId = person.getPersonId();
        this.code = accountType.getCode()+
                person.getPassport().getSerialNumber().substring(2)+
                (int)(Math.random()*(99 - 10) + 10);
        this.type = accountType.getTypeValue();
    }

    public Account(AccountType accountType, Transaction transaction, Integer personId) {
        this.personId = personId;
        this.type = accountType.getTypeValue();
        this.currencyId = transaction.getCurrencyId();
        this.code = accountType.getCode()+
                transaction.getCode().substring(1)+
                (int)(Math.random()*(999 - 100) + 100);
        this.amount = transaction.getAmount();
//        if (accountType.getTypeValue().contains("PERCENTAGE")) {
//            this.amount = 0.0;
//        }
        this.transactionId = transaction.getId();
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }
}