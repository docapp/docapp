<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vista de paciente</title>
</head>

<body>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>
<br>

<h2>Tus citas</h2>

<table border="1">
	<tr>
		<th>Día</th>
		<th>Hora</th>
		
	</tr>
		<c:forEach items="${appointments}" var="appi">

	<tr>
		<td>${appi.date}</td>
		<td>${appi.start_time}</td>
	</tr>
		</c:forEach>
	
</table>
<br>

<%-- <c:if test="${tfg_alumno.status == 3}">
	<form action="Form4TFGServlet" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<button type="submit">Subir memoria</button>
	</form>
</c:if> --%>
</body>
</html>