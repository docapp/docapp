package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.HashMap;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
import es.upm.dit.isst.webLab.dao.SpecialtyDAO;
import es.upm.dit.isst.webLab.dao.SpecialtyDAOImplementation;
import es.upm.dit.isst.webLab.model.Admin;
import es.upm.dit.isst.webLab.dao.AdminDAO;
import es.upm.dit.isst.webLab.dao.AdminDAOImplementation;
import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String admin_dni = req.getParameter("admin_dni");
		Boolean is_pas = false;
		
		DoctorDAO ddao = DoctorDAOImplementation.getInstance();
		req.getSession().setAttribute( "doctor_list", ddao.readAll() );
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		req.getSession().setAttribute( "appointment_list", adao.readAll() );
		PatientDAO pdao = PatientDAOImplementation.getInstance();
		req.getSession().setAttribute( "patient_list", pdao.readAll() );
		SpecialtyDAO sdao = SpecialtyDAOImplementation.getInstance();
		req.getSession().setAttribute( "specialty_list", sdao.readAll() );
		
		AdminDAO pasdao = AdminDAOImplementation.getInstance();
		
		Collection<Admin> admins = pasdao.readAll();
		HashMap<String, String> all = new HashMap<String, String>();
		
		for(Admin a : admins) {
			
			all.put(a.getDni(), a.getName());
		}
		
		String contains = all.get(admin_dni);
		
		if(contains != null) {
			is_pas = true;
		}
		
		req.getSession().setAttribute( "admin_list", admins );
		req.getSession().setAttribute( "is_pas", is_pas );
		
		getServletContext().getRequestDispatcher( "/AdminView.jsp" ).forward( req, resp );
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
