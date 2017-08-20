<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Stocks Portfolio by kri2</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<spring:url value="/resources/style.css" var="coreCss" />
	<spring:url value="/resources/jquery/jquery-3.2.1.min.js" var="jquery"/>
	<spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapCss"/>
	<spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrapJs"/>
	<script src="${jquery}"></script>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="${bootstrapCss}"  />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="${coreCss}" rel="stylesheet" />
	<script src="${bootstrapJs}" ></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">YOUR STOCKS PORTFOLIO</a>
    </div>
    <ul class="nav">
      <li class="active mojnav"><a href="<c:url value="/"/>">Home</a></li>
      <li><a href="<c:url value="portfolio"/>">Your portfolio</a></li>
      <li><a href="<c:url value="adduser"/>">Register</a></li>
      <c:if test="${(whoIsLoggedIn=='anonymousUser') || (whoIsLoggedIn=='')}">
      <li><a href="<c:url value="login"/>">Login</a></li>
      </c:if>
      <c:if test="${(whoIsLoggedIn!='anonymousUser') && (whoIsLoggedIn!='')}">
      <li><a href="<c:url value="logout"/>">Logout</a></li>
      </c:if>
    </ul>
  </div>
</nav>

<!--  
<nav class="navbar navbar-default ">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/"/>">Home</a>
		</div>
		
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/login"/>">Login</a></li>
				<li><a href="<c:url value="/adduser"/>">Register</a></li>
			</ul>
		
	</div>
</nav>-->
<!-- here i will put all <head> code which should be common for all my pages -->