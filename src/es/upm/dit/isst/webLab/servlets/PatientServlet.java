package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Collection;

import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;
import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
import es.upm.dit.isst.webLab.dao.SpecialtyDAO;
import es.upm.dit.isst.webLab.dao.SpecialtyDAOImplementation;
import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.AppointmentAndDoctor;
import es.upm.dit.isst.webLab.model.AppointmentAndPatient;
import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Patient;
import es.upm.dit.isst.webLab.model.Specialty;


/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PatientServlet() {
        super();

    }
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pat_dni = req.getParameter("pat_dni");
		
		PatientDAO pdao = PatientDAOImplementation.getInstance();
		Patient p = pdao.read(pat_dni);
		
		SpecialtyDAO sdao = SpecialtyDAOImplementation.getInstance();
		Collection<Specialty> specs = sdao.readAll();
		
		
		Collection<Appointment> appointments = p.getAppointments();
		Collection<AppointmentAndDoctor> anotherList = new ArrayList<AppointmentAndDoctor>();
		
		for (Appointment app : appointments ) {
			
			AppointmentAndDoctor obj = new AppointmentAndDoctor();
			
			Doctor doc = app.getApp_doctor();
			
			obj.setId(app.getId());
			obj.setApp_doctor(app.getApp_doctor());
			obj.setApp_patient(app.getApp_patient());
			obj.setDate(app.getDate());
			obj.setStart_time(app.getStart_time());
			obj.setPresence(app.getPresence());
			obj.setDoctor(doc);
			
			anotherList.add(obj);
		}
		
		req.getSession().setAttribute( "appointments", anotherList);
		req.getSession().setAttribute( "specialties", specs);
		req.getSession().setAttribute( "pat_dni", pat_dni);

		
		getServletContext().getRequestDispatcher( "/PatientView.jsp" ).forward( req, resp );
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
