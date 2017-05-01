<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/8/17
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/simple-header.jsp"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/complete-message.jsp"/>
    <form class="center" action="main-controller" method="get">
        <input type="submit" name="update-info" value="Редактировать профиль">
        <input type="submit" name="deposits" value="Меню депозитов">
        <input type="submit" name="credits" value="Меню кредитов">
        <input type="submit" name="active-transactions" value="Список транзакций">
    </form>
</body>
</html>
