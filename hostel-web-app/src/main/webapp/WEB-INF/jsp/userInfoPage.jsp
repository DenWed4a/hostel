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
	<c:out value = "${for_import}"></c:out>
	<c:if test="${empty for_import}" var="forImport">
	
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
			
			<div class="header_buttons">
				<c:if test="${isAdmin}">
				<a href="Controller?command=GO_TO_MANAGEMENT_PAGE">${management}</a>
				</c:if>
				<a href="Controller?command=GO_TO_BOOKING_PAGE">${booking}</a>
				<a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=booking">${booking_requests}</a>
				<a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=confirmed">${confirmed_requests}</a>
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
	</c:if>
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
				<td>${userDetailInfo.reiting}</td>	
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