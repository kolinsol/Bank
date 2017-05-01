<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/8/17
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/simple-header.jsp"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/error-message.jsp"/>
    <form class="center" action="update" method="post">
        <input class="insert" type="text" name="new-firstname" value="${person.getFirstName()}"><br>
        <input class="insert" type="text" name="new-secondname" value="${person.getSecondName()}"><br>
        <input class="insert" type="text" name="new-lastname" value="${person.getLastName()}"><br>
        <input class="date" type="text" name="new-day" value="${person.getBirthDate().getDayOfMonth()}" maxlength="2" size="2">
        <input class="date" type="text" name="new-month" value="${person.getBirthDate().getMonthValue()}" maxlength="2" size="2">
        <input class="date" type="text" name="new-year" value="${person.getBirthDate().getYear()}" maxlength="4" size="4"><br>
        <c:set var="malesex" scope="page" value="Мужской"/>
        <c:set var="femalesex" scope="page" value="Женский"/>
        <div class="input">
            <label>
                Мужской
                <input type="radio" name="new-sex" value="Мужской"
                <c:if test="${person.getSex() eq malesex}">
                       checked>
                </c:if>
            </label>
            <label>
                Женский
                <input type="radio" name="new-sex" value="Женский"
                <c:if test="${person.getSex() eq femalesex}">
                       chacked>
                </c:if>
            </label>
        </div>
        <div class="input">
            <label>
                Пенсионер
                <input type="checkbox" name="new-pension"
                <c:if test="${person.isPension() == true}">
                       checked>
                </c:if>
            </label>
        </div>
        <div class="input">
            <label>
                Военнообязанный
                <input type="checkbox" name="new-military"
                <c:if test="${person.isMilitary() == true}">
                       checked>
                </c:if>
            </label>
        </div>
        <input type="submit" name="update-person" value="Сохранить изменения">
    </form>
</body>
</html>