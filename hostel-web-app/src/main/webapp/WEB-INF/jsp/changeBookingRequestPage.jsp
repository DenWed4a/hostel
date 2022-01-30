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

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc"/>

	<fmt:message bundle="${loc}" key="local.booking.check_out" var="check_out"/>
	<fmt:message bundle="${loc}" key="local.booking.check_in" var="check_in"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_lockers" var="n_lockers"/>
	<fmt:message bundle="${loc}" key="local.booking.number_of_places" var="n_places"/>
	<fmt:message bundle="${loc}" key="local.booking.requests.create_date" var="create_date"/>
	<fmt:message bundle="${loc}" key="local.status" var="status"/>
	<fmt:message bundle="${loc}" key="local.client_id" var="client_id"/>
</head>
<body>
	
	<jsp:include page="blank/Header.jsp"></jsp:include>
	
	<div class="table">
		<form method="post" action="Controller?command=SAVE_BOOKING_CHANGES">
			<c:set var="booking_request" scope="session" value="${bookRequest}"/>
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
			</tr>
										
				<tr>
						<td>${booking_request.id}</td>
						<td><input type="date" name="startDate" value="${booking_request.startDate}"/></td>
						<td><input type="date" name="endDate" value="${booking_request.endDate}"/></td>
						<td>
							<select name = "numberPlaces">
							<option selected value="${booking_request.numberOfPlaces}">${booking_request.numberOfPlaces}</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							</select>
						</td>
						<td>
						<div class="lockers">
							<select name = "numberLokers">
							<option value="${booking_request.numberOfLockers}">${booking_request.numberOfLockers}</option>
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							</select>
						</div>
						</td>
						<td>${booking_request.status}</td>
						<td><a href="Controller?command=GO_TO_USER_INFO_PAGE&id=${booking_request.clientId}">${booking_request.clientId}</a></td>
						<td>${booking_request.timeOfCreation}</td>	
						<td></td>
						<td><button type="submit"><i class="far fa-save"></i></button></td>							
				</tr>
				
				
			</table>
			</form>
		</div>

</body>
</html>