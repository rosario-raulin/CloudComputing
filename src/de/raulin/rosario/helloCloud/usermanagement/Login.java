package de.raulin.rosario.helloCloud.usermanagement;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.raulin.rosario.helloCloud.sessions.InsecureConnectionException;
import de.raulin.rosario.helloCloud.sessions.SecureSession;
import de.raulin.rosario.helloCloud.sessions.Session;
import de.raulin.rosario.helloCloud.sessions.SessionHandler;
import de.raulin.rosario.helloCloud.utils.UrlBuilder;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		final String email = (String) req.getParameter("email");
		final String password = (String) req.getParameter("password");
		try {
			final User user = new User(email, password);
			DynamoDBHelper.lookup(user);

			final Session s = SessionHandler.getSession(req, resp);
			s.addValue("loggedIn", true);
			s.addValue("username", user.getName());
			try {
				final SecureSession ss = SessionHandler.getSecureSession(req, resp);
				ss.addValue("loggedIn", true);
				ss.addValue("username", user.getName() + " (secure)");
			} catch (InsecureConnectionException e) {
				System.err.println(e);
			}
			
			resp.sendRedirect(UrlBuilder.getUrl(req, "index.jsp?success=1"));
		} catch (EmptyInputException e) {
			resp.sendRedirect(UrlBuilder.getSecureUrl(req, "secure/login.jsp?empty=1"));
		} catch (InvalidLoginException e) {
			resp.sendRedirect(UrlBuilder.getSecureUrl(req, "secure/login.jsp?invalid=1"));
		}
	}

}
