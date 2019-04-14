<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Date view</title>
</head>
<body>

<h2>Elige un día para ver la disponibilidad ${pat_dni}</h2>
		<form action="DispDocServlet" method="post">
			<p>
				Fecha: <input type="date" name="date" />
			</p>
			<input type="hidden" name="doc_dni" value="${doc.dni}" />
			<input type="hidden" name="pat_dni" value="${pat_dni}" />
			
			<p>
				<button type="submit">Ver disponibilidad</button>
			</p>
		</form>
		
		

</body>
</html>