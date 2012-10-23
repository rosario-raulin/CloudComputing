<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>HelloCloud - Login</title>
		<link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
	</head>
	<body>
		<%@ include file="navigation.jsp" %>
		
		<div id="loginform">
			<form method="post" action="login">
				<p>E-Mail: <input type="text" name="email"></p>
				<p>Password: <input type="password" name="password"></p>
				<input type="submit" value="Login">
			</form>
		</div>
	</body>
</html>
