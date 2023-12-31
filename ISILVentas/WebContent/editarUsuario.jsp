<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Editar Usuario</h1>
	<br>
	<br>
	<form action="usuario" method="POST">
		<input type="hidden" name="opcionPost" value="actualizarUsuario">
		<input type="hidden" name="codigo" value="${objUsuario.codigo}">
		Correo: <input type="email" name="correo" value="${objUsuario.correo}"><br><br>
		Password: <input type="password" name="password" value="${objUsuario.password}"><br><br>
		Perfil: <select name="perfil">
					<c:if test= "${objUsuario.perfil=='Administrador del Sistema'}">
						<option value="Administrador del Sistema" selected>Administrador del Sistema</option>
						<option value="Jefe de Ventas">Jefe de Ventas</option>
						<option value="Responsable de Ventas">Responsable de Ventas</option>
						<option value="Cliente">Cliente</option>
					</c:if>
					<c:if test= "${objUsuario.perfil=='Jefe de Ventas'}">
						<option value="Administrador del Sistema">Administrador del Sistema</option>
						<option value="Jefe de Ventas" selected>Jefe de Ventas</option>
						<option value="Responsable de Ventas">Responsable de Ventas</option>
						<option value="Cliente">Cliente</option>
					</c:if>
					<c:if test= "${objUsuario.perfil=='Responsable de Ventas'}">
						<option value="Administrador del Sistema">Administrador del Sistema</option>
						<option value="Jefe de Ventas">Jefe de Ventas</option>
						<option value="Responsable de Ventas" selected>Responsable de Ventas</option>
						<option value="Cliente">Cliente</option>
					</c:if>
					<c:if test= "${objUsuario.perfil=='Cliente'}">
						<option value="Administrador del Sistema">Administrador del Sistema</option>
						<option value="Jefe de Ventas">Jefe de Ventas</option>
						<option value="Responsable de Ventas">Responsable de Ventas</option>
						<option value="Cliente" selected>Cliente</option>
					</c:if>
				</select><br><br>
		Estado: <select name="estado">
					<c:if test= "${objUsuario.estado=='Activo'}">
						<option value="Activo" selected>Activo</option>
						<option value="Inactivo">Inactivo</option>
					</c:if>
					<c:if test= "${objUsuario.estado=='Inactivo'}">
						<option value="Activo">Activo</option>
						<option value="Inactivo" selected>Inactivo</option>
					</c:if>
				</select><br><br>
		<button>Grabar</button>
	</form>
</body>
</html>