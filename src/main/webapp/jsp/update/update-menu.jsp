<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/8/17
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/complete-message.jsp"/>
    <form action="update" method="get">
        <input type="submit" name="update-person" value="Основная информация">
        <input type="submit" name="update-passport" value="Пасспортные данные">
        <input type="submit" name="update-contact-info" value="Контактная информация">
        <input type="submit" name="update-login-info" value="Логин и пароль">
        <input type="submit" name="delete-person" value="Удалить профиль">
    </form>
</body>
</html>
