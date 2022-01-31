<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	
	<link rel="stylesheet" href="css/styles.css" type="text/css">
	<c:set var="userInfo" scope="page" value="${user}"></c:set>
	<c:set var="userDetailInfo" scope="page" value="${userDetail}"></c:set>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.user.info.name" var="name"/>
	<fmt:message bundle="${loc}" key="local.user.info.surname" var="surname"/>
	<fmt:message bundle="${loc}" key="local.user.info.phone.number" var="phone_number"/>
	<fmt:message bundle="${loc}" key="local.user.info.email" var="email"/>
	<fmt:message bundle="${loc}" key="local.user.info.rating" var="rating"/>
	<fmt:message bundle="${loc}" key="local.user.info.address" var="address"/>
	<fmt:message bundle="${loc}" key="local.user.info.nationality" var="nationality"/>
	<fmt:message bundle="${loc}" key="local.user.info.date.of.birth" var="date_of_birth"/>
	<fmt:message bundle="${loc}" key="local.user.info.passport.number" var="passport_number"/>
	<fmt:message bundle="${loc}" key="local.user.info.passport.date.of.issue" var="passport_date_of_issue"/>
	<fmt:message bundle="${loc}" key="local.user.info.passport.date.of.expire" var="passport_date_of_expire"/>
	

	
	
</head>
<body>
	<jsp:include page="blank/Header.jsp"></jsp:include>
		<div class="table">
		<table>
			<tr>
				<td>Id</td>
				<td>${userInfo.id}</td>		
			</tr>
			
			<tr>
				<td>${name}</td>
				<td>${userDetailInfo.name}</td>	
			</tr>
			
			<tr>
				<td>${surname}</td>
				<td>${userDetailInfo.surname}</td>	
			</tr>
			
			<tr>
				<td>${phone_number}</td>
				<td>${userDetailInfo.phoneNumber}</td>	
			</tr>
			
			<tr>
				<td>${email}</td>
				<td>${userDetailInfo.email}</td>	
			</tr>
			
			<tr>
				<td>${rating}</td>
				<td>${userDetailInfo.rating}</td>	
			</tr>
			
			<tr>
				<td>${address}</td>
				<td>${userDetailInfo.address}</td>	
			</tr>
			
						
	
		<!--
		private double reiting;
		private String passportNumber;
		private String nationality;
		private Date dateOfBirth;
		private Date passportDateOfIssue;
		private Date passportDateOfExpire;
		private String address;
		-->
			
			<tr>
				<td>${nationality}</td>
				<td>${userDetailInfo.nationality}</td>	
			</tr>
			
			<tr>
				<td>${date_of_birth}</td>
				<td>${userDetailInfo.dateOfBirth}</td>	
			</tr>
			
			<tr>
				<td>${passport_number}</td>
				<td>${userDetailInfo.passportNumber}</td>	
			</tr>
			
			<tr>
				<td>${passport_date_of_issue}</td>
				<td>${userDetailInfo.passportDateOfIssue}</td>	
			</tr>
			
			<tr>
				<td>${passport_date_of_expire}</td>
				<td>${userDetailInfo.passportDateOfExpire}</td>	
			</tr>
			
	
			
		
		</table>
		</div>

</body>
</html>