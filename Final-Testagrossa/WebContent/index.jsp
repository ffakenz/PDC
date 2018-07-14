<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ct" %>

<%@ include file="./public/header.html" %> 


	<ct:grupos nombre="cod_grupo" fun_js="getGrupo(this)" presentacion_datos="R" todos="N"></ct:grupos>
	
	<div id="message"></div>	
	<div id="result"></div>
	
	<div id="links">
		<form action="javascript:void(null)" method="post">			
			<a href="" id="guardar">Guardar</a>
			<a href="" id="cancelar">Cancelar</a>			
		</form>
	</div>
	
	

	
<%@ include file="./public/footer.html" %> 