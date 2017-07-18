<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show all records</title>
<style>td{min-width:20px;border:1px solid gray;}table{border-collapse:collapse;}</style>
</head>
<body>
<!--  	${all}
	${all.iterator().next().name} -->
	<!--  
	<c:forEach var="i" begin="0" end="4">
		<c:out value="${all.get(i).getName() }"/>
	</c:forEach>-->
	<table>
	<tr>
			<th>id</th><th>name</th><th>password</th>
	</tr>
	<c:forEach items="${all}" var="item">
		<tr>
			<td>
				<c:out value="${item.id }"/>
			<td>
				<c:out value="${item.name}"/>
			</td>
			<td>
				<c:out value="${item.password }"/>
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>