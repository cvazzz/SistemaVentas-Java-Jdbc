<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Nuevo Usuario</h1>
	<br>
	<br>
	<form action="usuario" method="POST">
		<input type="hidden" name="opcionPost" value="grabarUsuario">
		Correo: <input type="email" name="correo"><br><br>
		Password: <input type="password" name="password"><br><br>
		Perfil: <select name="perfil">
					<option value="Administrador del Sistema">Administrador del Sistema</option>
					<option value="Jefe de Ventas">Jefe de Ventas</option>
					<option value="Responsable de Ventas">Responsable de Ventas</option>
					<option value="Cliente">Cliente</option>
				</select><br><br>
		<button>Grabar</button>
	</form>
</body>
</html>