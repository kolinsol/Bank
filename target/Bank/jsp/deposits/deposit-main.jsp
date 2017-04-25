<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/24/17
  Time: 20:25
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
<c:forEach var="depositType" items="${depositTypes}" varStatus="status">
    <div class="deposit-container">
        <span class="deposit-name">
                ${depositType.getName()}
        </span>
        <br>
        <form action="deposits" method="get">
            <input type="hidden" name="personId" value="${person.getPersonId()}">
            <input type="hidden" name="depositTypeId" value="${depositType.getId()}">
            <div class="table-wrapper">
                <input type="range" name="amount"
                       min="${depositType.getMinAmount()}"
                       max="${depositType.getMaxAmount()}">
            </div>
            <div class="table-wrapper">
                <input type="range" name="period"
                       min="${depositType.getMinPeriod()}"
                       max="${depositType.getMaxPeriod()}">
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
                ${depositType.getPercentage()} %
            </div>
            <input class="deposit-submit" type="submit" value="Подать заявку">
        </form>
    </div>
</c:forEach>
</body>
</html>
