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
	<link rel="stylesheet" href="css/bookingProcessingPageStyle.css" type="text/css">
	
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.bed.place.tier" var="tier"/>
	<fmt:message bundle="${loc}" key="local.photo" var="photo"/>
	<fmt:message bundle="${loc}" key="local.select.bed.places" var="select_beds"/>
	<fmt:message bundle="${loc}" key="local.select.lockers" var="select_lockers"/>
	<fmt:message bundle="${loc}" key="local.locker.size" var="size"/>
	<fmt:message bundle="${loc}" key="local.button.confirm" var="confirm"/>
	<fmt:message bundle="${loc}" key="local.button.loginout" var="login_out"/>
	<fmt:message bundle="${loc}" key="local.button.my.account" var="my_account"/>
	<fmt:message bundle="${loc}" key="local.button.registration" var="registration"/>
	<fmt:message bundle="${loc}" key="local.button.logination" var="login_in"/>
	
	<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>
</head>
<body>

	<c:set var="bed_places_list" scope="page" value="${bedPlaces}"/>
	<c:set var="lockers_list" scope="page" value="${lockers}"/>
	<jsp:include page="blank/Header.jsp"></jsp:include>
	<!--<div class="header_top">
		<div class="buttons_home_back">	
			<a href="Controller?command=GO_TO_INDEX_PAGE"><i class="fas fa-home"></i></a>
			<a href="Controller?command=GO_TO_INDEX_PAGE"><i class="fas fa-arrow-alt-circle-left"></i></a>
		</div>
		
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
	-->
	
	
		
		<form action="Controller?command=SAVE_CONFIRMED_REQUEST&bill_id=${bill.id}&booking_request_id=${bookingRequest.id}" method="post">
			<div class="table-flex">
				<table class="bed_places_table">
					<caption>${select_beds}</caption>
					
					<tr class="sticky">
						<th>Id</th>
						<th>${tier}</th>
						<th></th>	
					</tr>

					<c:forEach  items = "${bed_places_list}" var="i">
					<tr>
						<td>${i.id}</td>
						<td>${i.tier}</td>
						<td><input name="bed_places_id" type="checkbox" value="${i.id}"></td>												
					</tr>
					</c:forEach>
				</table>
				
				<table class="lockers_table">
					<caption>${select_lockers}</caption>
					<tr class="sticky">
						<th>Id</th>
						<th>${size}</th>
						<th></th>										
					</tr>

				<c:forEach  items = "${lockers_list}" var="i">
					<tr>
						<td>${i.id}</td>
						<td>${i.size}</td>
						<td><input name="lockers_id" type="checkbox" value="${i.id}"></td>														
					</tr>
				</c:forEach>
				</table>
			</div>
			<div>
				<table class=bill_info>  
					<tr>
						<td>Колличество мест: ${bookingRequest.numberOfPlaces}</td>
						<td>Колличество ячеек: ${bookingRequest.numberOfLockers}</td>
						<!--  <td>Итого: ${bill.totalAmount}</td>-->	
						<td><input type="submit" value="${confirm}"></td>
					</tr>
				</table>
				
			</div>
			
				
		</form>
		
			

</body>
</html>