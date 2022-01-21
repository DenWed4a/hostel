<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.doNotShow{display:none;}
</style>
</head>
<body>


<c:set var="user" scope="page" value="${user}"></c:set>

<c:set var="detail" scope="page" value="${detail}"></c:set>



<form action="Controller" method="post">

<table>

<tr class="doNotShow"><th>Id</th><td>${user.id}</td></tr>
<tr><th>Login:</th><td>${user.login}</td></tr>
<tr><th>Name:</th><td>${detail.name}</td></tr>
<tr><th>Surname:</th><td>${detail.surname}</td></tr>
<tr><th>Email:</th><td>${detail.email}</td></tr>
<tr><th>Phone number:</th><td>${detail.phoneNumber}</td></tr>
<tr><th>Passport number:</th><td>${detail.passportNumber}</td></tr>
<tr><th>Nationality:</th><td>${detail.nationality}</td></tr>
<tr><th>Date of birth:</th><td>${detail.dateOfBirth}</td></tr>
<tr><th>Passport date of issue:</th><td>${detail.passportDateOfIssue}</td></tr>
<tr><th>Passport date of expire:</th><td>${detail.passportDateOfExpire}</td></tr>
<tr><th>Address:</th><td>${detail.address}</td></tr>

</table>

	
    <a href="Controller?command=GO_TO_CHANGE_ACCAUNT_INFO">
	<input type="button" value="Change info"/>
    </a>
    
    <a href="Controller?command=LOGOUT">
    <input type="button" value="Logout"/>
    </a>
</form>
</body>
</html>