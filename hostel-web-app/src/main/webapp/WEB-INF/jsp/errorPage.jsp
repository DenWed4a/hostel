<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
	<fmt:message bundle="${loc}" key="local.this.login.already.exists.message" var="login_already_exists"/>
	
	<link rel="stylesheet" href="css/errorStyle.css" type="text/css">

<body>
	

	<div class="wrapper">
		<div class="error404">404</div>
		
		<div class="notfound">${not_found_message}</div>
		<c:if test="${duplicateLogin eq 'true'}">
			<div class="notfound">${login_already_exists}</div>
		</c:if>
		<a href="Controller?command=GO_TO_INDEX_PAGE">${to_home_page}</a>
	</div>

</body>
</html>