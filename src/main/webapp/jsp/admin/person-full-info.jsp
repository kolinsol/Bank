<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 5/1/17
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/simple-header.jsp"/>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<div class="center">
    <div class="person-info-container">
        <span class="person-info-name">
            Личная информация
        </span>
        <br>
        <div class="vertical-table-wrapper ">
            <table>
                <tr>
                    <td>Фамилия</td>
                    <td class="value">${person.getSecondName()}</td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td class="value">${person.getFirstName()}</td>
                </tr>
                <tr>
                    <td>Отчество</td>
                    <td class="value">${person.getLastName()}</td>
                </tr>
                <tr>
                    <td>День рождения</td>
                    <td class="value">${person.getBirthDate()}</td>
                </tr>
                <tr>
                    <td>Пол</td>
                    <c:set var="malesex" scope="page" value="Мужской"/>
                    <c:set var="femalesex" scope="page" value="Женский"/>
                    <td class="value">
                        <c:if test="${person.getSex() eq malesex}">
                            Мужской
                        </c:if>
                        <c:if test="${person.getSex() eq femalesex}">
                            Женский
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Военнообязанный</td>
                    <td class="value">
                        <c:if test="${person.isMilitary() == true}">
                            Да
                        </c:if>
                        <c:if test="${person.isMilitary() == false}">
                            Нет
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Пенсионер</td>
                    <td class="value">
                        <c:if test="${person.isPension() == true}">
                            Да
                        </c:if>
                        <c:if test="${person.isPension() == false}">
                            Нет
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        <br>
        <span class="person-info-name">
            Контактная информация
        </span>
        <br>
        <div class="vertical-table-wrapper ">
            <table>
                <tr>
                    <td>Город</td>
                    <td class="value">${person.getContactInfo().getCity()}</td>
                </tr>
                <tr>
                    <td>Адрес</td>
                    <td class="value">${person.getContactInfo().getAddress()}</td>
                </tr>
                <tr>
                    <td>Телефон</td>
                    <td class="value">${person.getContactInfo().getPhoneNumber()}</td>
                </tr>
                <tr>
                    <td>Эл. почта</td>
                    <td class="value">${person.getContactInfo().getEmail()}</td>
                </tr>
            </table>
        </div>
        <br>
        <span class="person-info-name">
            Паспортные данные
        </span>
        <br>
        <div class="vertical-table-wrapper ">
            <table>
                <tr>
                    <td>Орган выдачи</td>
                    <td class="value">${person.getPassport().getIssueFacility()}</td>
                </tr>
                <tr>
                    <td>Дата выдачи</td>
                    <td class="value">${person.getPassport().getIssueDate()}</td>
                </tr>
                <tr>
                    <td>Срок действия</td>
                    <td class="value">${person.getPassport().getExpireDate()}</td>
                </tr>
                <tr>
                    <td>Серийный номер</td>
                    <td class="value">${person.getPassport().getSerialNumber()}</td>
                </tr>
                <tr>
                    <td>Город прописки</td>
                    <td class="value">${person.getPassport().getCity()}</td>
                </tr>
                <tr>
                    <td>Адрес прописки</td>
                    <td class="value">${person.getPassport().getAddress()}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
