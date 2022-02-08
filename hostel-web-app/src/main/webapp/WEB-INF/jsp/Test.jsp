<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<link rel="stylesheet" href="css/testStyle.css" type="text/css">
		<script src="https://kit.fontawesome.com/1aa736e549.js" crossorigin="anonymous"></script>
</head>
<body>
	
	
	<form method="post" enctype="multipart/form-data" action="Controller?command=GO_TO_TEST">
		<div>
			<label onclick="showButton()" for="image_uploads"><i class="fas fa-edit"></i></label>
			<input type="file" id = "image_uploads" name="photo"/>
		</div>
		<div id="submit_hidden">
			<input type="submit" value="ok"/> 
		</div>	
	</form>
	<script src="js/script.js"></script>
</body>
</html>