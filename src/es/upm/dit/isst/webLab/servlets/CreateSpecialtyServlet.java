package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.upm.dit.isst.webLab.dao.SpecialtyDAO;
import es.upm.dit.isst.webLab.dao.SpecialtyDAOImplementation;
import es.upm.dit.isst.webLab.model.Specialty;

@WebServlet("/CreateSpecialtyServlet")
public class CreateSpecialtyServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter( "name" );
		String description = req.getParameter( "description" );
		String doctors = req.getParameter( "specialty" );

		
		Specialty spec = new Specialty();
		spec.setId(id);
		spec.setName(name);
		spec.setDescription(description);
			
		SpecialtyDAO sdao = SpecialtyDAOImplementation.getInstance();
		sdao.create( spec );
		
		resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
	}
}
