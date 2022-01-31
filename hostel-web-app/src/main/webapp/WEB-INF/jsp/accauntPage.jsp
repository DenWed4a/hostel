<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>

<body>
	
	<c:set var="user" scope="page" value="${user}"></c:set>
	
	<c:set var="detail" scope="page" value="${detail}"></c:set>
	<jsp:include page="blank/Header.jsp"></jsp:include>
		
	<div class="wrapper">	
		<div id="photo_edit">
			<img id="personal-photo" src="${pageContext.request.contextPath}${detail.image}" alt="photo"/>
			<a id="button_edit-personal-info" href="Controller?command=GO_TO_CHANGE_ACCAUNT_INFO">edit</a>
		</div>		
		<div id="personal-info">		
			<div class="doNotShow">
				<span>Id</span>
				<span>${user.id}</span>
			</div>			
			<div class="flex-row">
				<span>Login:</span>
				<span>${user.login}</span>
			</div>
			<div class="flex-row">
				<span>Name:</span>
				<span>${detail.name}</span>
			</div>
			<div class="flex-row">
				<span>Surname:</span>
				<span>${detail.surname}</span>
			</div>
			<div class="flex-row">
				<span>Email:</span>
				<span>${detail.email}</span>
			</div>
			<div class="flex-row">
				<span>Phone number:</span>
				<span>${detail.phoneNumber}</span>
			</div>
			<div class="flex-row">
				<span>Passport number:</span>
				<span>${detail.passportNumber}</span>
			</div>
			<div class="flex-row">
				<span>Nationality:</span>
				<span>${detail.nationality}</span>
			</div>
			<div class="flex-row">
				<span>Date of birth:</span>
				<span>${detail.dateOfBirth}</span>
			</div>
			<div class="flex-row">
				<span>Passport date of issue:</span>
				<span>${detail.passportDateOfIssue}</span>
			</div>
			<div class="flex-row">
				<span>Passport date of expire:</span>
				<span>${detail.passportDateOfExpire}</span>
			</div>
			<div class="flex-row">
				<span>Address:</span>
				<span>${detail.address}</span>
			</div>			
		</div>			  
	</div>
</body>
</html>