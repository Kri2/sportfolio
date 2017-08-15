<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<p>Welcome!</p>
	<form:form method="POST" modelAttribute="userForm">
		<form:input type="text" path="name"/>
		<form:input type="password" path="password"/>
		<input type="submit" value="submit"/>
	</form:form>
	<h2>${result }</h2>
	your name: ${name }
	<a href="<c:url value="adduser"/>">Add new user</a>
</body>
</html>