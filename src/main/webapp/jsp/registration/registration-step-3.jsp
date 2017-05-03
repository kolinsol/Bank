<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/4/17
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/simple-header.jsp"/>
<html>
<head>
    <meta charset="utf-8">
    <title>person registartion</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/error-message.jsp"/>
    <form class="center" action="registration" method="post">
        Серийный номер<br>
        <input class="insert" type="text" name="serial_number" placeholder="AA1234567" maxlength="9" pattern="[A-Za-z]{2}\d{7}"><br>
        Дата выдачи<br>
        <input class="date" type="text" name="issue_day" placeholder="dd" maxlength="2" size="2">
        <input class="date" type="text" name="issue_month" placeholder="mm" maxlength="2" size="2">
        <input class="date" type="text" name="issue_year" placeholder="yyyy" maxlength="4" size="4"><br>
        Срок действия<br>
        <input class="date" type="text" name="expire_day" placeholder="dd" maxlength="2" size="2">
        <input class="date" type="text" name="expire_month" placeholder="mm" maxlength="2" size="2">
        <input class="date" type="text" name="expire_year" placeholder="yyyy" maxlength="4" size="4"><br>
        Выдавший орган<br>
        <input class="insert" type="text" name="issue_facility" placeholder="Введите название..."><br>
        Адрес прописки<br>
        <input class="insert" type="text" name="address" placeholder="Введите адрес...">
        <div class="input">
            <label>
                Город
                <select name="city">
                    <c:forEach var="city" items="${cities}" varStatus="status">
                        <option>
                            <c:out value="${city}"/>
                        </option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <input type="submit" name="register-passport" value="Продолжить регистрацию">
    </form>
</body>
</html>
