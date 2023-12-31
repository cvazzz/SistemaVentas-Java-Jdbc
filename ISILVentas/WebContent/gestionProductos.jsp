<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ISIL Ventas - Gestión Productos</title>
</head>
<body>
	<h1>Gestión Productos</h1>
	<br>
	<br>
	<form action="producto" method="GET">
		<input type="hidden" name="opcionGet" value="buscarProductos">
		Tipo de Producto: <select name="tipoProducto">
							<option value="ceramico">Ceramico</option>
							<option value="piedra">Piedra Tallada</option>
						  </select>
		<button>Buscar</button>
	</form>
	<br><br>
	<table>
		<thead>
			<tr>
				<td>Código</td>
				<td>Nombre</td>
				<td>Tipo Producto</td>
				<td>Precio</td>
				<td>Stock</td>
				<td>Acciones</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="producto" items="${listaProductos}">
				<tr>
					<td>${producto.codigo}</td>
					<td>${producto.nombre}</td>
					<td>${producto.tipoProducto}</td>
					<td>${producto.precio}</td>
					<td>${producto.stock}</td>
					<td><a href="producto?opcionGet=editarProducto&codigo=${producto.codigo}">Editar</a>
						<a href="producto?opcionGet=eliminarProducto&codigo=${producto.codigo}">Eliminar</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<form action="producto" method="POST">
		<input type="hidden" name="opcionPost" value="mostrarNuevoProducto">
		<button>Nuevo</button>
	</form>
</body>
</html>