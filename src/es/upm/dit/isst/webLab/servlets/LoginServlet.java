package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;

@WebServlet({ "/LoginServlet", "/" })
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DoctorDAO pdao = DoctorDAOImplementation.getInstance();
		req.getSession().setAttribute( "doctor_list", pdao.readAll() );
		getServletContext().getRequestDispatcher( "/LoginView.jsp" ).forward( req, resp );
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dni = req.getParameter( "dni" );
		String pass = req.getParameter( "password" );
		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
			UsernamePasswordToken token = new UsernamePasswordToken( dni, pass );
			try {
				currentUser.login( token );
				if ( currentUser.hasRole( "admin" ) )
					resp.sendRedirect( req.getContextPath() + "/AdminServlet?admin_dni=" + currentUser.getPrincipal() );
				else if ( currentUser.hasRole( "doctor" ) )
					resp.sendRedirect( req.getContextPath() + "/DoctorServlet?dni=" + currentUser.getPrincipal() );
				else if ( currentUser.hasRole( "patient" ) )
					resp.sendRedirect( req.getContextPath() + "/PatientServlet?pat_dni=" + currentUser.getPrincipal() );
				else 
					resp.sendRedirect( req.getContextPath() + "/LoginServlet");
			} catch ( Exception e ) {
				resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
			}
		} else
			resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
	}
	
	@Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET");
        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

    }
}
