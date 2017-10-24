<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="container">
	<c:if test="${whoIsLoggedIn=='' || whoIsLoggedIn=='anonymousUser'}">
		<p>Please login first.</p>
	</c:if>
	<c:if test="${whoIsLoggedIn!='' && whoIsLoggedIn!='anonymousUser' }">
	<p>You are logged as ${whoIsLoggedIn }</p>
	Your Portfolio:
	<table>
		<tr>
			<th>ticker</th>
			<th>price</th>
			<th>change</th>
			<th>#shares</th>
			<th>$purchased</th>
			<th>value</th>
			<th>gained</th>
			<th>remove</th>
		</tr>
		<c:forEach items="${portfolioItems}" var="stockInfo">
			<tr>
				<td>
					<c:out value="${stockInfo.ticker}"/>
				</td>
				<td>
					<fmt:formatNumber type="currency" value="${stockInfo.price}"/>
				</td>
				<td>
					<c:out value="${stockInfo.changeP}%"/>
				</td>
				<td>
					<c:out value="${stockInfo.sharesCount}"/>
				</td>
				<td>
					<fmt:formatNumber type="currency" value="${stockInfo.purchasePrice}"/>
				</td>
				<td>
					<fmt:formatNumber type="currency" value="${stockInfo.sharesCount * stockInfo.price}"/>
				</td>
				<td>
					<fmt:formatNumber type="percent" maxFractionDigits="2" value="${(stockInfo.sharesCount)*(stockInfo.price-stockInfo.purchasePrice)/(stockInfo.purchasePrice*stockInfo.sharesCount)}"/>
				</td>
				<td>
					<a href="<c:url value="/remove/${whoIsLoggedIn}/${stockInfo.ticker}"/>">x</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value="/addportfolioitem" />" class="btn btn-outline-primary">add portfolio item</a>
	<a href="<c:url value="/logout" />" class="btn btn-outline-primary">Logout</a>
	</c:if>
</div>
</body>
</html>