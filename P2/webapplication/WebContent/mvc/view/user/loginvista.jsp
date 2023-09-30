<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"> <!-- ISO-8859-1? -->
		<title><%  out.println("Bienvenido a la Aplicacion web"); %></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
	</head>
	<body>	
	
	
<%	
String nextPage = "../../control/user/logincontrolador.jsp";
String mensaje = request.getParameter("mensaje");
int nintentos = 0;
try{
	nintentos = Integer.parseInt(request.getParameter("nintentos"));
}catch(Exception e){
	response.sendRedirect("../../control/user/logincontrolador.jsp");
	}

if (nintentos>3)
{
	response.sendRedirect("http://www.uco.es/");
}else{
%>
<!-- Aparecerá en primer lugar el formulario con las opciones de login y de register -->
		
		<div id="cuadro">
		<!-- Realizamos un formulario para el acceso a nuestra aplicacion -->
			
			<form  method="post" action="../../control/user/logincontrolador.jsp">
				<p id="titulo">INICIO DE SESION</p>
				<hr>
				<br/><br/>
				<label id="subtitulo1" for="Usuario">USUARIO</label>
				<br/><br/>
				<input type="text" name="Usuario" class="entrada" />
				<br/><br/>
				<label id="subtitulo1" for="Password">PASSWORD</label>
				<br/><br/>
				<input type="password" name="Password" class="entrada2"/>
				<br/><br/>
				<button type="submit" name="Boton_Ingreso"  id="buttom">Ingresar</button>
				<input type="hidden" name="nintentos"  value="<%=nintentos%>" /> 
				
				<%
					if(nintentos != 0){
						int nrestantes = 3 - nintentos;
						%>
						
						<script>alert("Usuario o password incorrectos, intentos restantes: "+<%=nrestantes%>)</script>
						
						<%
					}
				%>
			 </form>	
			<br/>
			<a href="/webapplication/index.jsp"> Volver</a>	
										
		</div>
<%
}
%>	
			
	</body>
</html>