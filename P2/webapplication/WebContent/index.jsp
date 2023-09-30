<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"> <!-- ISO-8859-1? -->
		<title>("Bienvenido a la Aplicacion web")</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
	</head>
	<body>
<!-- Aparecerá en primer lugar el formulario con las opciones de login y de register -->
		<div id="cuadro">
		<!-- Realizamos un formulario para el acceso a nuestra aplicacion -->
			<form action="${pageContext.request.contextPath}/mvc/control/user/logincontrolador.jsp">
			
				<p id="titulo">Bienvenido a WebAppp</p>
				<br/><br/>
				<input type="submit" name="Inicio de sesion" id="buttom" value="Iniciar sesion" />
				<br/><br/>
				
			</form>
			<form action="${pageContext.request.contextPath}/mvc/control/user/registercontrolador.jsp">
				<input type="submit" name="Nuevo usuario" id="buttom" value="Registrarse"  />	
			</form>			
			
		</div>

	</body>
</html>