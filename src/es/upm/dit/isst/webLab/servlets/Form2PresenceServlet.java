package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;

import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.Patient;


/**
 * Servlet implementation class Form2PresenceServlet
 */
@WebServlet("/Form2PresenceServlet")
public class Form2PresenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Form2PresenceServlet() {
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
		System.out.println("doPost");
		
		Integer id = Integer.valueOf(req.getParameter("id"));
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		
		Appointment app = adao.read(id);
		app.setPresence(true);
		
		adao.update(app);
		
		Patient p = app.getApp_patient();
		String dni  = p.getDni();

		resp.sendRedirect( req.getContextPath() + "/PatientServlet?pat_dni=" + dni );
		

			

	}

}
