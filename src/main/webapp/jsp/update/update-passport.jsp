<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/8/17
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header/simple-header.jsp"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<jsp:include page="../messages/error-message.jsp"/>
<form class="center" action="update" method="post">
    <input class="insert" type="text" name="new-serial-number" value="${person.getPassport().getSerialNumber()}" maxlength="9"><br>
    <input class="date" type="text" name="new-issue-day" value="${person.getPassport().getIssueDate().getDayOfMonth()}" maxlength="2" size="2">
    <input class="date" type="text" name="new-issue-month" value="${person.getPassport().getIssueDate().getMonthValue()}" maxlength="2" size="2">
    <input class="date" type="text" name="new-issue-year" value="${person.getPassport().getIssueDate().getYear()}" maxlength="4" size="4"><br>
    <input class="date" type="text" name="new-expire-day" value="${person.getPassport().getExpireDate().getDayOfMonth()}" maxlength="2" size="2">
    <input class="date" type="text" name="new-expire-month" value="${person.getPassport().getExpireDate().getMonthValue()}" maxlength="2" size="2">
    <input class="date" type="text" name="new-expire-year" value="${person.getPassport().getExpireDate().getYear()}" maxlength="4" size="4"><br>
    <input class="insert" type="text" name="new-issue-facility" value="${person.getPassport().getIssueFacility()}" ><br>
    <input class="insert" type="text" name="new-address" value="${person.getPassport().getAddress()}">
    <div class="input">
        <label>
            Город
            <select name="new-city">
                <c:forEach var="city" items="${cities}" varStatus="status">
                    <option
                            <c:if test="${person.getPassport().getCity() eq city}">
                                selected
                            </c:if>
                    >
                        <c:out value="${city}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
    </div>
    <input type="submit" name="update-passport" value="Сохранить изменения">
</form>
</body>
</body>
</html>
