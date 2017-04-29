<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/29/17
  Time: 19:24
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
<c:forEach var="credit" items="${credits}" varStatus="status">
    <div class="transaction-container">
        <form action="admin" method="get">
            <input type="hidden" name="creditId" value="${credit[0]}">
            <input type="hidden" name="personId" value="${credit[5]}">
            <div class="table-wrapper">
                    ${credit[2]}
            </div>
            <div class="table-wrapper">
                    ${credit[1]}
            </div>
            <div class="table-wrapper">
                    ${credit[3]}
            </div>
            <div class="table-wrapper">
                    ${credit[4]}
            </div>
            <input class="transaction-submit" name="accept-credit" type="submit" value="Принять">
            <input class="transaction-submit" name="decline-credit" type="submit" value="Отклонить">
        </form>
    </div>
</c:forEach>
</body>
</html>
