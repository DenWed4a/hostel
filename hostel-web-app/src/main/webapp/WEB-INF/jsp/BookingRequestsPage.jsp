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
	<fmt:message bundle="${loc}" key="local.button.booking.requests" var="booking_requests"/>
	<fmt:message bundle="${loc}" key="local.button.confirmed.requests" var="confirmed_requests"/>
	<fmt:message bundle="${loc}" key="local.button.loginout" var="login_out"/>
	<fmt:message bundle="${loc}" key="local.button.my.account" var="my_account"/>
	<fmt:message bundle="${loc}" key="local.button.registration" var="registration"/>
	<fmt:message bundle="${loc}" key="local.button.logination" var="login_in"/>
	<fmt:message bundle="${loc}" key="local.administrator.id" var="administrator_id"/>
	<fmt:message bundle="${loc}" key="local.bill.id" var="bill_id"/>
	<fmt:message bundle="${loc}" key="local.confirmation.date" var="confirmation_date"/>
	<fmt:message bundle="${loc}" key="local.button.close" var="close"/>
	<fmt:message bundle="${loc}" key="local.payment.date" var="date_of_payment"/>
	<fmt:message bundle="${loc}" key="local.button.new" var="button_new"/>
	<fmt:message bundle="${loc}" key="local.button.all" var="button_all"/>
	<fmt:message bundle="${loc}" key="local.button.active" var="button_active"/>
	<fmt:message bundle="${loc}" key="local.button.completed" var="button_completed"/>
	
	<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>
</head>
<body>
		
		<c:set var="list_booking_requests" scope="page" value="${requests}"></c:set>
		<c:set var="list_confirmed_requests" scope="page" value="${confirmedRequests}"></c:set>
		<c:set var="current_table" scope="page" value="${table}"></c:set>
		
		
		
	
	
	<div class="header_top">
	
		<div class="buttons_home_back">	
			<a href="Controller?command=GO_TO_INDEX_PAGE"><i class="fas fa-home"></i></a>
			<a href="Controller?command=GO_TO_MANAGEMENT_PAGE"><i class="fas fa-arrow-alt-circle-left"></i></a>
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
			<a  href="#booking_hidden_buttons">${booking_requests}</a>
				<div id="booking_hidden_buttons">
					<a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=booking&button=new">${button_new}</a>
					<a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=booking&button=all">${button_all}</a>			
				</div>
			<a href="#confirmed_hidden_buttons">${confirmed_requests}</a>
				<div id="confirmed_hidden_buttons">
					<a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=confirmed&button=active">${button_active}</a>
					<a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=confirmed&button=all">${button_all}</a>			
				</div>
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

	<c:choose>
		<c:when test="${ empty current_table or current_table eq 'booking'}"> 
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
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach  items = "${list_booking_requests}" var="i">
						<c:if test="${(empty button or button eq 'new') and i.status eq 'new'}">			
						<tr>
							<td>${i.id}</td>
							<td>${i.startDate}</td>
							<td>${i.endDate}</td>
							<td>${i.numberOfPlaces}</td>
							<td>${i.numberOfLockers}</td>
							<td>${i.status}</td>
							<td><a href="Controller?command=GO_TO_USER_INFO_PAGE&id=${i.clientId}&p_page=user_info_page">${i.clientId}</a></td>
							<td>${i.timeOfCreation}</td>	
							<td><a href="Controller?command=GO_TO_CHANGE_BOOKING_REQUEST&booking_request_id=${i.id}"><i class="fas fa-pen"></i></a></td>							
							<td><a href="Controller?command=DELETE_BOOKING_REQUEST&booking_request_id=${i.id}"><i class="far fa-trash-alt"></i></a></td>							
							<td><a href="Controller?command=GO_TO_BOOKING_PROCESSING_PAGE&booking_request_id=${i.id}"><i class="fas fa-arrow-right"></i></a></td>						
						</tr>
						</c:if>
						
						<c:if test="${button eq 'all' and (i.status eq 'confirmed' or i.status eq 'deleted')}">			
						<tr>
							<td>${i.id}</td>
							<td>${i.startDate}</td>
							<td>${i.endDate}</td>
							<td>${i.numberOfPlaces}</td>
							<td>${i.numberOfLockers}</td>
							<td>${i.status}</td>
							<td><a href="Controller?command=GO_TO_USER_INFO_PAGE&id=${i.clientId}&p_page=user_info_page">${i.clientId}</a></td>
							<td>${i.timeOfCreation}</td>	
							<td> <a  href="Controller?command=GO_TO_BOOKING_PROCESSING_PAGE&booking_request_id=${i.id}"><i class="fas fa-pen"></i></a></td>
							<td><a href="Controller?command=DELETE_BOOKING_REQUEST&booking_request_id=${i.id}"><i class="far fa-trash-alt"></i></a></td>						
						</tr>
						</c:if>
						
						<c:if test="${button eq 'info_by_id' and (i.status eq 'confirmed') and (i.id eq b_info_id)}">			
						<tr>
							<td>${i.id}</td>
							<td>${i.startDate}</td>
							<td>${i.endDate}</td>
							<td>${i.numberOfPlaces}</td>
							<td>${i.numberOfLockers}</td>
							<td>${i.status}</td>
							<td><a href="Controller?command=GO_TO_USER_INFO_PAGE&id=${i.clientId}&p_page=user_info_page">${i.clientId}</a></td>
							<td>${i.timeOfCreation}</td>	
							<td> <a  href="Controller?command=GO_TO_BOOKING_PROCESSING_PAGE&booking_request_id=${i.id}"><i class="fas fa-pen"></i></a></td>
							<td><a href="Controller?command=DELETE_BOOKING_REQUEST&booking_request_id=${i.id}"><i class="far fa-trash-alt"></i></a></td>						
						</tr>
						</c:if>
						
					</c:forEach>
				
				</table>	
			</div>
		</c:when>
		<c:when test="${not empty current_table and current_table eq 'confirmed'}">
			<div class="table">
				<table>
					<tr class="sticky">
						<th>Id</th>
						<th>${bill_id} </th>
						<th>${administrator_id}</th>
						<th>${confirmation_date}</th>
						<th>${status}</th>
						<th>${date_of_payment}</th>	
					</tr>
					
				<c:forEach  items = "${list_confirmed_requests}" var="i">
					<c:if test="${i.status.toString() eq 'ACTIVE'}">
						<tr>
							<td><a href="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=booking&button=info_by_id&b_info_id=${i.id}">${i.id}</a></td>
							<td><a href="Controller?command=GO_TO_BILL_PAGE&booking_id=${i.id}">${i.billId} </a></td>
							<td>${i.administratorId}</td>
							<td>${i.confirmationDate}</td>
							<td><form method="post" action="Controller?command=GO_TO_BOOKING_REQUESTS_PAGE&table=confirmed&button=active&confirmed_request_id=${i.id}">								
									<select name="status_name">
										<option value="0">${i.status}</option>								
										<option value="1">COMPLITED</option>								
									</select>
									<input type="submit" value="Save"/>
								</form>							
							</td>
							<td>${i.dateOfPayment}</td>
							<td>${j.startDate}</td>	
						</tr>
					</c:if>
						
					<c:if test="${button eq 'all' and (i.status eq 'COMPLITED' or i.status eq 'DELETED')}">
						<tr>
							<td>${i.id}</td>
							<td><a href="Controller?command=GO_TO_BILL_PAGE&booking_id=${i.id}">${i.billId} </a></td>
							<td>${i.administratorId}</td>
							<td>${i.confirmationDate}</td>
							<td>${i.status}</td>
							<td>${i.dateOfPayment}</td>
							<td>${j.startDate}</td>	
						</tr>
					</c:if>						
				</c:forEach>							
				</table>	
				
			</div>
		</c:when> 
		
		<c:when test="${ current_table eq 'alone'}">
		
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
						<th></th>
						<th></th>
						<th><div class="print">
										<c:out value=""/>
									</div></th>
						<th>${booking_id_for_input}</th>			
					</tr>
					<c:forEach  items = "${list_booking_requests}" var="i">
					
						<c:if test="${booking_id_for_input eq i.id}">		
							<tr>
								<td>${i.id}</td>
								<td>${i.startDate}</td>
								<td>${i.endDate}</td>
								<td>${i.numberOfPlaces}</td>
								<td>${i.numberOfLockers}</td>
								<td>${i.status}</td>
								<td><a href="Controller?command=GO_TO_USER_INFO_PAGE&id=${i.clientId}">${i.clientId}</a></td>
								<td>${i.timeOfCreation}</td>	
								<td> <a  href="Controller?command=GO_TO_BOOKING_PROCESSING_PAGE&booking_request_id=${i.id}"><i class="fas fa-pen"></i></a></td>
								<td><a href="Controller?command=DELETE_BOOKING_REQUEST&booking_request_id=${i.id}"><i class="far fa-trash-alt"></i></a></td>
								<td><i class="fas fa-arrow-right"></i></td>						
							</tr>
						</c:if>			
					</c:forEach>
				
				</table>	
			</div>
		
		</c:when>
	
	</c:choose>
	<script src="js/script.js"></script>
	
</body>
</html>