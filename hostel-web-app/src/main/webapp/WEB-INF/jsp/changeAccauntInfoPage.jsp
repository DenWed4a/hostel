<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
[name="id"]{display:none;}
</style>
</head>
<body>

<%--     
--%>
<c:set var="user" scope="page" value="${user}"></c:set>

<c:set var="detail" scope="page" value="${detail}"></c:set>




<form action="Controller" method="post">
<input type=hidden name="command" value="SAVE_ACCAUNT_CHANGES">

<c:if test="${not empty role and role eq 'Administrator' }" var="isAdmin">

<table>

<tr><th></th><td><input  type = "text" name = "id" value = "${user.id}"></td></tr>
<tr><th>Login:</th><td><input type = "text" name = "name" value = "${user.login}"></td></tr>
<tr><th>Name:</th><td><input type = "text" name = "name" value = "${detail.name}"></td></tr>
<tr><th>Surname:</th><td><input type = "text" name = "surname" value = "${detail.surname}"></td></tr>
<tr><th>Email:</th><td><input type = "text" name = "email" value = "${detail.email}"></td></tr>
<tr><th>Phone number:</th><td><input type = "text" name = "phoneNumber" value = "${detail.phoneNumber}"></td></tr>
<tr><th>Passport number:</th><td><input type = "text" name = "passportNumber" value = "${detail.passportNumber}"></td></tr>
<tr><th>Nationality:</th><td><input type = "text" name = "nationality" value = "${detail.nationality}"></td></tr>
<tr><th>Date of birth:</th><td><input type = "text" name = "dateOfBirth" value = "${detail.dateOfBirth}"></td></tr>
<tr><th>Passport date of issue:</th><td><input type = "text" name = "issue" value = "${detail.passportDateOfIssue}"></td></tr>
<tr><th>Passport date of expire:</th><td><input type = "text" name = "expire" value = "${detail.passportDateOfExpire}"></td></tr>
<tr><th>Address:</th><td><input type = "text" name = "address" value = "${detail.address}"></td></tr>

</table>
</c:if>

<c:if test="${not empty role and role eq 'Client' }" var="isClient">

<table>

<tr><th></th><td><input  type = "text" name = "id" value = "${user.id}"></td></tr>
<tr><th>Login:</th><td><input disabled type = "text" name = "name" value = "${user.login}"></td></tr>
<tr><th>Name:</th><td><input  type = "text" name = "name" value = "${detail.name}"></td></tr>
<tr><th>Surname:</th><td><input  type = "text" name = "surname" value = "${detail.surname}"></td></tr>
<tr><th>Email:</th><td><input type = "text" name = "email" value = "${detail.email}"></td></tr>
<tr><th>Phone number:</th><td><input type = "text" name = "phoneNumber" value = "${detail.phoneNumber}"></td></tr>
<tr><th>Passport number:</th><td><input disabled type = "text" name = "passportNumber" value = "${detail.passportNumber}"></td></tr>
<tr><th>Nationality:</th><td><input type = "text" name = "nationality" value = "${detail.nationality}"></td></tr>
<tr><th>Date of birth:</th><td><input disabled type = "text" name = "dateOfBirth" value = "${detail.dateOfBirth}"></td></tr>
<tr><th>Passport date of issue:</th><td><input disabled type = "text" name = "issue" value = "${detail.passportDateOfIssue}"></td></tr>
<tr><th>Passport date of expire:</th><td><input disabled type = "text" name = "expire" value = "${detail.passportDateOfExpire}"></td></tr>
<tr><th>Address:</th><td><input type = "text" name = "adress" value = "${detail.address}"></td></tr>

</table>

</c:if>


	<input type="submit" value="save"/>
    <%-- <a href="Controller?command=SAVE_ACCAUNT_CHANGES">
	
    </a>--%>
 </form> 

 
</body>
</html>