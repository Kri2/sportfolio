<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your portfolio</title>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<style>th{color:gray;}body{font-family: "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;}</style>
</head>
<body>
	<p>Welcome back ${name}</p>
	Your Portfolio:
	<table>
		<tr>
			<th>ticker</th>
			<th>price</th>
			<th>change</th>
			<th>shares bought</th>
			<th>purchase date</th>
			<th>purchase price</th>
			<th>value</th>
		</tr>
		<c:forEach items="${portfolioItems}" var="stockInfo">
			<tr>
				<td>
					<c:out value="${stockInfo.ticker}"/>
				</td>
				<td>
					<c:out value="$ ${stockInfo.price}"/>
				</td>
				<td>
					<c:out value="${stockInfo.changeP}%"/>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>