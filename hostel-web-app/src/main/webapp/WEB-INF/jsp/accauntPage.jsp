<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>
</head>

<body>
	
	<c:set var="user" scope="page" value="${user}"></c:set>
	
	<c:set var="detail" scope="page" value="${detail}"></c:set>
	<jsp:include page="blank/Header.jsp"></jsp:include>
		
	
		<c:if test="${button != 'edit'}">
			<div class="wrapper">	
			<div id="photo_edit">
			
				<div class = photo_button>
				<img id="personal-photo" src="${pageContext.request.contextPath}${detail.image}" alt="photo"/>
							<form method="post" enctype="multipart/form-data" action="Controller?command=SAVE_USER_IMAGE">
								<div>
									<label onclick="showButton()" for="image_uploads"><i class="fas fa-edit"></i></label>
									<input type="file" id = "image_uploads" name="photo"/>
								</div>
								<div id="submit_hidden">
									<input type="submit" value="ok"/> 
								</div>	
							</form>															
				</div>
				<a id="button_edit-personal-info" href="Controller?command=GO_TO_ACCAUNT_PAGE&button=edit">edit</a>
			</div>		
			<div id="personal-info">		
				<div class="doNotShow">
					<span>Id</span>
					<span>${user.id}</span>
				</div>			
				<div class="flex-row">
					<span>Login:</span>
					<span>${user.login}</span>
				</div>
				<div class="flex-row">
					<span>Name:</span>
					<span>${detail.name}</span>
				</div>
				<div class="flex-row">
					<span>Surname:</span>
					<span>${detail.surname}</span>
				</div>
				<div class="flex-row">
					<span>Email:</span>
					<span>${detail.email}</span>
				</div>
				<div class="flex-row">
					<span>Phone number:</span>
					<span>${detail.phoneNumber}</span>
				</div>
				<div class="flex-row">
					<span>Passport number:</span>
					<span>${detail.passportNumber}</span>
				</div>
				<div class="flex-row">
					<span>Nationality:</span>
					<span>${detail.nationality}</span>
				</div>
				<div class="flex-row">
					<span>Date of birth:</span>
					<span>${detail.dateOfBirth}</span>
				</div>
				<div class="flex-row">
					<span>Passport date of issue:</span>
					<span>${detail.passportDateOfIssue}</span>
				</div>
				<div class="flex-row">
					<span>Passport date of expire:</span>
					<span>${detail.passportDateOfExpire}</span>
				</div>
				<div class="flex-row">
					<span>Address:</span>
					<span>${detail.address}</span>
				</div>			
			</div>
			</div>
		</c:if>
		
		<c:if test="${button eq 'edit'}">
			
		<form method="post" action="Controller?command=SAVE_ACCAUNT_CHANGES&p_page=accauntPage">
			<div class="wrapper">
			<div id="photo_edit">
				<img id="personal-photo" src="${pageContext.request.contextPath}${detail.image}" alt="photo"/>
				<input id="button_edit-personal-info" type="submit" value="save">
			</div>
			
					
			<div id="personal-info">
						
				<div class="doNotShow">
				
					<span>Id</span>
					<span><input  type = "text" name = "id" value = "${user.id}"></span>
					<span>Role</span>
					<input  type = "text" name = "role" value = "${user.role}"/>
					<input type = "text" name = "image" value="${detail.image}"/>
					
				</div>			
				<div class="flex-row">
					<span>Login:</span>
					<span><input readonly type = "text" name = "login" value = "${user.login}"></span>
				</div>
				<div class="flex-row">						
					<span>Name:</span>
					<span><input  type = "text" name = "name" value = "${detail.name}"></span>				
				</div>
				<div class="flex-row">
					<span>Surname:</span>
					<span><input  type = "text" name = "surname" value = "${detail.surname}"></span>
				</div>
				<div class="flex-row">
					<span>Email:</span>
					<span><input type = "text" name = "email" value = "${detail.email}"></span>
				</div>
				<div class="flex-row">
					<span>Phone number:</span>
					<span><input type = "text" name = "phoneNumber" value = "${detail.phoneNumber}"></span>
				</div>
				<div class="flex-row">
					<span>Passport number:</span>
					<span><input readonly type = "text" name = "passportNumber" value = "${detail.passportNumber}"></span>
				</div>
				<div class="flex-row">
					<span>Nationality:</span>
					<span><input type = "text" name = "nationality" value = "${detail.nationality}"></span>
				</div>
				<div class="flex-row">
					<span>Date of birth:</span>
					<span><input readonly type = "text" name = "dateOfBirth" value = "${detail.dateOfBirth}"></span>
				</div>
				<div class="flex-row">
					<span>Passport date of issue:</span>
					<span><input readonly type = "text" name = "issue" value = "${detail.passportDateOfIssue}"></span>
				</div>
				<div class="flex-row">
					<span>Passport date of expire:</span>
					<span><input readonly type = "text" name = "expire" value = "${detail.passportDateOfExpire}"></span>
				</div>
				<div class="flex-row">
					<span>Address:</span>
					<span><input type = "text" name = "address" value = "${detail.address}"></span>
				</div>	
				
					
			</div>
			
			</div>
			</form>	
		
		
		</c:if>
		
		
		
		
		
		
		
		
					  
	<script src="js/script.js"></script>
</body>
</html>