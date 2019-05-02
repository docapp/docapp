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
		<th>Fecha</th>
		<th>Hora</th>
		<th>Presencia</th>
		<th>Paciente</th>
		
		
	</tr>
		<c:forEach items="${anotherList}" var="obj">

	<tr>
		<td>${obj.date}</td>
		<td>${obj.start_time}</td>
		<td>${obj.presence}</td>
		<td>${obj.patient.surname} ${obj.patient.name}</td>
	
			
	</tr>
		</c:forEach>
	
</table>
<br>

</body>
</html>