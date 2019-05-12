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
import org.json.JSONObject;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
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
			
		String dni = req.getParameter("dni");
		String pass = req.getParameter( "password");

		Subject currentUser = SecurityUtils.getSubject();
		
		if ( !currentUser.isAuthenticated() ) {
			UsernamePasswordToken token = new UsernamePasswordToken( dni, pass );
			try {
				currentUser.login( token );
				if ( currentUser.hasRole( "admin" ) ) {
					System.out.println(" admin" +currentUser.getPrincipal());
				}
				else if ( currentUser.hasRole( "doctor" ) ) {
					DoctorDAO ddao = DoctorDAOImplementation.getInstance();
					Doctor d = ddao.read(dni);
					System.out.println(" doctor" +currentUser.getPrincipal());
					out.print(d.toJSON());
				}
				else if ( currentUser.hasRole( "patient" ) ) {
					System.out.println(" patient" +currentUser.getPrincipal());
					PatientDAO pdao = PatientDAOImplementation.getInstance();
					Patient p = pdao.read(dni);
					out.print(p.toJSON());				
				}
				else 
					System.out.println(" error" +currentUser.getPrincipal());
					//out.print();	
			} catch ( Exception e ) {
				System.out.println("error");
			}
		} else
			System.out.println("error 404");

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
