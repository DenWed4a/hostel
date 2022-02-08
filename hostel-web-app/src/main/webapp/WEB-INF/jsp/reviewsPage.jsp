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
	
	<c:forEach items="${reviews}" var="i">
		<div class="review">				
			<c:forEach var="users_reviews" items="${i.value}">
				<c:set var="user" value="${users_reviews.key}"/>
				<c:set var="review" value="${users_reviews.value}"/>
					<div class="user_review">
						<div class="image"><img id="personal-photo" src="${pageContext.request.contextPath}${user.detail.image}" alt="photo"/></div>
						<div class="text">${review.text}</div>
					</div>
					<div class="moderator_answer">
						<div class="image"></div>
						<div class="text"></div>
					</div>											
			</c:forEach>
		</div>	
	</c:forEach>
	

</body>
</html>