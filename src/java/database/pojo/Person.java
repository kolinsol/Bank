package database.pojo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by kolinsol on 3/14/17.
 */
public class Person {
    private int personId;
    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDate birthDate;
    private String sex;
    private boolean pension;
    private boolean military;

    private Passport passport;
    private ContactInfo contactInfo;
    private LoginInfo loginInfo;

    private ArrayList<Transaction> transactions;

    public Person(String firstName, String secondName, String lastName,
                  LocalDate birthDate, String sex,
                  boolean pension, boolean military) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.pension = pension;
        this.military = military;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public boolean isPension() {
        return pension;
    }

    public boolean isMilitary() {
        return military;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public static class Passport {
        private int passportId;
        private String IssueFacility;
        private LocalDate issueDate;
        private LocalDate expireDate;
        private String serialNumber;
        private String city;
        private String address;

        public Passport(String serialNumber, String issueFacility, LocalDate issueDate,
                        LocalDate expireDate, String city, String address) {
            this.IssueFacility = issueFacility;
            this.issueDate = issueDate;
            this.expireDate = expireDate;
            this.serialNumber = serialNumber;
            this.city = city;
            this.address = address;
        }

        public String getIssueFacility() {
            return IssueFacility;
        }

        public LocalDate getIssueDate() {
            return issueDate;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public String getCity() {
            return city;
        }

        public String getAddress() {
            return address;
        }

        public LocalDate getExpireDate() {
            return expireDate;
        }

        public int getPassportId() {
            return passportId;
        }

        public void setPassportId(Integer passportId) {
            this.passportId = passportId;
        }
    }

    public static class ContactInfo {
        private int contactInfoId;
        private String email;
        private String phoneNumber;
        private String address;
        private String city;

        public ContactInfo(String email, String phoneNumber, String address, String city) {
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public int getContactInfoId() {
            return contactInfoId;
        }

        public void setContactInfoId(Integer contactInfoId) {
            this.contactInfoId = contactInfoId;
        }
    }

    public static class LoginInfo {
        private int loginInfoId;
        private String username;
        private String password;

        public LoginInfo(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public int getLoginInfoId() {
            return loginInfoId;
        }

        public void setLoginInfoId(Integer loginInfoId) {
            this.loginInfoId = loginInfoId;
        }
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Passport getPassport() {
        return passport;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}