package com.ISILVentas.model;

public class ProductoVenta {
	
	private int codigo;
	private String tipoProducto;
	private float precio;
	private int stock;
	private String nombre;
	
	public ProductoVenta(int codigo, String tipoProducto, float precio, int stock, String nombre) {
		this.codigo = codigo;
		this.tipoProducto = tipoProducto;
		this.precio = precio;
		this.stock = stock;
		this.nombre = nombre;
	}

	public ProductoVenta() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
