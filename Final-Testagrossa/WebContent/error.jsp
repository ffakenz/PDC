<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" import="java.io.*"%>
<%
	boolean fromAjax = request.getHeader("X-Requested-With") != null;
	if(fromAjax) {
		out.println(request.getAttribute("error"));
	} else { 
%>
	<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html">
		<meta charset="UTF-8"/>
	    <meta name="description" content="Examen Final PDC">
		<meta name="author" content="Testagrossa Franco">
	    <title>Reporte de Error</title>
	</head>
	<body>
		<div id="error">
			<%= exception.toString() %>
			<% out.println(request.getAttribute("error")); %>
		</div>
	</body>
	</html>
<%	
	} 
%>
