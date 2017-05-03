<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/11/17
  Time: 12:35
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
<form class="center" action="admin" method="get">
    <input type="submit" name="admin-deposits" value="Депозитные заявки">
    <input type="submit" name="admin-credits" value="Кредитные заявки">
    <input type="submit" name="admin-persons" value="Список клиентов">
</form>
<form class="center" action="admin" method="get">
    <input type="submit" name="process-transactions" value="Закрытие банковского дня">
</form>
</body>
</html>
