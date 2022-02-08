<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>
</head>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc"/>
	
	<link rel="stylesheet" href="css/styles.css" type="text/css">
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.button.loginout" var="login_out"/>
	<fmt:message bundle="${loc}" key="local.button.my.account" var="my_account"/>
	<fmt:message bundle="${loc}" key="local.button.registration" var="registration"/>
	<fmt:message bundle="${loc}" key="local.button.logination" var="login_in"/>
	<fmt:message bundle="${loc}" key="local.booking" var="booking"/>
	<fmt:message bundle="${loc}" key="local.contacts" var="contacts"/>
	<fmt:message bundle="${loc}" key="local.gallery" var="gallery"/>
	<fmt:message bundle="${loc}" key="local.reviews" var="reviews"/>
	<fmt:message bundle="${loc}" key="local.button.booking.requests" var="booking_requests"/>
	<fmt:message bundle="${loc}" key="local.button.confirmed.requests" var="confirmed_requests"/>

	<fmt:message bundle="${loc}" key="local.administrator.id" var="administrator_id"/>
	<fmt:message bundle="${loc}" key="local.bill.id" var="bill_id"/>
	<fmt:message bundle="${loc}" key="local.confirmation.date" var="confirmation_date"/>
	<fmt:message bundle="${loc}" key="local.button.close" var="close"/>
	<fmt:message bundle="${loc}" key="local.payment.date" var="date_of_payment"/>
	<fmt:message bundle="${loc}" key="local.button.new" var="button_new"/>
	<fmt:message bundle="${loc}" key="local.button.all" var="button_all"/>
	<fmt:message bundle="${loc}" key="local.button.active" var="button_active"/>
	<fmt:message bundle="${loc}" key="local.button.completed" var="button_completed"/>
	
	
	<fmt:message bundle="${loc}" key="local.button.management" var="management"/>


<body>
	
	
	<div class="header_top">
		<div class="buttons_home_back">	
			<a href="Controller?command=GO_TO_INDEX_PAGE"><i class="fas fa-home"></i></a>
			<a href="${header.referer}"><i class="fas fa-arrow-alt-circle-left"></i></a>
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
	<c:if test="${page eq 'main_page' or empty page}">
		<div class="header_line">
			<!--<form action="Controller?command=GO_TO_BOOKING_PAGE" method="post">
				<input type="submit" value="booking"/>
			</form> -->
			
			<div class="header_buttons">
				<c:if test="${isAdmin}">
				<a href="Controller?command=GO_TO_MANAGEMENT_PAGE">${management}</a>
				</c:if>
				<a href="Controller?command=GO_TO_BOOKING_PAGE">${booking}</a>
				<a href="Controller?command=GO_TO_GALLERY_PAGE">${gallery}</a>
				<a href="">${contacts}</a>
				<a href="Controller?command=GO_TO_REVIEWS_PAGE">${reviews}</a>
				<a href="Controller?command=GO_TO_TEST">TEST</a>	
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
	
	<c:if test="${page eq 'booking_requests_page'}">
			
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
	</c:if>
	
	<c:if test="${page eq 'places_list'}">
			
			<div class="header_line">
			
			<div class="header_buttons">
				<a href="Controller?command=GO_TO_PLACES_LIST_PAGE&button=new">add new</a>		
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
	
	
	<c:if test="${page eq 'lockers_list'}">
			
			<div class="header_line">
			
			<div class="header_buttons">
				<a href="Controller?command=GO_TO_LOCKERS_LIST_PAGE&button=new">add new</a>		
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
	
	

</body>
</html>