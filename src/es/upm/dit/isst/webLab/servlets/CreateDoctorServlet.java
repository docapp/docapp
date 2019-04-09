package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.model.Doctor;

@WebServlet("/CreateDoctorServlet")
public class CreateDoctorServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter( "name" );
		String surname = req.getParameter( "surname" );
		String specialty = req.getParameter( "specialty" );

		String password = req.getParameter( "password" );
		
		Doctor doctor = new Doctor();
		doctor.setName( name );
		doctor.setSurname( surname );
		doctor.setSpecialty( specialty );
		
		doctor.setPassword( new Sha256Hash( password ).toString() );
		
		DoctorDAO ddao = DoctorDAOImplementation.getInstance();
		ddao.create( doctor );
		
		resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
	}
}
