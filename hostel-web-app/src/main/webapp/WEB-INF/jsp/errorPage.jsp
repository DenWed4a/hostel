<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.not.found.message" var="not_found_message"/>
	<fmt:message bundle="${loc}" key="local.to.home.page" var="to_home_page"/>
	
	<link rel="stylesheet" href="css/errorStyle.css" type="text/css">

<body>

	<div class="wrapper">
		<div class="error404">404</div>
		<div class="notfound">${not_found_message}</div>
		<a href="Controller?command=GO_TO_INDEX_PAGE">${to_home_page}</a>
	</div>

</body>
</html>