<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login here</title>
</head>
<body>
	<h2>welcome ${usern}</h2>
	<!-- <h2> welcome <%= request.getAttribute("usern") %></h2> -->
	
          <form action="/logout">
         <button type="submit">logout</button>
      </form>
</body>
</html>