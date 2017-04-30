import database.pojo.Account;
import database.pojo.AccountType;
import database.pojo.Deposit;
import database.pojo.Person;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by kolinsol on 3/12/17.
 */
public class Main {
    public static void main(String[] args) {
        LocalDate n = LocalDate.now();
        System.out.println(n.plusMonths(10));
    }
}
