<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient view</title>
</head>

<body>
<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>
<br>

	<h3>Listado de especialidades</h3>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Doctores</th>
				
			</tr>
			<c:forEach items="${specialties}" var="speci">
				<tr>
					<td>${speci.id }</td>
					<td><a href="SpecDocsServlet?spec=${speci.id}&pat_dni=${pat_dni}">${speci.name }</a></td>
					<td>${speci.description }</td>
					<td>${fn:length(speci.doctors) }</td>
				</tr>
			</c:forEach>
		</table>

	<h2>Tus citas</h2>

	<table border="1">
		<tr>
			<th>Día</th>
			<th>Hora</th>
			
		</tr>
		
		<c:forEach items="${appointments}" var="appi">
			<tr>
				<td>${appi.date}</td>
				<c:if test = "${appi.start_time == 0}">
		         <td>9:00-9:30</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 1}">
		         <td>9:30-10:00</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 2}">
		         <td>10:00-10:30</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 3}">
		         <td>10:30-11:00</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 4}">
		         <td>11:00-11:30</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 5}">
		         <td>11:30-12:00</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 6}">
		         <td>12:00-12:30</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 7}">
		         <td>12:30-13:00</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 8}">
		         <td>13:00-13:30</td>
		        </c:if>
		        <c:if test = "${appi.start_time == 8}">
		         <td>13:30-14:00</td>
		        </c:if>
		        
			</tr>
		</c:forEach>	
	</table>
<br>

</body>
</html>