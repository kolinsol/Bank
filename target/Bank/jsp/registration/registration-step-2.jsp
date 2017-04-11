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
    <title>contact registartion</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/error-message.jsp"/>
    <form action="registration" method="post">
        Номер телефона<br>
        <input class="insert" type="text" name="phone_number" placeholder="Введите номер..." maxlength="13"><br>
        Электронная почта<br>
        <input class="insert" type="text" name="email" placeholder="Введите почту..."><br>
        Адрес<br>
        <input class="insert" type="text" name="address" placeholder="Введите адрес..."><br>
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
        <input type="submit" name="register-contact" value="Продолжить регистрацию">
    </form>
</body>
</html>
