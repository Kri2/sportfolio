<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- all <head></head> and <body> tag is header.jsp  -->
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="container">
	<form:form method="POST" modelAttribute="addItemForm">
		<form:input type="text" path="ticker" placeholder="ticker"/>
		<form:input type="text" path="sharesCount" placeholder="how many shares"/>
		<input type="submit" value="add"/>
	</form:form>
</div>
</body>
</html>