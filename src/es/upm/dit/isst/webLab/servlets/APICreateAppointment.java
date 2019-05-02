package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;
import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Patient;

/**
 * Servlet implementation class APICreateAppointment
 */
@WebServlet("/APICreateAppointment")
public class APICreateAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APICreateAppointment() {
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
		
		String doc_dni = req.getParameter("doc_dni");
		String pat_dni = req.getParameter("pat_dni");
		Integer start_time = Integer.valueOf(req.getParameter("start_time"));
		java.sql.Date date = Date.valueOf(req.getParameter( "date" ));
		
		DoctorDAO ddao = DoctorDAOImplementation.getInstance();
		Doctor doctor = ddao.read(doc_dni);
		
		PatientDAO pdao = PatientDAOImplementation.getInstance();
		Patient patient = pdao.read(pat_dni);
		
		setAccessControlHeaders(resp);

		Appointment app = new Appointment();
		app.setApp_doctor(doctor);
		app.setApp_patient(patient);
		app.setDate(date);
		app.setStart_time(start_time);
		app.setPresence(false);
		
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		adao.create( app );
		
    	PrintWriter out = resp.getWriter();
		
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
 
    	out.print("200OK");
	}
	
	@Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
    }

}
