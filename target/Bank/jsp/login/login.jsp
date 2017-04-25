<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/4/17
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/simple-header.jsp"/>
<html>
<head>
    <meta charset="utf-16">
    <title>user registration</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/complete-message.jsp"/>
<jsp:include page="../messages/error-message.jsp"/>
<form class="center" action="login" method="post">
    Логин<br>
    <input class="insert" type="text" name="username" placeholder="Введите логин..."><br>
    Пароль<br>
    <input class="insert" type="password" name="password" placeholder="Введите пароль..."><br>
    <input type="submit" name="login" value="Войти">
    <br>
    <input type="submit" name="register" value="Зарегистрироваться">
</form>
</body>
