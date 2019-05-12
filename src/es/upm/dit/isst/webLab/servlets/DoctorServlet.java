package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Patient;
import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.AppointmentAndPatient;


/**
 * Servlet implementation class ProfessorServlet
 */
@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoctorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dni = req.getParameter("dni");
		
		DoctorDAO pdao = DoctorDAOImplementation.getInstance();
		Doctor p = pdao.read(dni);
		
		Collection<Appointment> appointments = p.getAppointments();
		
		Collection<AppointmentAndPatient> anotherList = new ArrayList<AppointmentAndPatient>();
		
		for (Appointment app : appointments ) {
			
			AppointmentAndPatient obj = new AppointmentAndPatient();
			
			Patient pat = app.getApp_patient();
			obj.setId(app.getId());
			obj.setApp_doctor(app.getApp_doctor());
			obj.setApp_patient(app.getApp_patient());
			obj.setDate(app.getDate());
			obj.setStart_time(app.getStart_time());
			obj.setPresence(app.getPresence());
			obj.setPatient(pat);
			
			anotherList.add(obj);
		}
		
		req.getSession().setAttribute( "anotherList", anotherList);
		
		getServletContext().getRequestDispatcher( "/DoctorView.jsp" ).forward( req, resp );
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

    }

}
