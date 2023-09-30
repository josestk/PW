<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.contacto.Contacto,es.uco.pw.data.dao.contacto.ContactoDAO" %>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Desconexion</title>
	</head>
	<body>
		
		<div id="cuadro">
			<form method="post" action="../../control/user/desconexionController.jsp">
			<p id="titulo1">Cierre de sesion</p>
			<br/><br/>
			<button name="Si"  id="Si" class="entrada">Si</button>
			<br/><br/>
			<button name="No"  id="No" class="entrada">No</button>
			<br/><br/>				
			</form>
		
		
		</div>
		
	</body>
</html>