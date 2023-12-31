package com.ISILVentas.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ISILVentas.dao.ProductoDAO;
import com.ISILVentas.model.ProductoVenta;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet("/producto")
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO objProductoDAO = new ProductoDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcionGet");
		switch (opcion) {
			case "buscarProductos" : {
				buscarProductos(request,response);
				break;
			}
			case "eliminarProducto": {
				eliminarProducto(request,response);
				break;
			}
			case "editarProducto": {
				editarProducto(request,response);
			}
		}
		
	}

	public void editarProducto(HttpServletRequest request,HttpServletResponse response) {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		ProductoVenta objProductoVenta = objProductoDAO.buscarProductoxCodigo(codigo);
		request.setAttribute("objProductoVenta", objProductoVenta);
		String paginaDestino = "/editarProducto.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		objProductoDAO.eliminarProducto_SP(codigo);
		String paginaDestino = "/gestionProductos.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void buscarProductos(HttpServletRequest request, HttpServletResponse response) {
		String tipoProducto = request.getParameter("tipoProducto");
		/*List<ProductoVenta> listaProductos = objProductoDAO.buscarProductos(tipoProducto);*/
		List<ProductoVenta> listaProductos = objProductoDAO.buscarProductos_SP(tipoProducto);
		request.setAttribute("listaProductos", listaProductos);
		String paginaDestino = "/gestionProductos.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcionPost");
		switch (opcion) {
			case "mostrarNuevoProducto" : {
				mostrarNuevoProducto(request,response);
				break;
			}
			case "grabarProducto": {
				grabarProducto(request,response);
				break;
			}
			case "actualizarProducto": {
				actualizarProducto(request, response);
				break;
			}
		}
	}

	public void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String nombre = request.getParameter("nombre");
		String tipoProducto = request.getParameter("tipoProducto");
		float precio = Float.parseFloat(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		objProductoDAO.actualizarProducto_SP(codigo, nombre, tipoProducto, precio, stock);
		String paginaDestino = "/gestionProductos.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void grabarProducto(HttpServletRequest request, HttpServletResponse response) {
		String nombre = request.getParameter("nombre");
		String tipoProducto = request.getParameter("tipoProducto");
		float precio = Float.parseFloat(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		objProductoDAO.registrarProducto_SP(nombre, tipoProducto, precio, stock);
		String paginaDestino = "/gestionProductos.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarNuevoProducto(HttpServletRequest request, HttpServletResponse response) {
		String paginaDestino = "/nuevoProducto.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
