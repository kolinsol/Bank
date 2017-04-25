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
    private Integer amount;

    public Account(AccountType accountType, Person person) {
        this.personId = person.getPersonId();
        this.code = accountType.getCode()+
                person.getPassport().getSerialNumber().substring(2)+
                (int)(Math.random()*(99 - 10) + 10);
        this.type = accountType.getTypeValue();
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

    public Integer getAmount() {
        return amount;
    }
}
