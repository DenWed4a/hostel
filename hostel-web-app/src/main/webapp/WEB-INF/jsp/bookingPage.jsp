<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>


<link rel="stylesheet" href="css/bookingStyle.css" type="text/css">
<body>

	
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.booking.header" var="header_message"/>
	<fmt:message bundle="${loc}" key="local.booking.check_out" var="check_out"/>
	<fmt:message bundle="${loc}" key="local.booking.check_in" var="check_in"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_lockers" var="n_lockers"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_places" var="n_places"/>
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.booking.sent.message" var="bs_message"/>
	<fmt:message bundle="${loc}" key="local.booking.button.book" var="book"/>
	
	
	
	<div class="header_top">
		<div class="welcome_message">
			<h1>${header_message}</h1>
		</div>
	
		<div class="language_change">
			<form action="Controller?command=CHANGE_LOCALE" method="post">
				<input type="hidden" name="local" value="ru"/>
				<input class="language-button" type="submit"  value="${ru_button}"/>
			</form>
			
			<form action="Controller?command=CHANGE_LOCALE" method="post">
				<input type="hidden" name="local" value="en"/>
				<input class="language-button" type="submit"  value="${en_button}"/>
			</form>
		</div>
	</div>



	<form class="main" method="post" action="Controller?command=SAVE_BOOKING_REQUEST">
		<div class="dates">	
			<div class="check_in_date">
				<label>${check_in}:</label> 
				<input type="date" name="startDate"/>
			</div>
			
			<div class="check_out_date">
				<label>${check_out}:</label> 
				<input  type="date" name="endDate"/>			
			</div>
		</div>

	<div class="places_lockers">
	
			<div class="places">
				<label>${n_places}:</label>
				<select name = "numberPlaces">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				</select>
			</div>
			
			<div class="lockers">
				<label>${n_lockers}:</label>
				<select name = "numberLokers">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				</select>
			</div>
	</div>

	<input type="submit" value="${book}">
	</form>
	
	
	<c:if test="${sessionScope.bookingMessage eq 'itSent'}" var = "requestSent">
	
	<div class="sent_message">
	<h1>${bs_message}</h1>
	</div>
	
	</c:if>
	<c:remove var="bookingMessage" scope="session"/>
				
				
			





</body>
</html>