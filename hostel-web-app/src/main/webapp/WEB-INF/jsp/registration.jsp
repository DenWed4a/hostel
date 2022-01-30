<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
label {
	width: 120px;
	display: inline-block;
}
</style>

<link rel="stylesheet" href="css/loginationStyle.css" type="text/css">
<body>

	<div class="header_top">	
		
		<div class="welcome_message">
			<h1>Register here</h1>			
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
				<label for="1">Login:</label>
				<input id="1" type = "text" name = "login" value = ""/>
			</div>
			<div class="form-row">
				<label for="2">Password:</label>
				<input id="2" type = "password" name = "password" value = "" />
			</div>
			<div class="form-row">
				<label for="3">Name:</label>
				<input id="3" type = "text" name = "name" value = ""/>
			</div>
			<div class="form-row">
				<label for="4">Surname: </label>
				<input id="4" type = "text" name = "surname" value = ""/>
			</div>
			<div class="form-row">
				<label for="5">Phone:</label>
				<input id="5" type = "text" name = "phoneNumber" value = ""/>
			</div>
			<div class="form-row">
				<label for="6">Email:</label>
				<input id="6" type = "text" name = "email" value = ""/>
			</div>
			<input type = "submit" value = "complete"></input>
			<a href = "/hostel-web-app">${back_to_main}</a>									
		</form>
	</div>

</body>
</html>