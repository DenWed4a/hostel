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
  <c:set var="bill" scope="page" value="${bill}"/>
  	
  	<div>счет № id</div>
  	
  	<div>Гость: смирнов</div>
  	<div>дата въезда-</div>   
  	<div>дата выезда</div>
  	<table>
  		<tr>
  		
  		<th>наименование</th>
  		<th>колличество</th>
  		<th>дней проживания</th>
  		<th>тариф, руб.</th>
  		<th>стоимость, руб.</th>
  		<tr>
  		
  		<tr>
  		<td>спальное место</td>
  		<td>2</td>
  		<td>10</td>
  		<td>4</td>
  		<td>500</td>
  		<tr>
  		
  		<tr>
  		<td>место для хранения</td>
  		<td>5</td>
  		<td>123</td>
  		<td>123</td>
  		<td>345</td>
  		<tr>

  	</table>
  	
  	<button>распечатать</button>
  	<button>подтвердить оплату</button>

</body>
</html>