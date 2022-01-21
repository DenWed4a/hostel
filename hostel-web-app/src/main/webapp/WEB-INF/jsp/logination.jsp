<%@page import="com.epam.ds.hostel.entity.UserDetail"%>
<%@page import="com.epam.ds.hostel.entity.User"%>
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

	<fmt:message bundle="${loc}" key="local.login" var="login"/>
	<fmt:message bundle="${loc}" key="local.password" var="password"/>
	<fmt:message bundle="${loc}" key="local.button.back_to_main_page" var="back_to_main"/>
	<fmt:message bundle="${loc}" key="local.button.logination" var="button_login"/>
	<fmt:message bundle="${loc}" key="local.button.registration" var="button_registration"/>
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>

	<link rel="stylesheet" href="css/loginationStyle.css" type="text/css">

<body>


	<div class="header_top">	
	
	<div class="welcome_message">
		<h1>${button_login}</h1>			
	</div>
		
		<div class="language_change">
			<form action="Controller?command=CHANGE_LOCALE" method="post">
				<input type="hidden" name="local" value="ru"/>
				<input class="language-button" type="submit"  value="${ru_button}"/>
			</form>
			
			<form action="Controller?command=CHANGE_LOCALE" method="post">
				<input type="hidden" name="local" value="en"/>
				<input class="language-button" type="submit"  value="${en_button}"/>
			</form>
		</div>
	</div>




	<div class="main">
		
			
		
			<form class="login_forms" action="Controller" method="post">
				<input type="hidden" name="command" value="logination">
				<div class="labels">
					<label for="1">${login}:</label> 
					<label for="2">${password}:</label> 
				</div>
				
				<div class="inputs">
					<input id="1" type="text" name="login" value="" /> 
					<input id="2" type="password" name="password" value="" />
				</div>
				
				<input type="submit" value="${button_login}"></input> 
			</form>
			
			
			<a href="Controller?command=GO_TO_REGISTRATION_PAGE">${button_registration}</a>
			<a href = "/hostel-web-app">${back_to_main}</a>
			
	
	</div>

</body>
</html>