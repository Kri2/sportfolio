<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>custom login page</title>
</head>
<body>
<h1>Welcome!</h1>
<p>please type in your username and password to see your portfolio or 
<a href="<c:url value="/adduser"/>">Register</a>
as new user</p>
<c:set var="loginUrl"><c:url value="/login"/></c:set>
<form method="post" action="${loginUrl}">
    <input type="text" name="name" />
    <input type="password" name="password" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Zaloguj" />
</form>

</body>
</html>