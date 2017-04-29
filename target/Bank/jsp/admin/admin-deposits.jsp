<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/26/17
  Time: 20:26
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
<jsp:include page="../messages/complete-message.jsp"/>
<c:forEach var="deposit" items="${deposits}" varStatus="status">
    <div class="transaction-container">
        <form action="admin" method="get">
            <input type="hidden" name="depositId" value="${deposit[0]}">
            <input type="hidden" name="personId" value="${deposit[5]}">
            <div class="table-wrapper">
                ${deposit[2]}
            </div>
            <div class="table-wrapper">
                ${deposit[1]}
            </div>
            <div class="table-wrapper">
                ${deposit[3]}
            </div>
            <div class="table-wrapper">
                ${deposit[4]}
            </div>
            <input class="transaction-submit" name="accept-deposit" type="submit" value="Принять">
            <input class="transaction-submit" name="decline-deposit" type="submit" value="Отклонить">
        </form>
    </div>
</c:forEach>
</body>
</html>
