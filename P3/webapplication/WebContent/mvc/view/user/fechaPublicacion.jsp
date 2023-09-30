<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Fecha Publicacion</title>
	</head>
	<body>
		<!-- Formulario para filtrar entre un intervalo de fechas.  -->
		<div id="cuadro">
			<form method="post" action="filtrado">
			<p id="titulo2">Fecha Publicacion</p>
			<br/><br/>
			<label id="subtitulo2" for="FechaInicio">FechaInicio</label>
			<br/><br/>
			<input type="text" name="FechaInicio"  id="FechaInicio"  class="entrada"/>
			<br/><br/>
			<label id="subtitulo2" for="FechaFinal">FechaFinal</label>
			<br/><br/>
			<input type="text" name="FechaFinal"  id="FechaFinal"  class="entrada"/>
			<button type="submit" name ="Enviar consulta" id="buttom">Enviar </button>
			<br/><br/>	
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>			
			</form>
		
		
		</div>
		
	</body>
</html>