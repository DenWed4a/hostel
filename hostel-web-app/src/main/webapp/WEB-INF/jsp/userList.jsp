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
	<fmt:message bundle="${loc}" key="local.guests" var="guests"/>
	<fmt:message bundle="${loc}" key="local.employees" var="employees"/>
	
	<fmt:message bundle="${loc}" key="local.photo" var="photo"/>
	<fmt:message bundle="${loc}" key="local.user.info.name" var="name"/>
	<fmt:message bundle="${loc}" key="local.user.info.surname" var="surname"/>
	<fmt:message bundle="${loc}" key="local.user.info.phone.number" var="phoneNumber"/>
	<fmt:message bundle="${loc}" key="local.user.info.email" var="email"/>
	<fmt:message bundle="${loc}" key="local.role" var="role"/>
	<fmt:message bundle="${loc}" key="local.residents" var="residents"/>
	<fmt:message bundle="${loc}" key="local.button.all" var="all"/>
	
	
	
	

	<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>

</head>
<body>

	<c:set var="user_list" scope="page" value="${users}"/>
	<c:set var="user_id_id" scope="page" value="${user_id}"/>
	
	
		<div class="header_top">
	
		<div class="buttons_home_back">	
			<a href="Controller?command=GO_TO_INDEX_PAGE"><i class="fas fa-home"></i></a>
			<a href="Controller?command=GO_TO_INDEX_PAGE"><i class="fas fa-arrow-alt-circle-left"></i></a>
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
			<a  href="#booking_hidden_buttons">${guests}</a>
				<div id="booking_hidden_buttons">
					<a href="Controller?command=GO_TO_USER_LIST&table_name=clients">${residents}</a>
					<a href="Controller?command=GO_TO_USER_LIST&table_name=clients">${all}</a>			
				</div>
			
				
					<a href="Controller?command=GO_TO_USER_LIST&table_name=employees">${employees}</a>
				
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
	
	
	<div class="table">
				<table class="user_list_table">
					<tr class="sticky">
						<th>Id</th>
						<th>${photo}</th>
						<th>${name}</th>
						<th>${surname}</th>
						<th>${phoneNumber}</th>
						<th>${email}</th>
						<th>${role}</th>
						
					</tr>
					<c:forEach  items = "${user_list}" var="i">
						<c:if test="${i.role eq 'CLIENT' and table_name eq 'clients'}">		
							<tr>
								<td>${i.id}</td>
								<td><img class="user_photo" src="${pageContext.request.contextPath}${i.detail.image}" alt=" "></td>
								<td>${i.detail.name}</td>
								<td>${i.detail.surname}</td>
								<td>${i.detail.phoneNumber}</td>
								<td>${i.detail.email}</td>
								<td>${i.role}</td>						
							</tr>
						</c:if>
						
						<c:if test="${table_name eq 'employees' and (i.role eq 'ADMINISTRATOR' or i.role eq 'MODERATOR')}">
							<tr>
								<td>${i.id}</td>
								<td><img class="user_photo" src="${pageContext.request.contextPath}${i.detail.image}" alt=" "></td>
								<td>${i.detail.name}</td>
								<td>${i.detail.surname}</td>
								<td>${i.detail.phoneNumber}</td>
								<td>${i.detail.email}</td>
								<td>${i.role}</td>						
							</tr>
						</c:if>			
					
						
					</c:forEach>
				
				</table>	
			</div>
			
		<c:if test="${user_id_id eq 3}">
			<div class="table">
				<table class="user_list_table">
					<tr class="sticky">
						<th>Id</th>
						<th>${photo}</th>
						<th>${name}</th>
						<th>${surname}</th>
						<th>${phoneNumber}</th>
						<th>${email}</th>
						<th>${role}</th>
						
					</tr>
					<c:forEach  items = "${user_list}" var="i">	
												
							<tr>
								<td>${i.id}</td>
								<td><img class="user_photo" src="${pageContext.request.contextPath}${i.detail.image}" alt=" "></td>
								<td>${i.detail.name}</td>
								<td>${i.detail.surname}</td>
								<td>${i.detail.phoneNumber}</td>
								<td>${i.detail.email}</td>
								<td>${i.role}</td>						
							</tr>
												
					</c:forEach>
				
				</table>	
			</div>
		</c:if>
			


</body>
</html>