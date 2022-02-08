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

	<fmt:message bundle="${loc}" key="local.photo" var="photo"/>
	<fmt:message bundle="${loc}" key="local.room" var="room"/>
	<fmt:message bundle="${loc}" key="local.bed.place.tier" var="tier"/>
	<fmt:message bundle="${loc}" key="local.status" var="status"/>
	
</head>
<body>
	<jsp:include page="blank/Header.jsp"></jsp:include>
	<c:if test="${empty button}">
		<div class="table">
			<table class="user_list_table">
				<tr class="sticky">
					<th>Id</th>
					<th>${photo}</th>
					<th>${room}</th>
					<th>${tier}</th>
					<th>${status}</th>						
				</tr>
				<c:forEach items = "${placesList}" var="i">
					<tr>
						<td>${i.id}</td>
						<td>${i.imagePath}</td>
						<td>${i.room}</td>
						<td>${i.tier}</td>
						<td>${i.status}</td>										
					</tr>
				</c:forEach>
				
			</table>	
		</div>
	</c:if>
	<c:if test="${button eq 'new'}">
		<div class="table">		
			<form method="post" enctype="multipart/form-data" action="Controller?command=ADD_NEW_PLACE">		
				<table class="user_list_table">
					<tr class="sticky">				
						<th>${photo}</th>
						<th>${room}</th>
						<th>${tier}</th>
						<th>${status}</th>
						<th></th>						
					</tr>	
					<tr>
					<td><input type="file" name="photo"/></td>
						<td><select name="room">
							<option value="101">101</option>
							<option value="102">102</option>
							<option value="103">103</option>
							<option value="104">104</option>
							<option value="201">201</option>
							<option value="202">202</option>
							<option value="203">203</option>
							<option value="204">204</option>
						</select></td>
						<td><select name="tier">
							<option value="1">1</option>
							<option value="2">2</option>		
						</select></td>
						<td><select>
							<option value="0">active</option>
							<option value="1">frozen</option>			
						</select></td>
						<td><input type="submit" value="save"></td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>
	<script src="js/script.js"></script>
</body>
</html>