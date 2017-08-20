<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="container">
<h2>Hello World!</h2>
<p>This page allows to input your portfolio details and then see everyday summary, stocks data is automatically updated from google server</p>
	<a href="<c:url value="login"/>">Login</a>
	<a href="<c:url value="portfolio"/>">Your portfolio</a>
	<a href="<c:url value="adduser"/>">register</a>
  </div>

</body>
</html>
