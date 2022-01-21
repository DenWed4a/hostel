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
<body>
<c:set var="message" scope ="page" value="${2*2000 }"/>
<c:out value="${message}"/>

<h2>Register here</h2>
<br/>

<form action="Controller" method = "post" >
		<input type = "hidden" name = "command" value = "registration">

<label>Login:</label>
		<input type = "text" name = "login" value = ""/>
		<br/>
<label>Password:</label>
		<input type = "password" name = "password" value = "" />
		<br/>
<label>Name:</label>
		<input type = "text" name = "name" value = ""/>
		<br/>
<label>Surname: </label>
		<input type = "text" name = "surname" value = ""/>
		<br/>
<label>Phone Number:</label>
		<input type = "text" name = "phoneNumber" value = ""/>	
		<br/>
<label>Email:</label>
		<input type = "text" name = "email" value = ""/>	
		<br/>
		<input type = "submit" value = "complete"></input>
		<br/>
		<a href = "/hostel-web-app">
		<input type = "button" value = "back to main page"></input>	
		</a>									
		</form>

</body>
</html>