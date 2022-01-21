<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.button.registration" var="registration"/>
	<fmt:message bundle="${loc}" key="local.button.logination" var="login_in"/>
	<fmt:message bundle="${loc}" key="local.booking" var="booking"/>
	<fmt:message bundle="${loc}" key="local.contacts" var="contacts"/>
	<fmt:message bundle="${loc}" key="local.gallery" var="gallery"/>
	<fmt:message bundle="${loc}" key="local.reviews" var="reviews"/>
	<fmt:message bundle="${loc}" key="local.button.loginout" var="login_out"/>
	<fmt:message bundle="${loc}" key="local.button.my.account" var="my_account"/>
	<fmt:message bundle="${loc}" key="local.button.management" var="management"/>
	
	
	
	<link rel="stylesheet" href="css/styles.css" type="text/css">

</head>

<body>
	
	<div class="header_top">	
		<div class="welcome_message">
			<c:if test="${not empty role and role eq 'Administrator'}" var="isAdmin">	
			<h1>Hello, Administrator!</h1>	
			</c:if>
		
			<c:if test="${empty role}" var = "isUnknownUser">
			<h1>Welcome to the coolest hostel in the World!</h1>
			</c:if>
		</div>
		
		<div class="language_change">
			<form action="Controller?command=CHANGE_LOCALE" method="get">
				<input type="hidden" name="command" value="CHANGE_LOCALE">
				<input type="hidden" name="local" value="ru"/>
				<input class="language-button" type="submit"  value="${ru_button}"/>
			</form>
			
			<form action="Controller?command=CHANGE_LOCALE" method="post">
				<input type="hidden" name="local" value="en"/>
				<input class="language-button" type="submit"  value="${en_button}"/>
			</form>
		</div>
	</div>
	
	<div class="header_line">
		<!--<form action="Controller?command=GO_TO_BOOKING_PAGE" method="post">
			<input type="submit" value="booking"/>
		</form> -->
		
		<div class="header_buttons">
			<c:if test="${isAdmin}">
			<a href="Controller?command=GO_TO_MANAGEMENT_PAGE">${management}</a>
			</c:if>
			<a href="Controller?command=GO_TO_BOOKING_PAGE">${booking}</a>
			<a href="">${gallery}</a>
			<a href="">${contacts}</a>
			<a href="">${reviews}</a>	
		</div>
		
		<div class="logination_registration">
			<c:if test="${empty role}" var = "isUnknownUser">
				<a href="Controller?command=GO_TO_LOGINATION_PAGE" >${login_in}</a>					
				<a href="Controller?command=GO_TO_REGISTRATION_PAGE">${registration}</a>
			</c:if>
		
			<c:if test="${not empty login}" var="isLoginIn">
					<a href="Controller?command=GO_TO_ACCAUNT_PAGE">${my_account}</a>
					<a href="Controller?command=LOGOUT">${login_out}</a>	
			</c:if>
		</div>
		
	</div>

	
</body>
</html>