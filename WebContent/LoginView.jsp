<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login View</title>
</head>
<body>
	<shiro:guest>
		<h2>Login</h2>
		<form action="LoginServlet" method="post">
			Email: <input type="text" name="email" placeholder="Email" />
			Password: <input type="password" name="password"
				placeholder="Password" />
			<button type="submit">Login</button>
		</form>
		<h3>Pedir cita</h3>
		<form action="CreateAppointmentServlet" method="post">
			<p>
				Id: <input type="text" name="pat_id" />
			</p>
			<p>
				Fecha: <input type="date" name="date" />
			</p>
			<p>
				Hora de inicio: <input type="time" name="start_time" />
			</p>
			
			<p>
			Doctor: <select name="doc">
  				<option value="" disabled selected>Elija un doctor</option>
				<c:forEach items="${doctor_list}" var="doc">
    				<option value="${doc.id}">
      					${doc.name}-${doc.surname}-${doc.specialty}
    				</option>
  				</c:forEach>
				</select>
			</p>
			<p>
				<button type="submit">Pedir cita</button>
			</p>
		</form>
	</shiro:guest>

	<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
</shiro:user>
</body>
</html>