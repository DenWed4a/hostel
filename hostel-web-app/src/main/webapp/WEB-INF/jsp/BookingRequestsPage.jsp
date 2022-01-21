<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc"/>
	
	<link rel="stylesheet" href="css/styles.css" type="text/css">
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.booking.check_out" var="check_out"/>
	<fmt:message bundle="${loc}" key="local.booking.check_in" var="check_in"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_lockers" var="n_lockers"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_places" var="n_places"/>
	<fmt:message bundle="${loc}" key="local.booking.requests.create_date" var="create_date"/>
	<fmt:message bundle="${loc}" key="local.status" var="status"/>
	<fmt:message bundle="${loc}" key="local.client_id" var="client_id"/>


</head>
<body>
		
		<c:set var="list_booking_requests" scope="page" value="${requests}"></c:set>
		
	
	
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
	<div class="table">
		<table>
		<tr class="sticky">
			<th>Id</th>
			<th>${check_in}</th>
			<th>${check_out}</th>
			<th>${n_places}</th>
			<th>${n_lockers}</th>
			<th>${status}</th>
			<th>${client_id}</th>
			<th>${create_date}</th>
		</tr>
		<c:forEach  items = "${list_booking_requests}" var="i">
			<tr>
				<td>${i.id}</td>
				<td>${i.startDate}</td>
				<td>${i.endDate}</td>
				<td>${i.numberOfPlaces}</td>
				<td>${i.numberOfLockers}</td>
				<td>${i.status}</td>
				<td>${i.clientId}</td>
				<td>${i.timeOfCreation}</td>
				
				
				
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>