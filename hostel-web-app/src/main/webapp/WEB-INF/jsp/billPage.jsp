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
	
	<fmt:message bundle="${loc}" key="local.bill.number" var="bill_number"/>
	<fmt:message bundle="${loc}" key="local.guest" var="guest"/>
	<fmt:message bundle="${loc}" key="local.booking.check_in" var="check_in"/>
	<fmt:message bundle="${loc}" key="local.booking.check_out" var="check_out"/>
	<fmt:message bundle="${loc}" key="local.total" var="total"/>
	<fmt:message bundle="${loc}" key="local.button.print" var="print"/>
	<fmt:message bundle="${loc}" key="local.confirm.payment" var="confirm_payment"/>
	<fmt:message bundle="${loc}" key="local.nomination" var="nomination"/>
	<fmt:message bundle="${loc}" key="local.number" var="number"/>
	<fmt:message bundle="${loc}" key="local.days.of.stay" var="days_of_stay"/>
	<fmt:message bundle="${loc}" key="local.tariff" var="tariff"/>
	<fmt:message bundle="${loc}" key="local.byn" var="byn"/>
	<fmt:message bundle="${loc}" key="local.cost" var="cost"/>
	<fmt:message bundle="${loc}" key="local.bed.place" var="place"/>
	<fmt:message bundle="${loc}" key="local.locker" var="locker"/>
	
	
</head>
<body>
 <jsp:include page="blank/Header.jsp"></jsp:include>
	<c:set var="bill" scope="page" value="${bill}"/>
	<c:set var="calc" scope="page" value="${calculator}"/>
	<c:set var="days" scope="page" value="${calc.getNumberDays(bookingRequest.startDate, bookingRequest.endDate)}"/>
	
  	<div class="bill">
	  	<h2>${bill_number}${bill.id}</h2>
	  	
	  	<div>${guest}: ${user.detail.name} ${user.detail.surname}</div>
	  	<div id="dates">
	  	<div>${check_in}: ${bookingRequest.startDate}</div>   
	  	<div>${check_out}: ${bookingRequest.endDate}</div>
	  	</div>
	  	<table border=1 id="bill_table">
	  		<tr>
	  		
	  		<th>${nomination}</th>
	  		<th>${number}</th>
	  		<th>${days_of_stay}</th>
	  		<th>${tariff}, ${byn}</th>
	  		<th>${cost}, ${byn}</th>
	  		</tr>
	  		
	  		<tr>
	  		<td>${place}</td>
	  		<td>${bookingRequest.numberOfPlaces}</td>
	  		<td>${days}</td>
	  		<td>${calc.getPlacesRate()}</td>
	  		<td>${calc.placesCost(bookingRequest.numberOfPlaces, days)}</td>
	  		</tr>
	  	<c:if test="${bookingRequest.numberOfLockers != '0'}">	
	  		<tr>
	  		<td>${locker}</td>
	  		<td>${bookingRequest.numberOfLockers}</td>
	  		<td>${calc.getNumberDays(bookingRequest.startDate, bookingRequest.endDate)}</td>
	  		<td>${calc.getLockersRate()}</td>
	  		<td>${calc.lockersCost(bookingRequest.numberOfLockers, days)}</td>
	  		</tr>
		</c:if>
	  	</table>	
	  	<div id="total_in_bill">${total}: ${bill.totalAmount}, ${byn}</div>	
  	</div>
  	
  		<div id="bill_button">
  		<button>${print}</button>
  		<c:if test="${bill.status.getTitle() eq '0'}">
  		<form method="post" action="Controller?command=UPDATE_PAYMENT&bill_id=${bill.id}">	
	  		<button type="submit">${confirm_payment}</button>
	  	</form>
	  	</c:if>
	  	</div>

</body>
</html>