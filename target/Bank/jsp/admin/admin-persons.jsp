<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 5/1/17
  Time: 19:37
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
<c:forEach var="person" items="${persons}" varStatus="status">
    <div class="transaction-container">
        <form action="admin" method="get">
            <input type="hidden" name="personId" value="${person.getPersonId()}">
            <div class="table-wrapper">
                ${person.getSecondName()} ${person.getFirstName()} ${person.getLastName()}
            </div>
            <div class="table-wrapper">
                    ${person.getContactInfo().getCity()}
            </div>
            <div class="table-wrapper">
                    ${person.getContactInfo().getPhoneNumber()}
            </div>
            <input class="transaction-submit" name="get-full-person-info" type="submit" value="Полная информация">
        </form>
    </div>
</c:forEach>
</body>
</html>