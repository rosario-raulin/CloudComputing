<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>HelloCloud - Register</title>
	</head>
	<body>
		<%@ include file="navigation.jsp" %>
		
		<div id="registerform">
			<form method="post" action="register">
				<p>E-Mail: <input type="text" name="email"></p>
				<p>Name: <input type="text" name="username"></p>
				<p>Password: <input type="password" name="password"></p>
				<input type="submit" value="Register">
			</form>
		</div>
	</body>
</html>
