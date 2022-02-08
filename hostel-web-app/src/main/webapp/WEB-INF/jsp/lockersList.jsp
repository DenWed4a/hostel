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
	<fmt:message bundle="${loc}" key="local.locker.type" var="type"/>
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
					<th>${type}</th>
					<th>${status}</th>						
				</tr>
				<c:forEach items = "${lockerList}" var="i">
					<tr>
						<td>${i.id}</td>
						<td>${i.imagePath}</td>
						<td>${i.size}</td>
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
						<th>${type}</th>
						<th>${status}</th>
						<th></th>						
					</tr>	
					<tr>
					<td><input type="file" name="photo"/></td>
						<td><select name="type">
							<option value="SMALL">SMALL</option>
							<option value="NORMAL">NORMAL</option>
							<option value="BIG">BIG</option>							
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
	
	
</body>
</html>