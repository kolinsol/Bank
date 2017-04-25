package database.pojo;

/**
 * Created by kolinsol on 4/18/17.
 */
public enum AccountType {
    PERSONAL("PERSONAL", "3014"),
    DEPOSIT("DEPOSIT", "3404"),
    DEPOSIT_PERCENTAGE("DEPOSIT-PERCENTAGE", "3414");

    private String typeValue;
    private String code;

    AccountType(String typeValue, String code) {
        this.typeValue = typeValue;
        this.code = code;
    }

    public String getTypeValue() {
        return this.typeValue;
    }

    public String getCode() {
        return code;
    }
}
