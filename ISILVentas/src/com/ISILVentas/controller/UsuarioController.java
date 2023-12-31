package com.ISILVentas.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ISILVentas.dao.UsuarioDAO;
import com.ISILVentas.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UsuarioDAO objUsuarioDAO = new UsuarioDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcionGet");
		switch (opcion) {
			case "buscarUsuarios": {
				buscarUsuarios(request,response);
				break;
			}
			case "editarUsuario": {
				editarUsuario(request,response);
				break;
			}
			case "eliminarUsuario": {
				eliminarUsuario(request,response);
				break;
			}
		}
	}
	
	public void editarUsuario(HttpServletRequest request,HttpServletResponse response) {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Usuario objUsuario = objUsuarioDAO.buscarUsuarioxCodigo(codigo);
		request.setAttribute("objUsuario", objUsuario);
		String paginaDestino = "/editarUsuario.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarUsuario(HttpServletRequest request,HttpServletResponse response) {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		objUsuarioDAO.eliminarUsuario(codigo);
		String paginaDestino = "/gestionUsuarios.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void buscarUsuarios(HttpServletRequest request,HttpServletResponse response) {
		List<Usuario> listaUsuarios;
		String correo = request.getParameter("correo");
		if (correo.equals("")) {
			listaUsuarios = objUsuarioDAO.buscarTodosUsuarios();
		}else {
			listaUsuarios = objUsuarioDAO.buscarUsuariosxCorreo(correo);
		}
		request.setAttribute("listaUsuarios", listaUsuarios); /*esto de aqui deja una variable en memoria para que pueda ser utilizado por el JSP*/
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
		String opcion = request.getParameter("opcionPost");
		switch (opcion) {
			case "mostrarNuevoUsuario" : {
				mostrarNuevoUsuario(request,response);
				break;
			}
			case "grabarUsuario" : {
				grabarUsuario(request,response);
				break;
			}
			case "actualizarUsuario":{
				actualizarUsuario(request,response);
				break;
			}
		}
	}

	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response){
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		String perfil = request.getParameter("perfil");
		String estado = request.getParameter("estado");
		objUsuarioDAO.actualizarUsuario(codigo, correo, password, perfil, estado);
		String paginaDestino = "/gestionUsuarios.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void grabarUsuario(HttpServletRequest request, HttpServletResponse response) {
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		String perfil = request.getParameter("perfil");
		String paginaDestino;
		int existe = objUsuarioDAO.validarUsuarioxCorreo(correo);
		if (existe==0) {
			/*Aqui el usuario no existe y hay que registrarlo*/
			/*Se le pone activo directamente porque un usuario siempre se crea en estado Activo*/
			objUsuarioDAO.registrarUsuario(correo, password, "Activo", perfil);
			paginaDestino = "/gestionUsuarios.jsp";
		}
		else {
			/*Aqui el usuario existe y no hay que registrarlo, hay que avisarle al usuario*/
			paginaDestino = "/nuevoUsuario.jsp";
		}
		/*Este codigo siempre nos envia a una nueva pagina*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarNuevoUsuario(HttpServletRequest request, HttpServletResponse response) {
		String paginaDestino = "/nuevoUsuario.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
