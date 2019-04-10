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

<h2>Doctores de ${spec}</h2>

		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Disponibilidad</th>
			</tr>
			<c:forEach items="${doctor_list}" var="doc">
				<tr>
					<td>${doc.name }</td>
					<td>${doc.surname }</td>
					<td>
						<form action="DateServlet" method="post">
							<input type="hidden" name="doc_dni" value="${doc.dni}" />
							<button type="submit">Ver disponibilidad</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		

</body>
</html>