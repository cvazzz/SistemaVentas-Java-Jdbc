package com.ISILVentas.model;

public class Usuario {
	/*En las clases model, los atributos son los mismo campos de la tabla*/
	private int codigo;
	private String correo;
	private String password;
	private String estado;
	private String perfil;
	
	public Usuario() {
	
	}
	
	public Usuario(int codigo, String correo, String password, String estado, String perfil) {
		this.codigo = codigo;
		this.correo = correo;
		this.password = password;
		this.estado = estado;
		this.perfil = perfil;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
		
}
