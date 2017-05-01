<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 5/1/17
  Time: 21:08
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
<c:forEach var="transaction" items="${transactions}" varStatus="status">
    <div class="transaction-container">
            <div class="table-wrapper">
                    ${transaction[0]}
            </div>
            <div class="table-wrapper">
                    ${transaction[1]}
            </div>
            <div class="table-wrapper">
                    ${transaction[2]}
            </div>
            <div class="table-wrapper">
                    ${transaction[3]}
            </div>
            <div class="table-wrapper">
                    ${transaction[4]}
            </div>
            <div class="table-wrapper">
                    ${transaction[5]}
            </div>
    </div>
</c:forEach>
</body>
</html>