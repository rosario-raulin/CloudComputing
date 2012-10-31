<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="de.raulin.rosario.helloCloud.sessions.*" %>
<%@ page import="de.raulin.rosario.helloCloud.utils.*" %>
	
<% 
	Session s;
	try {
		s = SessionHandler.getSecureSession(request, response);
	} catch (InsecureConnectionException e) {
		s = SessionHandler.getSession(request, response);
	}
	String username = (String) s.getValue("username");
%>
	
<div id="nav">
	<ul>
		<li><a href="<%= UrlBuilder.getUrl(request, "index.jsp") %>">Home</a>
		<% if (username != null) { %>
		<li>Hello, <%= username %>!</li>
		<li><a href="<%= UrlBuilder.getSecureUrl(request, "logout") %>">Logout</a></li>
		<% } else { %>
		<li><a href="<%= UrlBuilder.getSecureUrl(request, "secure/login.jsp") %>">Login</a></li>
		<li><a href="<%= UrlBuilder.getSecureUrl(request, "secure/register.jsp") %>">Register</a></li>
		<% } %>
	</ul>
</div>
