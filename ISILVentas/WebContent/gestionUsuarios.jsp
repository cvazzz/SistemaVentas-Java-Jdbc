<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ISIL Ventas - Gestión Usuarios</title>
</head>
<body>
	<h1>Gestión Usuarios</h1>
	<br>
	<br>
	<form action="usuario" method="GET">
		<input type="hidden" name="opcionGet" value="buscarUsuarios">
		Correo: <input type="text" name="correo">
		<button>Buscar</button>
	</form>
	<br><br>
	<table>
		<thead>
			<tr>
				<td>Código</td>
				<td>Correo</td>
				<td>Estado</td>
				<td>Perfil</td>
				<td>Acciones</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="usuario" items="${listaUsuarios}">
				<tr>
					<td>${usuario.codigo}</td>
					<td>${usuario.correo}</td>
					<td>${usuario.estado}</td>
					<td>${usuario.perfil}</td>
					<td><a href="usuario?opcionGet=editarUsuario&codigo=${usuario.codigo}">Editar</a>
						<a href="usuario?opcionGet=eliminarUsuario&codigo=${usuario.codigo}">Eliminar</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<form action="usuario" method="POST">
		<input type="hidden" name="opcionPost" value="mostrarNuevoUsuario">
		<button>Nuevo</button>
	</form>
</body>
</html>