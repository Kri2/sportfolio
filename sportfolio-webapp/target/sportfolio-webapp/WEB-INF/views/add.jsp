<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert new record</title>
</head>
<body>
	<form:form method="POST" modelAttribute="userForm">
		<table>
			<tr>
				<td>Enter name</td>
				<td><form:input type="text" path="name"/></td>
			</tr>
			<tr>
				<td>Enter passsword</td>
				<td><form:input type="text" path="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="write"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>