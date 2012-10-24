<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	if (request.getMethod().equals("HEAD"))
		return;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>HelloCloud - Welcome</title>
<link rel="stylesheet" href="styles/styles.css" type="text/css"
	media="screen">
</head>
<body>
	<%@ include file="navigation.jsp"%>

	<h1>Welcome...</h1>

	<p>... to the Cloud! There is no real content here yet.</p>
</body>
</html>