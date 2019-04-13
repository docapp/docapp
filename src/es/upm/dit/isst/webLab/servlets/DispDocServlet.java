package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;
import es.upm.dit.isst.webLab.dao.SpecialtyDAO;
import es.upm.dit.isst.webLab.dao.SpecialtyDAOImplementation;
import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Specialty;
import es.upm.dit.isst.webLab.model.TimeSlot;

/**
 * Servlet implementation class DispDocServlet
 */
@WebServlet("/DispDocServlet")
public class DispDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispDocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doc_dni = req.getParameter("doc_dni");
		String pat_dni = req.getParameter("pat_dni");

		java.sql.Date date = Date.valueOf(req.getParameter( "date" ));

		TimeSlot timeSlot = new TimeSlot();
		HashMap <Integer, String> slots = timeSlot.getDaySlots();
		
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		Collection<Appointment> chosen = adao.filterDateDoctor(doc_dni, date);
		HashMap <Integer, String> available = timeSlot.getAvailableTimeSlots(chosen);
		
		req.getSession().setAttribute( "available", available.values());
		req.getSession().setAttribute( "doc_dni", doc_dni);
		req.getSession().setAttribute( "pat_dni", pat_dni);
		req.getSession().setAttribute( "date", date);
	
		getServletContext().getRequestDispatcher( "/TimeView.jsp" ).forward( req, resp );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
