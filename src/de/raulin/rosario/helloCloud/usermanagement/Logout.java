package de.raulin.rosario.helloCloud.usermanagement;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.raulin.rosario.helloCloud.sessions.SessionHandler;
import de.raulin.rosario.helloCloud.utils.UrlBuilder;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		SessionHandler.cancelAllSessions(request, response);
		response.sendRedirect(UrlBuilder.getSecureUrl(request, "secure/login.jsp?loggedOut=1"));
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logout(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logout(request, response);
	}

}
