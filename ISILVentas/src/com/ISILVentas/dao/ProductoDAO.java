package com.ISILVentas.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ISILVentas.model.ProductoVenta;

public class ProductoDAO {
	private String url; /*Este atributo guarda la ruta donde se encuentra la base de datos*/
	private Connection conexion; /*Este atribuo contiene la conexion que vamos a establecer*/

	public ProductoDAO() {
		/*Vamos a definir todo lo necesario para conectarnos con la base de datos*/
		/*1. Definimos la ruta donde esta la base de datos*/
		/*La ruta de la BD es: jdbc:sqlserver://servidorBD:1433;databaseName=instanciaBD;user=usuarioBD;password=passwordBD*/
		this.url = "jdbc:sqlserver://localhost:1433;databaseName=miSistemaBD;user=sa;password=sa";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.conexion = DriverManager.getConnection(this.url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public List<ProductoVenta> buscarProductos(String tipoProducto){
		List<ProductoVenta> listaProductos = new ArrayList<ProductoVenta>();
		String query = "select * from ProductoVenta where tipoProducto = ?";
		PreparedStatement pstmt;
		try {
			pstmt = this.conexion.prepareStatement(query);
			pstmt.setString(1, tipoProducto);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//Cada vez que entre a este while se trata de un usuario
				//Voy a crear un objeto Usuario.
				int codigo = rs.getInt(1);
				String tipoProductoBD = rs.getString(2);
				float precio = rs.getFloat(3);
				int stock = rs.getInt(4);
				String nombre = rs.getString(5);
				ProductoVenta objProducto = new ProductoVenta(codigo,tipoProductoBD, precio, stock, nombre);
				listaProductos.add(objProducto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProductos;
	}
	
	public ProductoVenta buscarProductoxCodigo(int codigo) {
		ProductoVenta objProductoVenta = new ProductoVenta();
		CallableStatement cs;
		try {
			cs = this.conexion.prepareCall("{CALL SP_buscarProductoxCodigo(?)}");
			cs.setInt(1, codigo);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				//Cada vez que entre a este while se trata de un usuario
				//Voy a crear un objeto Usuario.
				int codigoProducto = rs.getInt(1);
				String tipoProductoBD = rs.getString(2);
				float precio = rs.getFloat(3);
				int stock = rs.getInt(4);
				String nombre = rs.getString(5);
				objProductoVenta.setCodigo(codigoProducto);
				objProductoVenta.setNombre(nombre);
				objProductoVenta.setPrecio(precio);
				objProductoVenta.setStock(stock);
				objProductoVenta.setTipoProducto(tipoProductoBD);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objProductoVenta;
	}
	
	public List<ProductoVenta> buscarProductos_SP(String tipoProducto){
		List<ProductoVenta> listaProductos = new ArrayList<ProductoVenta>();
		CallableStatement cs;
		try {
			cs = this.conexion.prepareCall("{CALL SP_buscarProductoxTipoProducto(?)}");
			cs.setString(1, tipoProducto);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				//Cada vez que entre a este while se trata de un usuario
				//Voy a crear un objeto Usuario.
				int codigo = rs.getInt(1);
				String tipoProductoBD = rs.getString(2);
				float precio = rs.getFloat(3);
				int stock = rs.getInt(4);
				String nombre = rs.getString(5);
				ProductoVenta objProducto = new ProductoVenta(codigo,tipoProductoBD, precio, stock, nombre);
				listaProductos.add(objProducto);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProductos;
	}
	
	public void registrarProducto_SP(String nombre, String tipoProducto, float precio, int stock) {
		CallableStatement cs;
		try {
			cs = this.conexion.prepareCall("{CALL SP_registrarProducto(?,?,?,?)}");
			cs.setString(1, nombre);
			cs.setString(2, tipoProducto);
			cs.setFloat(3, precio);
			cs.setInt(4, stock);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarProducto_SP(int codigo, String nombre, String tipoProducto, float precio, int stock) {
		CallableStatement cs;
		try {
			cs = this.conexion.prepareCall("{CALL SP_actualizarProducto(?,?,?,?,?)}");
			cs.setInt(1, codigo);
			cs.setString(2, nombre);
			cs.setString(3, tipoProducto);
			cs.setFloat(4, precio);
			cs.setInt(5, stock);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarProducto_SP(int codigo) {
		CallableStatement cs;
		try {
			cs = this.conexion.prepareCall("{CALL SP_eliminarProducto(?)}");
			cs.setInt(1, codigo);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
