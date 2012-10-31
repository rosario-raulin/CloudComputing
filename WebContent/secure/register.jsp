<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="de.raulin.rosario.helloCloud.sessions.*" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>HelloCloud - Register</title>
<link rel="stylesheet" href="../styles/styles.css" type="text/css"
	media="screen">
</head>
<body>
	<%@ include file="../navigation.jsp"%>
 
	<%
		if (SessionHandler.getSecureSession(request, response).getValue("loggedIn") != null) {
	%>
	<p>You are logged in. A new registration makes now sense at all.</p>
	<%
		} else if (request.getParameter("success") != null) {
	%>
	<p>
		You registration was successful. You can now <a href="login.jsp">log
			in</a>.
	</p>
	<%
		} else {
	%>

	<%
		if (request.getParameter("inuse") != null) {
	%>
	<p>Error: E-Mail already in use!</p>
	<%
		} else if (request.getParameter("empty") != null) {
	%>
	<p>Error: Please fill out all the forms!</p>
	<%
		}
	%>
	<div id="registerform">
		<form method="post" action="../register">
			<p>
				E-Mail: <input type="text" name="email">
			</p>
			<p>
				Name: <input type="text" name="username">
			</p>
			<p>
				Password: <input type="password" name="password">
			</p>
			<input type="submit" value="Register">
		</form>
	</div>
	<%
		}
	%>
</body>
</html>
