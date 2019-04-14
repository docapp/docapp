<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available time solts</title>
</head>
<body>

<h2>Horas disponibles ${pat_dni} </h2>

	<form action="CreateAppointmentServlet" method="post">
		
		Start_time: <select name="start_time">
 				<option value="" disabled selected>Elija una hora</option>
			
 				<c:forEach items="${available}" var="time" varStatus="loop">
   				<option value="${time}">
     					${time}
   				</option>
 				</c:forEach>
			</select>
		
		<input type="hidden" name="doc_dni" value="${doc.dni}" />
		<input type="hidden" name="pat_dni" value="${pat_dni}" />
		<input type="hidden" name="date" value="${date}" />
	
		<p><button type="submit">Pedir cita</button></p>
	</form>
	

</body>
</html>