<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin View</title>
</head>
<body>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>
<hr>
	<h2>Vista de administrador</h2>
	<shiro:lacksRole name="admin">
	No tienes permiso para ver el contenido de esta página
</shiro:lacksRole>
	<shiro:hasRole name="admin">
		<h3>Crear doctor nuevo</h3>
		<form action="CreateDoctorServlet" method="post">
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Apellidos: <input type="text" name="surname" />
			</p>
			<p>
				Especialidad: <input type="text" name="specialty" />
			</p>
			<p>
				Password: <input type="password" name="password" />
			</p>
			<p>
				<button type="submit">Crear doctor</button>
			</p>
		</form>
		<h3>Listado de doctores</h3>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Citas</th>
			</tr>
			<c:forEach items="${doctor_list}" var="doc">
				<tr>
					<td>${doc.name }</td>
					<td>${doc.surname }</td>
					<td>${fn:length(doc.appointments) }</td>
				</tr>
			</c:forEach>
		</table>
		<h3>Listado de citas</h3>
		<table border="1">
			<tr>
				<th>Día</th>
				<th>Hora</th>
				
			</tr>
			<c:forEach items="${appointment_list}" var="appi">
				<tr>
					<td>${appi.date }</td>
					<td>${appi.start_time }</td>
					
				</tr>
			</c:forEach>
		</table>
	</shiro:hasRole>
</body>
</html>