package com.ISILVentas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ISILVentas.model.Usuario;

public class UsuarioDAO {
	private String url; /*Este atributo guarda la ruta donde se encuentra la base de datos*/
	private Connection conexion; /*Este atribuo contiene la conexion que vamos a establecer*/
	
	public UsuarioDAO() {
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
	
	public int validarUsuario(String correo, String password) {
		int existe=0;
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "select * from Usuario where correo='" + correo + "' and password='" + password + "' and estado='Activo'";
			ResultSet rs = stmt.executeQuery(sentenciaSQL); /*Aqui ejecutamos el query y el resultado lo guardamos en un ResultSet*/
			while (rs.next()) {
				existe = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}
	
	public List<Usuario> buscarTodosUsuarios(){
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "select * from Usuario";
			ResultSet rs = stmt.executeQuery(sentenciaSQL); /*Aqui ejecutamos el query y el resultado lo guardamos en un ResultSet*/
			while (rs.next()) {
				//Cada vez que entre a este while se trata de un usuario
				//Voy a crear un objeto Usuario.
				int codigo = rs.getInt(1);
				String correo = rs.getString(2);
				String password = rs.getString(3);
				String estado = rs.getString(4);
				String perfil = rs.getString(5);
				Usuario objUsuario = new Usuario(codigo,correo,password,estado,perfil);
				listaUsuarios.add(objUsuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	public List<Usuario> buscarUsuariosxCorreo(String correo){
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "select * from Usuario where correo like '%" + correo +"%'";
			ResultSet rs = stmt.executeQuery(sentenciaSQL); /*Aqui ejecutamos el query y el resultado lo guardamos en un ResultSet*/
			while (rs.next()) {
				//Cada vez que entre a este while se trata de un usuario
				//Voy a crear un objeto Usuario.
				int codigo = rs.getInt(1);
				String correoUsuario = rs.getString(2);
				String password = rs.getString(3);
				String estado = rs.getString(4);
				String perfil = rs.getString(5);
				Usuario objUsuario = new Usuario(codigo,correoUsuario,password,estado,perfil);
				listaUsuarios.add(objUsuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsuarios;		
	}
	
	public int validarUsuarioxCorreo(String correo) {
		int existe=0;
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "select * from Usuario where correo='" + correo + "'";
			ResultSet rs = stmt.executeQuery(sentenciaSQL); /*Aqui ejecutamos el query y el resultado lo guardamos en un ResultSet*/
			while (rs.next()) {
				existe = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;		
	}
	
	public void registrarUsuario(String correo, String password, String estado, String perfil) {
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "insert into Usuario(correo,password,estado,perfil) values ('"+correo+"','"+password+"','" + estado + "','" + perfil +"')";
			stmt.execute(sentenciaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarUsuario(int codigo) {
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "delete from Usuario where codigo="+codigo;
			stmt.execute(sentenciaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Usuario buscarUsuarioxCodigo(int codigo) {
		Usuario objUsuario = new Usuario();
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "select * from Usuario where codigo =" + codigo;
			ResultSet rs = stmt.executeQuery(sentenciaSQL); /*Aqui ejecutamos el query y el resultado lo guardamos en un ResultSet*/
			while (rs.next()) {
				//Cada vez que entre a este while se trata de un usuario
				//Voy a crear un objeto Usuario.
				int codigoUsuario = rs.getInt(1);
				String correoUsuario = rs.getString(2);
				String password = rs.getString(3);
				String estado = rs.getString(4);
				String perfil = rs.getString(5);
				objUsuario.setCodigo(codigoUsuario);
				objUsuario.setCorreo(correoUsuario);
				objUsuario.setEstado(estado);
				objUsuario.setPassword(password);
				objUsuario.setPerfil(perfil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objUsuario;
	}
	
	public void actualizarUsuario(int codigo, String correo, String password, String perfil, String estado) {
		Statement stmt; /* Este objeto nos va a permitir hacer los querys*/
		try {
			stmt = this.conexion.createStatement();
			String sentenciaSQL = "update Usuario set correo='" + correo + "', password='" + password +"', perfil='" + perfil + "', estado='" + estado +"' where codigo=" + codigo;
			stmt.execute(sentenciaSQL);
			stmt.close(); /*Esto para cerrar la conexion de forma explicita*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
