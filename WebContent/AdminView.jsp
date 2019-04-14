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
		<c:if test="${is_pas == true}">
			<h3>Listado de especialidades</h3>
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Descripción</th>
						<th>Doctores</th>
						
					</tr>
					<c:forEach items="${specialty_list}" var="speci">
						<tr>
							<td>${speci.id }</td>
							<td><a href="SpecDocsServlet?spec=${speci.id}&pat_dni=${pat_dni}">${speci.name }</a></td>
							<td>${speci.description }</td>
							<td>${fn:length(speci.doctors) }</td>
						</tr>
					</c:forEach>
				</table>		
		</c:if>  
		<c:if test="${is_pas == false}">
		<h3>Crear perfil de Personal Administrativo</h3>
		<form action="CreateAdminServlet" method="post">
			<p>
				DNI: <input type="text" name="dni" />
			</p>
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Apellidos: <input type="text" name="surname" />
			</p>
			<p>
				Password: <input type="password" name="password" />
			</p>
			<p>
				<button type="submit">Crear admin</button>
			</p>
		</form>
		<h3>Listado de Personal Administrativo</h3>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
			</tr>
			<c:forEach items="${admin_list}" var="admini">
				<tr>
					<td>${admini.name }</td>
					<td>${admini.surname }</td>
				</tr>
			</c:forEach>
		</table>
		<h3>Crear doctor nuevo</h3>
		<form action="CreateDoctorServlet" method="post">
			<p>
				DNI: <input type="text" name="dni" />
			</p>
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
		<h3>Registrar nuevo paciente</h3>
		<form action="CreatePatientServlet" method="post">
			<p>
				DNI: <input type="text" name="dni" />
			</p>
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Apellidos: <input type="text" name="surname" />
			</p>
			<p>
				Fecha de nacimiento: <input type="date" name="birth" />
			</p>
			<p>
				Password: <input type="password" name="password" />
			</p>
			<p>
				<button type="submit">Registrar paciente</button>
			</p>
		</form>
		<h3>Listado de pacientes</h3>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Fecha de nacimiento</th>
				<th>Citas</th>
				
			</tr>
			<c:forEach items="${patient_list}" var="pati">
				<tr>
					<td>${pati.name }</td>
					<td>${pati.surname }</td>
					<td>${pati.birth }</td>
					<td>${fn:length(pati.appointments) }</td>
				</tr>
			</c:forEach>
		</table>
		<h3>Registrar nueva especialidad</h3>
		<form action="CreateSpecialtyServlet" method="post">
			<p>
				Identificador: <input type="text" name="id" />
			</p>
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Descripción: <input type="text" name="description" />
			</p>
			
			<p>
				<button type="submit">Registrar especiald</button>
			</p>
		</form>
		<h3>Listado de especialidades</h3>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Doctores</th>
				
			</tr>
			<c:forEach items="${specialty_list}" var="speci">
				<tr>
					<td>${speci.id }</td>
					<td>${speci.name }</td>
					<td>${speci.description }</td>
					<td>${fn:length(speci.doctors) }</td>
				</tr>
			</c:forEach>
		</table>		
		</c:if>  
		
		
	</shiro:hasRole>
</body>
</html>