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
	<fmt:message bundle="${loc}" key="local.login" var="login"/>
	<fmt:message bundle="${loc}" key="local.password" var="password"/>
	<fmt:message bundle="${loc}" key="local.user.info.name" var="name"/>
	<fmt:message bundle="${loc}" key="local.user.info.surname" var="surname"/>
	<fmt:message bundle="${loc}" key="local.user.info.phone.number" var="phone_number"/>
	<fmt:message bundle="${loc}" key="local.user.info.email" var="email"/>
	<fmt:message bundle="${loc}" key="local.button.registration" var="registration"/>
	<fmt:message bundle="${loc}" key="local.complete" var="complete"/>
	<fmt:message bundle="${loc}" key="local.button.back_to_main_page" var="back_to_main"/>
	<fmt:message bundle="${loc}" key="local.already.have.an.account" var="question"/>
	<fmt:message bundle="${loc}" key="local.button.logination" var="log_in_button"/>
	
	
</head>

  
<style>
label {
	width: 170px;
	display: inline-block;
}
</style>

<link rel="stylesheet" href="css/loginationStyle.css" type="text/css">
<body>


	<div class="header_top">	
		
		<div class="welcome_message">
			<h1>${registration}</h1>			
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
		<form class="login_forms_column" action="Controller" method = "post" >
			<input type = "hidden" name = "command" value = "registration">
			<div class="form-row">
				<label for="1">${login}:</label>
				<input id="1" type = "text" name = "login" value = ""/>
			</div>
			<div class="form-row">
				<label for="2">${password}:</label>
				<input id="2" type = "password" name = "password" value = "" />
			</div>
			<div class="form-row">
				<label for="3">${name}:</label>
				<input id="3" type = "text" name = "name" value = ""/>
			</div>
			<div class="form-row">
				<label for="4">${surname}: </label>
				<input id="4" type = "text" name = "surname" value = ""/>
			</div>
			<div class="form-row">
				<label for="5">${phone_number}:</label>
				<input id="5" type = "text" name = "phoneNumber" value = ""/>
			</div>
			<div class="form-row">
				<label for="6">${email}:</label>
				<input id="6" type = "email" name = "email" value = ""/>
			</div>
			<input type = "submit" value = "${complete}"></input>
			
				<a class="centration" href = "/hostel-web-app">${back_to_main}</a>	
				<div class="centration">${question}<a href="Controller?command=GO_TO_LOGINATION_PAGE">${log_in_button}</a></div>
									
		</form>
	</div>
	
			

</body>
</html>