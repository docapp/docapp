package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.upm.dit.isst.webLab.dao.AdminDAO;
import es.upm.dit.isst.webLab.dao.AdminDAOImplementation;
import es.upm.dit.isst.webLab.model.Admin;

/**
 * Servlet implementation class CreateAdminServlet
 */
@WebServlet("/CreateAdminServlet")
public class CreateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String admin_dni = (String) req.getParameter("dni");
		String admin_name = (String) req.getParameter("name");
		String admin_surname = (String) req.getParameter("surname");
		String admin_pass = (String) req.getParameter("password");
		
		Admin admin = new Admin();
		admin.setDni(admin_dni);
		admin.setName(admin_name);
		admin.setSurname(admin_surname);
		admin.setPassword( new Sha256Hash( admin_pass ).toString() );

		AdminDAO adao = AdminDAOImplementation.getInstance();
		
		adao.create( admin );
		
		resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
		

	}

}
