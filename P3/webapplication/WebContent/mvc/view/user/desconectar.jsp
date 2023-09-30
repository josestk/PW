<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.bussiness.contacto.Contacto,es.uco.pw.data.dao.contacto.ContactoDAO" %>
<%@ page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Desconexion</title>
	</head>
	<body>
		<!-- Formulario para desconexion de la aplicacion o vuelta al inicio de la aplicacion -->
		<div id="cuadro">
			<form method="get" action="../../../desconexionController">
			<p id="titulo1">Cierre de sesion</p>
			<input type="hidden" id="opcion" name="opcion" value=0></input>
			<br/><br/>
			<button name="Si"  id="Si" class="entrada" onclick="document.getElementById('opcion').value=1">Si</button>
			<br/><br/>
			<button name="No"  id="No" class="entrada" onclick="document.getElementById('opcion').value=2">No</button>
			<br/><br/>				
			</form>
		
		
		</div>
		
	</body>
</html>