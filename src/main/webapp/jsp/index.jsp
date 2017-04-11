<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" session="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<form action="main" method="post">
    <input type="submit" value="JSTL" name="tags"/>
    ${person.getSecondName()}
</form>
</body>
</html>