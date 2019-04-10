package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
import es.upm.dit.isst.webLab.dao.SpecialtyDAO;
import es.upm.dit.isst.webLab.dao.SpecialtyDAOImplementation;
import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DoctorDAO ddao = DoctorDAOImplementation.getInstance();
		req.getSession().setAttribute( "doctor_list", ddao.readAll() );
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		req.getSession().setAttribute( "appointment_list", adao.readAll() );
		PatientDAO pdao = PatientDAOImplementation.getInstance();
		req.getSession().setAttribute( "patient_list", pdao.readAll() );
		SpecialtyDAO sdao = SpecialtyDAOImplementation.getInstance();
		req.getSession().setAttribute( "specialty_list", sdao.readAll() );
		
		getServletContext().getRequestDispatcher( "/AdminView.jsp" ).forward( req, resp );
	}
}
