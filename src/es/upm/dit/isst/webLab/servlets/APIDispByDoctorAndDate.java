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

import org.json.JSONArray;
import org.json.JSONObject;

import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;
import es.upm.dit.isst.webLab.dao.SpecialtyDAO;
import es.upm.dit.isst.webLab.dao.SpecialtyDAOImplementation;
import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.Specialty;
import es.upm.dit.isst.webLab.model.TimeSlot;

/**
 * Servlet implementation class DispByDoctorAndDate
 */
@WebServlet("/api/availability")
public class APIDispByDoctorAndDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APIDispByDoctorAndDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doc_dni = req.getParameter("doc_dni");
		java.sql.Date date = Date.valueOf(req.getParameter( "date" ));

		TimeSlot timeSlot = new TimeSlot();
		HashMap <Integer, String> slots = timeSlot.getDaySlots();
		
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		Collection<Appointment> chosen = adao.filterDateDoctor(doc_dni, date);
		HashMap <Integer, String> available = timeSlot.getAvailableTimeSlots(chosen);
		
		JSONObject av = new JSONObject();
				
		for (HashMap.Entry<Integer, String> entry : available.entrySet()) {
			av.put(String.valueOf(entry.getKey()), entry.getValue());
		}
		
		
    	PrintWriter out = resp.getWriter();
		
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
 
    	
    	out.print(av.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, resp);
	}

}
