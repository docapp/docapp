package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
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
 * Servlet implementation class APIPatientList
 */
@WebServlet("/api/patapp")
public class APIPatientList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APIPatientList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
    	setAccessControlHeaders(resp);
        	
    	String dni = req.getParameter("dni");
		
    	PatientDAO pdao = PatientDAOImplementation.getInstance();
		Patient p = pdao.read(dni);
		
		
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
    	PrintWriter out = resp.getWriter();
    	
    	JSONArray array = new JSONArray();

    	for(AppointmentAndDoctor s : anotherList) {
    		array.put(s.toJSON());
    	}
    	
    	out.print(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
    }

}
