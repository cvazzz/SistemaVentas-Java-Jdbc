package com.ISILVentas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ISILVentas.dao.UsuarioDAO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO objUsuarioDAO = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("opcionGet");
		switch (accion) {
			case "mostrarGestionUsuarios": {
				mostrarGestionUsuarios(request,response);
				break;
			}
			case "mostrarGestionProductos": {
				mostrarGestionProductos(request,response);
				break;
			}
		}
	}

	public void mostrarGestionProductos(HttpServletRequest request, HttpServletResponse response) {
		String paginaDestino = "/gestionProductos.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarGestionUsuarios(HttpServletRequest request,HttpServletResponse response) {
		String paginaDestino = "/gestionUsuarios.jsp";
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
		String accion = request.getParameter("opcionPost");
		switch (accion) {
			case "validarUsuario" : {
				validarUsuario(request,response);
				break;
			}
		}
	}
	
	public void validarUsuario(HttpServletRequest request, HttpServletResponse response) {
		/*Estoy recuperando los valores que el usuario ha ingresado en las cajas de texto*/
		String paginaDestino = "/index.jsp";
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		int existe = objUsuarioDAO.validarUsuario(correo, password);
		if (existe==1) {
			paginaDestino = "/principal.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}