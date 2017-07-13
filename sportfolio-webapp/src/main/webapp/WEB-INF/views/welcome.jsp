<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display portfolio</title>
<style>
table{border-collapse:collapse;}
table,th,td{border:1px solid gray;}
td{width:80px;padding:4px 4px 4px 8px;}
</style>
</head>
<body>
	<p>Welcome!</p>
	<!-- loop example 
	<c:forEach var="i" begin="1" end="5">
		Item <c:out value="${i}"/>
	</c:forEach>
	-->
	
	<table>
		<tr>
			<th>ticker</th>
			<th>price</th>
			<th>change</th>
			<th>shares bought</th>
			<th>purchase date</th>
			<th>purchase price</th>
		</tr>
		
		<c:forEach items="${portfolioList}" var="stockInfo">
			<tr>
				<td>
					<c:out value="${stockInfo.ticker}"/>
				</td>
				<td>
					<c:out value="$ ${stockInfo.price}"/>
				</td>
				<td>
					<c:out value="${stockInfo.percentChange }%"/>
				</td>
				<td>
					<c:out value="${stockInfo.sharesCount}"/>
				</td>
				<td>
					<c:out value="${stockInfo.datePurchased}"/>
				</td>
				<td>
					<c:out value="${stockInfo.purchasePrice}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>