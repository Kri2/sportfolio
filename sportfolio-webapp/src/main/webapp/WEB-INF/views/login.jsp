<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="container">
<c:set var="loginUrl"><c:url value="/login"/></c:set>
<h2 style="text-align:center">Welcome!</h2>
<form method="post" action="${loginUrl}" class="card card-container form-signin">
	<p>Please type in your username and password to see your portfolio or 
	<a href="<c:url value="/adduser"/>">Register</a>
	as new user</p>

    <input type="text" name="name" class="form-control" placeholder="Your Name"/>
    <input type="password" name="password" class="form-control" placeholder="Password"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control"/>
    <input type="submit" value="login" class="btn btn-default"/>
</form>
</div>
</body>
</html>