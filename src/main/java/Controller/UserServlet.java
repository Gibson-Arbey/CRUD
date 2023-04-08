package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDao;
import entities.User;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
    public void init(ServletConfig config) throws ServletException {
		
		this.userDao = new UserDao(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch(action) {
			case"/new": showPage(request, response, "usuario.jsp");
				break;
			case"/insert": insertarUsuario(request, response);
				break;
			case"/delete": eliminarUsuario(request, response);
				break;
			case"/edit": showEditForm(request, response);
				break;
			case"/update": actualizarUsuario(request, response);
				break;
			default:	listUsuarios(request, response);
				break;
			}
		}catch(SQLException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void showPage(HttpServletRequest request, HttpServletResponse response, String pag) 
			throws ServletException, IOException {
		 request.getRequestDispatcher(pag).forward(request, response);
	}
	
	private void  showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		User usuarioActual = userDao.find(id);
		request.setAttribute("user", usuarioActual);
		request.getRequestDispatcher("usuario.jsp").forward(request, response);
	}
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		
		User u = new User();
		u = userDao.find(id);
		u.setName(name);
		u.setEmail(email);
		u.setPais(pais);
		
		
		userDao.update(u);
		
		response.sendRedirect("list");
	}
	
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		String nombre = request.getParameter("name");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		userDao.insert(new User(nombre, email, pais));
		
		response.sendRedirect("list");
	}
	
	private void  eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		User u = userDao.find(id);
		userDao.delete(u);
		response.sendRedirect("list");
	}
	
	private void listUsuarios(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, SQLException, IOException {
		
		List<User> listUsuarios = new ArrayList<>();
		listUsuarios = userDao.list(); 
		request.setAttribute("listUsuarios", listUsuarios);
		showPage(request, response,"usuariolist.jsp");
	}
	
}
