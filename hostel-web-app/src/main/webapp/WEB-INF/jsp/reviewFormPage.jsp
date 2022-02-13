<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reviewsStyle.css" type="text/css">
</head>
<body>
	<jsp:include page="blank/Header.jsp"></jsp:include>
		<div style="max-width: 55vw;" class="user_review">
			<form id="review_form" method="post" action="Controller?command=SAVE_REVIEW">
				<div class="topic">
					<span class="review_form_span">Введите оглавление:</span>
					<input style="width: 400px;" type="text" name="topic" value="${topic_edit}">
					
					<span>оценка</span>
					<select name="mark">
						<option selected="${mark_edit}">${mark_edit}</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</div>
				<div class="topic">
					<span class="review_form_span">Оснойвной текс:</span>
					<textarea style="width: 400px; height: 100px;" name="text">${text_edit}</textarea>
					
					<input type="hidden" name="confirmed_request_id" value="${bk_request_id}">
					<c:if test="${it_edit eq 'true'}">
						<input type="hidden" name="it_edt" value="true">
						<input type="hidden" name="id" value="${review_id}"> 
					</c:if>
					
					<c:if test="${empty button}"><input style="align-self: flex-end;" type="submit" value="save"></c:if>
				</div>
			</form>	
		</div>
	
		<c:if test="${button eq 'answer'}">
		<div style="max-width: 55vw;" class="user_review"> 
			<form method="post" action="Controller?command=SAVE_REVIEW&button=answer">
				<div class="topic">
					<span class="review_form_span">Ответ:</span>
					<textarea style="width: 400px; height: 100px;" name="answer">${admins_responce}</textarea>
					
					<input type="hidden" name="confirmed_request_id" value="${bk_request_id}">
					<c:if test="${it_edit eq 'true'}">						
						<input type="hidden" name="id" value="${review_id}">
						<input type="hidden" name="administrator_id" value="${sessionScope.userId}"/>
					</c:if>					
					<input style="align-self: flex-end;" type="submit" value="save">
				</div>
			</form>
			</div>
		</c:if>
	
</body>
</html>