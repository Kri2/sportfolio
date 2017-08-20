<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="container">
	<p>Here you can add new user</p>
	<p>Current logged user is: ${whoIsLoggedIn }</p>
	<form:form modelAttribute="userCredentials" method="POST">
		<form:input type="text" path="name" placeholder="your name"/>
		<form:input type="text" path="password" placeholder="your new password"/>
		<input type="submit" value="submit"/>
	</form:form>
</div>
</body>
</html>