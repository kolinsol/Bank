package web;

import database.DataBaseManager.ContactManager;
import database.DataBaseManager.LoginManager;
import database.DataBaseManager.PassportManager;
import database.DataBaseManager.PersonManager;
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
 * Created by kolinsol on 4/8/17.
 */
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        if (req.getParameter("update-person") != null) {
            req.getRequestDispatcher("jsp/update/update-person.jsp").forward(req, resp);
        }
        if (req.getParameter("update-passport") != null) {
            req.getRequestDispatcher("jsp/update/update-passport.jsp").forward(req, resp);
        }
        if (req.getParameter("update-contact-info") != null) {
            req.getRequestDispatcher("jsp/update/update-contact-info.jsp").forward(req, resp);
        }
        if (req.getParameter("update-login-info") != null) {
            req.getRequestDispatcher("jsp/update/update-login-info.jsp").forward(req, resp);
        }
        if (req.getParameter("delete-person") != null) {
            deletePerson(req, resp);
            req.setAttribute("completeMessage", "Пользователь удален");
            req.getRequestDispatcher("jsp/login/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        if (req.getParameter("update-person") != null) {
            try {
                updatePerson(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Выберите пол");
                req.getRequestDispatcher("jsp/update/update-person.jsp").forward(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Заполните все поля");
                req.getRequestDispatcher("jsp/update/update-person.jsp").forward(req, resp);
            } catch (NumberFormatException | DateTimeException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Неверно введена дата");
                req.getRequestDispatcher("jsp/update/update-person.jsp").forward(req, resp);
            }
        }
        if (req.getParameter("update-passport") != null) {
            try {
                updatePassport(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Данный номер паспорта уже зарегистрированы");
                req.getRequestDispatcher("jsp/update/update-passport.jsp").forward(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Запоните все поля");
                req.getRequestDispatcher("jsp/update/update-passport.jsp").forward(req, resp);
            } catch (NumberFormatException | DateTimeException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Неверно введена дата");
                req.getRequestDispatcher("jsp/update/update-passport.jsp").forward(req, resp);
            }
        }
        if (req.getParameter("update-contact-info") != null) {
            try {
                updateContactInfo(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Адрес почты или номер телефона уже зарегистрированы");
                req.getRequestDispatcher("jsp/update/update-contact-info.jsp").forward(req, resp);
            } catch (IOException e) {
                req.setAttribute("errorMessage", "Запоните все поля");
                req.getRequestDispatcher("jsp/update/update-contact-info.jsp").forward(req, resp);
            }
        }
        if (req.getParameter("update-login-info") != null) {
            try {
                updateLoginInfo(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Логин уже зарегистрированы");
                req.getRequestDispatcher("jsp/registration/registration-step-4.jsp").forward(req, resp);
            } catch (IOException e) {
                req.setAttribute("errorMessage", "Заполните все поля");
                req.getRequestDispatcher("jsp/registration/registration-step-4.jsp").forward(req, resp);
            }
        }
    }

    private void updatePerson(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, DateTimeException {
        String firstName = req.getParameter("new-firstname");
        String secondName = req.getParameter("new-secondname");
        String lastName = req.getParameter("new-lastname");
        if (firstName.isEmpty() || secondName.isEmpty() || lastName.isEmpty()) {
            throw new IOException();
        }
        LocalDate birthDate = LocalDate.of(
                Integer.parseInt(req.getParameter("new-year")),
                Integer.parseInt(req.getParameter("new-month")),
                Integer.parseInt(req.getParameter("new-day")));
        if (birthDate.isAfter(LocalDate.now())) {
            throw new DateTimeException("Illegal date");
        }
        String sex = req.getParameter("new-sex");
        boolean pension = false;
        if ((req.getParameter("new-pension")) != null) {
            pension = true;
        }
        boolean military = false;
        if ((req.getParameter("new-military")) != null) {
            military = true;
        }
        Person person = new Person(firstName, secondName, lastName, birthDate,
                sex, pension, military);
        HttpSession session = req.getSession(false);
        Person tempPerson = (Person)session.getAttribute("person");
        new PersonManager().update(tempPerson.getPersonId(),person);
        tempPerson = new PersonManager().getEntityById(tempPerson.getPersonId());
        session.setAttribute("person", tempPerson);
        try {
            req.setAttribute("completeMessage", "Информация успешно изменена");
            req.getRequestDispatcher("jsp/update/update-menu.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePassport(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, DateTimeException {
        String serialNumber = req.getParameter("new-serial-number");
        String issueFacility = req.getParameter("new-issue-facility");
        String address = req.getParameter("new-address");
        if (serialNumber.isEmpty() || issueFacility.isEmpty() || address.isEmpty()) {
            throw new IOException();
        }
        String city = req.getParameter("new-city");
        LocalDate issueDate = LocalDate.of(
                Integer.parseInt(req.getParameter("new-issue-year")),
                Integer.parseInt(req.getParameter("new-issue-month")),
                Integer.parseInt(req.getParameter("new-issue-day")));
        LocalDate expireDate = LocalDate.of(
                Integer.parseInt(req.getParameter("new-expire-year")),
                Integer.parseInt(req.getParameter("new-expire-month")),
                Integer.parseInt(req.getParameter("new-expire-day")));
        if (issueDate.isAfter(expireDate) || issueDate.isAfter(LocalDate.now()) || expireDate.isBefore(LocalDate.now())) {
            throw new DateTimeException("Illegal date");
        }
        Person.Passport passport = new Person.Passport(serialNumber, issueFacility,
                issueDate, expireDate, city, address);
        HttpSession session = req.getSession(false);
        Person tempPerson = (Person)session.getAttribute("person");
        new PassportManager().update(tempPerson.getPassport().getPassportId(), passport);
        tempPerson = new PersonManager().getEntityById(tempPerson.getPersonId());
        session.setAttribute("person", tempPerson);
        try {
            req.setAttribute("completeMessage", "Информация успешно изменена");
            req.getRequestDispatcher("jsp/update/update-menu.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateContactInfo(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String phoneNumber = req.getParameter("new-phone-number");
        String email = req.getParameter("new-email");
        String address = req.getParameter("new-address");
        if (phoneNumber.isEmpty() || email.isEmpty() || address.isEmpty()) {
            throw new IOException();
        }
        String city = req.getParameter("new-city");
        Person.ContactInfo contactInfo = new Person.ContactInfo(email, phoneNumber, address, city);
        HttpSession session = req.getSession(false);
        Person tempPerson = (Person)session.getAttribute("person");
        new ContactManager().update(tempPerson.getContactInfo().getContactInfoId(), contactInfo);
        tempPerson = new PersonManager().getEntityById(tempPerson.getPersonId());
        session.setAttribute("person", tempPerson);
        try {
            req.setAttribute("completeMessage", "Информация успешно изменена");
            req.getRequestDispatcher("jsp/update/update-menu.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLoginInfo(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String password = req.getParameter("new-password");
        String confirmPassword = req.getParameter("new-confirm-password");
        if (!password.equals(confirmPassword)) {
            try {
                req.setAttribute("errorMessage", "Passwords don't match");
                req.getRequestDispatcher("jsp/update/update-login-info.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            String username = req.getParameter("new-username");
            if (username.isEmpty() || password.isEmpty()) {
                throw new IOException();
            }
            Person.LoginInfo loginInfo = new Person.LoginInfo(username, password);
            HttpSession session = req.getSession(false);
            Person tempPerson = (Person)session.getAttribute("person");
            new LoginManager().update(tempPerson.getLoginInfo().getLoginInfoId(), loginInfo);
            tempPerson = new PersonManager().getEntityById(tempPerson.getPersonId());
            session.setAttribute("person", tempPerson);
            try {
                req.setAttribute("completeMessage", "Информация успешно изменена");
                req.getRequestDispatcher("jsp/update/update-menu.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deletePerson(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        Person tempPerson = (Person)session.getAttribute("person");
        Integer personId = tempPerson.getPersonId();
        try {
            new PersonManager().delete(personId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}