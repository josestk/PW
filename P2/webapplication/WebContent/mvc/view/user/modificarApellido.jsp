<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Cambio de Apellido</title>
	</head>
	<body>
		
		<div id="cuadro">
			<form method="post" action="../../control/user/ContactoController.jsp">
			<p id="titulo1">Cambio de Apellido</p>
			<br/><br/>
			<label id="subtitulo1" for="NuevoApellido">NuevoApellido</label>
			<br/><br/>
			<input type="text" name="NuevoApellido"  id="NuevoApellido"  class="entrada"/>
			<br/><br/>
			<button type="submit" name ="Enviar consulta" id="buttom"  >Enviar </button>
			<br/><br/>	
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>	
			</form>
			
		
		</div>
		
	</body>
</html>