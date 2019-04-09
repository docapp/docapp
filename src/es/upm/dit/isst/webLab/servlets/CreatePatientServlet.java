package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
import es.upm.dit.isst.webLab.model.Patient;

@WebServlet("/CreatePatientServlet")
public class CreatePatientServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter( "name" );
		String surname = req.getParameter( "surname" );
		String birth = req.getParameter( "birth" );
		
		java.sql.Date birth_date = Date.valueOf(birth);

		String password = req.getParameter( "password" );
	
		
		Patient patient = new Patient();
		patient.setName( name );
		patient.setSurname( surname );
		patient.setBirth(birth_date);
		
		patient.setPassword( new Sha256Hash( password ).toString() );
		
		PatientDAO pdao = PatientDAOImplementation.getInstance();
		pdao.create( patient );
		
		resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
	}
}