<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<% 
	Boolean loggedIn = (Boolean) request.getSession().getAttribute("loggedIn");
	String invalid = request.getParameter("invalid");
	String empty = request.getParameter("empty");
	String success = request.getParameter("success");
	String loggedOut = request.getParameter("loggedOut");

%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>HelloCloud - Login</title>
		<link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
	</head>
	<body>
		<%@ include file="navigation.jsp" %>
	
		<% if (loggedIn != null) {
			if (success != null) { %>
			<p>You're now logged in.</p>
			<% }  else { %>
			<p>You are already logged in.</p>
			<form action="logout" method="post">
				<input type="submit" value="logout">
			</form>
			<% } 
		} else if (loggedOut != null) { %>
			<p>You are now logged off.</p>
		<% }  else { %>
				
				<% if (empty != null) { %>
					<p>Error: Please fill out all the forms!</p>
				<% } else if (invalid != null) { %>
					<p>Error: Login failed!</p>
				<% } %>
				
		<div id="loginform">
			<form method="post" action="login">
				<p>E-Mail: <input type="text" name="email"></p>
				<p>Password: <input type="password" name="password"></p>
				<input type="submit" value="Login">
			</form>
		</div>
		<% } %>
	</body>
</html>
