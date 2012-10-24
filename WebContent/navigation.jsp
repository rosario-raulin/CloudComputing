<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="nav">
	<ul>
		<li><a href="index.jsp">Home</a>
		<% if (request.getSession().getAttribute("loggedIn") != null) { %>
		<li><a href="logout">Logout</a></li>
		<% } else { %>
		<li><a href="login.jsp">Login</a></li>
		<li><a href="register.jsp">Register</a></li>
		<% } %>
	</ul>
</div>
