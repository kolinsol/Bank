<%--
  Created by IntelliJ IDEA.
  User: kolinsol
  Date: 4/10/17
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>contact registartion</title>
    <link rel="stylesheet" href="css/new.css">
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div class="error-wrapper">
        <div class="error-message">
            <table>
                <tr>
                    <td>
                        <img class="message-icon" src="images/warning.png" width="50">
                    </td>
                    <td>
                            ${errorMessage}
                    </td>
                </tr>
            </table>
        </div>
    </div>
</c:if>
</body>
</html>
