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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.AppointmentAndPatient;
import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Patient;
import es.upm.dit.isst.webLab.model.Specialty;

/**
 * Servlet implementation class APILogin
 */
@WebServlet("/api/login")
public class APILogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APILogin() {
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
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
		setAccessControlHeaders(resp);
		PrintWriter out = resp.getWriter();
		
		System.out.println("hola hola hola");
		
		String dni = req.getParameter("dni");
		String pass = req.getParameter( "password");

		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
			UsernamePasswordToken token = new UsernamePasswordToken( dni, pass );
			try {
				currentUser.login( token );
			if ( currentUser.hasRole( "admin" ) )
					resp.sendRedirect( req.getContextPath() + "/AdminServlet?admin_dni=" + currentUser.getPrincipal() );
				else if ( currentUser.hasRole( "doctor" ) ) {
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
					
					JSONArray array = new JSONArray();

			    	for(AppointmentAndPatient a : anotherList) {
			    		array.put(a.toJSON());
			    	}
			    	
			    	out.print(array.toString());
				}
				else if ( currentUser.hasRole( "patient" ) )
					resp.sendRedirect( req.getContextPath() + "/PatientServlet?pat_dni=" + currentUser.getPrincipal() );
				else 
					resp.sendRedirect( req.getContextPath() + "/LoginServlet");
			} catch ( Exception e ) {
				//resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
				System.out.println("error");
			}
		} else
			// resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
		
    	out.print(dni);

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
        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

    }

}
