<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/29/17
  Time: 18:09
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
<c:forEach var="creditType" items="${creditTypes}" varStatus="status">
    <div class="transaction-container">
        <span class="transaction-name">
                ${creditType.getName()}
        </span>
        <br>
        <form action="credits" method="get">
            <input type="hidden" name="personId" value="${person.getPersonId()}">
            <input type="hidden" name="creditTypeId" value="${creditType.getId()}">
            <div class="table-wrapper">
                <input type="range" name="amount"
                       min="${creditType.getMinAmount()}"
                       max="${creditType.getMaxAmount()}">
            </div>
            <div class="table-wrapper">
                <input type="range" name="period"
                       min="${creditType.getMinPeriod()}"
                       max="${creditType.getMaxPeriod()}">
            </div>
            <div class="table-wrapper">
                <label>
                    Валюта
                    <select name="currency">
                        <c:forEach var="currency" items="${currencies}" varStatus="status">
                            <option>
                                <c:out value="${currency}"/>
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="table-wrapper">
                    ${creditType.getPercentage()} %
            </div>
            <input class="transaction-submit" type="submit" value="Подать заявку">
        </form>
    </div>
</c:forEach>
</body>
</html>