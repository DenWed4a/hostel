<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="blank/Header.jsp"></jsp:include>
	<c:set var="bill" scope="page" value="${bill}"/>
	
  	<div class="bill">
	  	<h2>счет № id</h2>
	  	
	  	<div>Гость: смирнов</div>
	  	<div id="dates">
	  	<div>дата въезда-</div>   
	  	<div>дата выезда</div>
	  	</div>
	  	<table border=1 id="bill_table">
	  		<tr>
	  		
	  		<th>наименование</th>
	  		<th>колличество</th>
	  		<th>дней проживания</th>
	  		<th>тариф, руб.</th>
	  		<th>стоимость, руб.</th>
	  		</tr>
	  		
	  		<tr>
	  		<td>спальное место</td>
	  		<td>2</td>
	  		<td>10</td>
	  		<td>4</td>
	  		<td>500</td>
	  		</tr>
	  		
	  		<tr>
	  		<td>место для хранения</td>
	  		<td>5</td>
	  		<td>123</td>
	  		<td>123</td>
	  		<td>345</td>
	  		</tr>
	
	  	</table>	
	  	<div id="total_in_bill">итого:</div>  	
  	</div>
  	
  		<div id="bill_button"><button>распечатать</button>
	  	<button>подтвердить оплату</button>
	  	</div>
</body>
</html>