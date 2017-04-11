package web;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import database.DataBaseManager.*;
import database.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Created by kolinsol on 4/4/17.
 */

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        if (req.getParameter("register-person") != null) {
            System.out.println("PERSON");
            Integer personId = null;
            try{
                personId = registerPerson(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Выберите пол");
                req.getRequestDispatcher("jsp/registration/registration-step-1.jsp").forward(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Заполните все поля");
                req.getRequestDispatcher("jsp/registration/registration-step-1.jsp").forward(req, resp);
            } catch (NumberFormatException | DateTimeException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Неверно введена дата");
                req.getRequestDispatcher("jsp/registration/registration-step-1.jsp").forward(req, resp);
            }
            if (personId != null) {
                System.out.println(personId);
                HttpSession session = req.getSession();
                session.setAttribute("person_id", personId);
                req.getRequestDispatcher("jsp/registration/registration-step-2.jsp").forward(req, resp);
            }
        }

        if (req.getParameter("register-contact") != null) {
            System.out.println("CONTACT");
            Integer contactInfoId = null;
            try {
                contactInfoId = registerContactInfo(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Адрес почты или номер телефона уже зарегистрированы");
                req.getRequestDispatcher("jsp/registration/registration-step-2.jsp").forward(req, resp);
            } catch (IOException e) {
                req.setAttribute("errorMessage", "Запоните все поля");
                req.getRequestDispatcher("jsp/registration/registration-step-2.jsp").forward(req, resp);
            }
            if (contactInfoId != null) {
                HttpSession session = req.getSession(false);
                Integer personId = (Integer)session.getAttribute("person_id");
                System.out.println(personId);
                System.out.println(contactInfoId);
                try {
                    new PersonManager().setContactInfo(personId, contactInfoId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jsp/registration/registration-step-3.jsp").forward(req, resp);
            }
        }

        if (req.getParameter("register-passport") != null) {
            System.out.println("PASSPORT");
            Integer passportId = null;
            try {
                passportId = registerPassport(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Данный номер паспорта уже зарегистрированы");
                req.getRequestDispatcher("jsp/registration/registration-step-3.jsp").forward(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Запоните все поля");
                req.getRequestDispatcher("jsp/registration/registration-step-3.jsp").forward(req, resp);
            } catch (NumberFormatException | DateTimeException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Неверно введена дата");
                req.getRequestDispatcher("jsp/registration/registration-step-3.jsp").forward(req, resp);
            }
            if (passportId != null) {
                HttpSession session = req.getSession(false);
                Integer personId = (Integer)session.getAttribute("person_id");
                System.out.println(personId);
                System.out.println(passportId);
                try {
                    new PersonManager().setPassport(personId, passportId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jsp/registration/registration-step-4.jsp").forward(req, resp);
            }
        }

        if (req.getParameter("register-login") != null) {
            System.out.println("LOGIN");
            if (!checkPassword(req, resp)) {
                req.setAttribute("errorMessage", "Пароли не сопадают");
                req.getRequestDispatcher("jsp/registration/registration-step-4.jsp").forward(req, resp);
            } else {
                Integer loginInfoId = null;
                try {
                    loginInfoId = registerLoginInfo(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                    req.setAttribute("errorMessage", "Логин уже зарегистрированы");
                    req.getRequestDispatcher("jsp/registration/registration-step-4.jsp").forward(req, resp);
                } catch (IOException e) {
                    req.setAttribute("errorMessage", "Заполните все поля");
                    req.getRequestDispatcher("jsp/registration/registration-step-4.jsp").forward(req, resp);
                }
                if (loginInfoId != null) {
                    HttpSession session = req.getSession(false);
                    Integer personId = (Integer)session.getAttribute("person_id");
                    System.out.println(personId);
                    System.out.println(loginInfoId);
                    try {
                        new PersonManager().setLoginInfo(personId, loginInfoId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.setAttribute("completeMessage", "Ркгистрация прошла успешно");
                    req.getRequestDispatcher("jsp/login/login.jsp").forward(req, resp);
                }
            }
        }
    }

    private Integer registerPerson(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, DateTimeException {
        String firstName = req.getParameter("firstname");
        String secondName = req.getParameter("secondname");
        String lastName = req.getParameter("lastname");
        if (firstName.isEmpty() || secondName.isEmpty() || lastName.isEmpty()) {
            throw new IOException();
        }
        LocalDate birthDate = LocalDate.of(
                Integer.parseInt(req.getParameter("year")),
                Integer.parseInt(req.getParameter("month")),
                Integer.parseInt(req.getParameter("day")));
        if (birthDate.isAfter(LocalDate.now())) {
            throw new DateTimeException("Illegal date");
        }
        String sex = req.getParameter("sex");
        boolean pension = false;
        if ((req.getParameter("pension")) != null) {
            pension = true;
        }
        boolean military = false;
        if ((req.getParameter("military")) != null) {
            military = true;
        }
        Person person = new Person(firstName, secondName, lastName, birthDate,
                sex, pension, military);
        return new PersonManager().create(person);
    }

    private Integer registerContactInfo(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String phoneNumber = req.getParameter("phone_number");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        if (phoneNumber.isEmpty() || email.isEmpty() || address.isEmpty()) {
            throw new IOException();
        }
        String city = req.getParameter("city");
        Person.ContactInfo contactInfo = new Person.ContactInfo(email, phoneNumber, address, city);
        return new ContactManager().create(contactInfo);
    }

    private Integer registerPassport(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, DateTimeException {
        String serialNumber = req.getParameter("serial_number").toUpperCase();
        String issueFacility = req.getParameter("issue_facility");
        String address = req.getParameter("address");
        if (serialNumber.isEmpty() || issueFacility.isEmpty() || address.isEmpty()) {
            throw new IOException();
        }
        String city = req.getParameter("city");
        LocalDate issueDate = LocalDate.of(
                Integer.parseInt(req.getParameter("issue_year")),
                Integer.parseInt(req.getParameter("issue_month")),
                Integer.parseInt(req.getParameter("issue_day")));
        LocalDate expireDate = LocalDate.of(
                Integer.parseInt(req.getParameter("expire_year")),
                Integer.parseInt(req.getParameter("expire_month")),
                Integer.parseInt(req.getParameter("expire_day")));
        if (issueDate.isAfter(expireDate) || issueDate.isAfter(LocalDate.now()) || expireDate.isBefore(LocalDate.now())) {
            throw new DateTimeException("Illegal date");
        }
        Person.Passport passport = new Person.Passport(serialNumber, issueFacility,
                issueDate, expireDate, city, address);
        return new PassportManager().create(passport);
    }

    private Boolean checkPassword(HttpServletRequest req, HttpServletResponse resp) {
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");
        if (password.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    private Integer registerLoginInfo(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.isEmpty() || password.isEmpty()) {
            throw new IOException();
        }
        Person.LoginInfo loginInfo = new Person.LoginInfo(username, password);
        return new LoginManager().create(loginInfo);
    }
}