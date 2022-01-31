<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>


<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

	<link rel="stylesheet" href="css/proformaInvoicePageStyle.css" type="text/css">

	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.booking.check_in" var="check_in"/>
	<fmt:message bundle="${loc}" key="local.booking.check_out" var="check_out"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_places" var="nPlaces"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_lockers" var="nLockers"/>
	<fmt:message bundle="${loc}" key="local.total" var="total"/>
	<fmt:message bundle="${loc}" key="local.booking.button.book" var="book"/>
	<fmt:message bundle="${loc}" key="local.cost.calculation" var="costCalculation"/>
	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button"/>

</head>
<body>

<c:set var="startDate" scope="request" value="${startDate}"></c:set>
<c:set var="endDate" scope="request" value="${endDate}"></c:set>
<c:set var="numberOfPlaces" scope="request" value="${numberPlaces}"></c:set>
<c:set var="numberOfLockers" scope="request" value="${numberLockers}"></c:set>
<c:set var="totalBill" scope="request" value="${totalBill}"></c:set>

	<div class="header_top">
		<div class="welcome_message">
			<h1>${costCalculation}</h1>
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

	<table>
		<tr>
			<td>${check_in}</td>
			<td>${startDate}</td>	
		</tr>
		
		<tr>
			<td>${check_out}</td>
			<td>${endDate}</td>	
		</tr>
		
		<tr>
			<td>${nPlaces}</td>
			<td>${numberOfPlaces}</td>	
		</tr>
		
		<tr>
			<td>${nLockers}</td>
			<td>${numberOfLockers}</td>	
		</tr>
		
		<tr>
			<td>${total}</td>
			<td>${totalBill}</td>	
		</tr>
	
	</table>

	<div class="main">
	<form  method="post" action="Controller?command=SAVE_BOOKING_REQUEST">
		<input type="hidden" name="startDate" value="${startDate}">
		<input type="hidden" name="endDate" value="${endDate}">
		<input type="hidden" name="numberPlaces" value="${numberPlaces}">
		<input type="hidden" name="numberLokers" value="${numberLockers}">
		<input type="submit" value="${book}"/>
	</form>
	</div>
	

	
	


</body>
</html>