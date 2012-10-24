package de.raulin.rosario.helloCloud.usermanagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String email = (String) request.getParameter("email");
		final String password = (String) request.getParameter("password");
		final String username = (String) request.getParameter("username");

		try {
			final User user = new User(email, username, password);
			DynamoDBHelper.add(user);
			response.sendRedirect(request.getContextPath() + "/register.jsp?success=1");
		} catch (EmptyInputException e) {
			response.sendRedirect(request.getContextPath() + "/register.jsp?empty=1");
		} catch (InUseException e) {
			response.sendRedirect(request.getContextPath() + "/register.jsp?inuse=1");
		}
	}
}
