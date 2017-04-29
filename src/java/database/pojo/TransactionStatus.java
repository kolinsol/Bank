package database.pojo;

/**
 * Created by kolinsol on 4/25/17.
 */
public enum TransactionStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    DECLINED("DECLINED"),
    EXPIRED("EXPIRED");

    private String statusValue;

    TransactionStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
