<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Doctor View</title>
</head>
<body>
<br>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>
<hr>

<h2>Vista de doctor</h2>


<h2>Sus citas programadas</h2>

<table border="1">
	<tr>
		<th>Hora</th>
		<th>DNI</th>
		
	</tr>
		<c:forEach items="${appointments}" var="appi">

	<tr>
		<td>${appi.date}</td>
		<td>${appi.start_time}</td>
	</tr>
		</c:forEach>
	
</table>
<br>

<%-- <table border="1">
	<tr>
		<th>Título</th>
		<th>Email alumno</th>
		<th>Nombre del alumno</th>
		<th>Email tutor</th>
		<th>Nombre del tutor</th>
		<th>Estado del TFG</th>
		<th>Memoria</th>
		<th>Acción requerida</th>
	</tr>
	<c:forEach items="${tfgs}" var="tfgi">
		<tr>
			<td>${tfgi.title }</td>
			<td>${tfgi.email }</td>
			<td>${tfgi.name }</td>
			<td>${tfgi.advisor.email }</td>
			<td>${tfgi.advisor.name }</td>
			<td>${tfgi.status }</td>
			<td><c:if test="${tfgi.status > 3}">
				<form action="ServeFileServlet">
					<input type="hidden" name="email" value="${tfgi.email}" />
					<button type="submit">Descargar</button>
				</form>
			</c:if></td>
			<td><c:if test="${tfgi.status == 1}">
				<form action="Form2ProfessorServlet" method="post">
					<input type="hidden" name="email" value="${tfgi.email}" />
					<button type="submit">Aceptar tfg</button>
				</form>
			</c:if></td>
		</tr>
	</c:forEach>
</table> --%>
</body>
</html>