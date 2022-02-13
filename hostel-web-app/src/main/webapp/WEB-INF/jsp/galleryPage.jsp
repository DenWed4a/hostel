<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/galleryStyle.css" type="text/css">
</head>
<body>
	<jsp:include page="blank/Header.jsp"></jsp:include>
	
	<div class="images">
	
	<div class="image-row">
		<img  src="${pageContext.request.contextPath}/images/1aaa.jpg" alt="photo"/>
		<img  src="${pageContext.request.contextPath}/images/3.jpg" alt="photo"/>
		</div>
		
		
		<div class="image-row">
		<img  src="${pageContext.request.contextPath}/images/4.jpg" alt="photo"/>
		<img  src="${pageContext.request.contextPath}/images/5.jpg" alt="photo"/></div>
		
		<div class="image-row">
		<img  src="${pageContext.request.contextPath}/images/9as.jpg" alt="photo"/>
		<img  src="${pageContext.request.contextPath}/images/11as.jpg" alt="photo"/></div>
	
	</div>

</body>
</html>