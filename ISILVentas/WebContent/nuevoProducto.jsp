<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Nuevo Producto</h1>
	<br>
	<br>
	<form action="producto" method="POST">
		<input type="hidden" name="opcionPost" value="grabarProducto">
		Nombre: <input type="text" name="nombre"><br><br>
		Tipo de Producto: <select name="tipoProducto">
					<option value="ceramico">Cerámico</option>
					<option value="piedra">Piedra Tallada</option>
				</select><br><br>
		Precio: <input type="text" name="precio"><br><br>
		Stock: <input type="text" name="stock"><br><br>
		<button>Grabar</button>
	</form>
</body>
</html>