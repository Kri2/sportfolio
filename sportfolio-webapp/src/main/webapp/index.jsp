<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	
<body>
<h2>Hello World!</h2>
	<a href="<c:url value="login"/>">Login</a>
	<a href="<c:url value="portfolio"/>">Your portfolio</a>
	<a href="<c:url value="adduser"/>">register</a>
</body>
</html>
