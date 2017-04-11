<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/8/17
  Time: 20:37
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
<jsp:include page="../messages/error-message.jsp"/>
<form action="update" method="post">
    <input class="insert" type="text" name="new-username" value="${person.getLoginInfo().getUsername()}"><br>
    <input class="insert" type="password" name="new-password"><br>
    <input class="insert" type="password" name="new-confirm-password"><br>
    <input type="submit" name="update-login-info" value="Сохранить изменения">
</form>
</body>
</html>
