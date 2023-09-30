<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
	</head>
	<body>
	
<%
int registro = 0;
try{
	
	registro = Integer.parseInt(request.getParameter("registrado"));
	
}catch(Exception e){
	
	response.sendRedirect("../../control/user/registercontrolador.jsp");
}
	
%>
		<div id="cuadro">
		<!-- Realizamos un formulario para el acceso a nuestra aplicacion -->
			<form  method="post" action="../../control/user/registercontrolador.jsp">
				
				<p id="titulo">Registro de nuevo usuario</p>
				<hr>
				<br/><br/>
				<label id="subtitulo3" for="Email">Email</label>
				<br/><br/>
				<input type="text" name="Email" class="entrada" />
				<br/><br/>
				<label id="subtitulo3" for="Nombre">Nombre</label>
				<br/><br/>
				<input type="text" name="Nombre" class="entrada"/>
				<br/><br/>
				<label id="subtitulo3" for="Apellidos">Apellidos</label>
				<br/><br/>
				<input name="Apellidos" type="text" class="entrada"/>
				<br/><br/>
				<label id="subtitulo3" for="Password">Password</label>
				<br/><br/>
				<input type="password" name="Password" class="entrada2"/>
				<br/><br/>
				<label id="subtitulo3" for="FechaNacimiento">FechaNacimiento</label>
				<br/><br/>
				<input type="text" name="FechaNacimiento"  class="entrada2">
				<br/><br/>
				<label id="subtitulo3" for="Intereses">Intereses</label>
				<br/><br/>
				<input type="text" name="Intereses"  class="entrada2"/>
				<br/><br/>
				<button type="submit" name="Boton_Ingreso" id="buttom">Registrar</button>
				<%
					if(registro != 0){
						
						%>
						
						<script> alert("El Email introducido ya existe")</script>
						
						<%
					}
				
				%>
				 
			 </form>	
			<br/>	
			<a href="/webapplication/index.jsp"> Volver</a>										
		</div>
	
	</body>
</html>