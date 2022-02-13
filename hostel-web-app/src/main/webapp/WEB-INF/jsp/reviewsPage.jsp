<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reviewsStyle.css" type="text/css">
<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="blank/Header.jsp"></jsp:include>
	
	<c:forEach items="${reviews}" var="i">
		<div class="review">				
				<c:set var="user" value="${i.value}"/>
				<c:set var="review" value="${i.key}"/>
					<div class="user_review">
						<div class="image"><img id="personal-image" src="${pageContext.request.contextPath}${user.detail.image}" alt="photo"/>
						${user.detail.name}<br>
						${user.detail.surname}
						</div>
						<div style="display: flex; flex-direction: column; justify-content: space-between;">
							<div>
								<div style="display: flex; justify-content: flex-start; gap: 10px;">
									<h3>${review.topic}</h3>
									<h3>${review.mark}</h3>
									<h4 style="align-self: flex-end">${review.date}</h4>
								</div>
								<div class="text">${review.text}</div>
							</div>
							<div><c:if test="${user.id eq sessionScope.userId}"><a href="Controller?command=GO_TO_REVIEW_FORM&review_id=${review.id}"><i class="fas fa-pen"></i></a></c:if></div>
							<div><c:if test="${user.id eq sessionScope.userId}"><a href="Controller?command=DELETE_REVIEW&review_id=${review.id}"><i class="fas fa-trash-alt"></i></a></c:if></div>
							<c:if test="${sessionScope.role eq 'Administrator'}"><div><a href="Controller?command=GO_TO_REVIEW_FORM&review_id=${review.id}&button=answer">answer</a></div></c:if>
						</div>
					</div>
					
					
					
					<c:if test="${not empty review.responceToReview}">				
						<div class="administrator_answer">
									<c:forEach items="${admins}" var="j">
										<c:if test="${review.moderatorId eq j.id}">
											<c:set var="admin" value="${j}"/>
										</c:if>
									</c:forEach>
							<div class="image_admin"><img id="admin-image" src="${pageContext.request.contextPath}${admin.detail.image}" alt="photo"/>
								<label>${admin.role}</label><br>
									   ${admin.detail.name}
							</div>
							<div style="display: flex; flex-direction: column; justify-content: space-between;">
								<div class="text">${review.responceToReview}</div>
								<div><c:if test="${admin.id eq sessionScope.userId}"><a href="Controller?command=GO_TO_REVIEW_FORM&review_id=${review.id}&button=answer"><i class="fas fa-pen"></i></a></c:if></div>
							</div>
						</div>	
					</c:if>													
		</div>	
	</c:forEach>
	

</body>
</html>