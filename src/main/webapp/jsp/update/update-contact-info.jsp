<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/8/17
  Time: 20:37
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
    <input class="insert" type="text" name="new-phone-number" value="${person.getContactInfo().getPhoneNumber()}" maxlength="13" pattern="\+375(25|33|29|44)\d{7}"><br>
    <input class="insert" type="text" name="new-email" value="${person.getContactInfo().getEmail()}"><br>
    <input class="insert" type="text" name="new-address" value="${person.getContactInfo().getAddress()}"><br>
    <div class="input">
        <label>
            Город
            <select name="new-city">
                <c:forEach var="city" items="${cities}" varStatus="status">
                    <option
                            <c:if test="${person.getContactInfo().getCity() eq city}">
                                selected
                            </c:if>
                    >
                        <c:out value="${city}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
    </div>
    <input type="submit" name="update-contact-info" value="Сохранить изменения">
</form>
</body>
</html>
