<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp"%>
 
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>
<!DOCTYPE html>
<html>
	<style>
	#button{
	
	width:10em;
	height: 2em;
	border-radius: 0.5em;
	background: blue;
	color: #ffffff;
	font-size: 1em;
	}
	
	</style>
	
	<head>
		<meta charset="UTF-8">
		<title>Pagina de Inicio</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
	</head>
	<body>

		 <nav class="navbar navbar-dark bg-dark">
			<a style="color: white" class="navbar-toggler"> <span class="navbar-toggler-icon"></span> HOME</a>
			<!--<div class="dropdown">
			
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosFlash</a>
				<div class="dropdown-menu text-center">
					<a><button name="CrearAnuncioFlash" type="submit" id="button1"> CrearAnuncioFlash</button></a>
					<a><button name="EliminarAnuncioFlash" type="submit" id="button1"> EliminarAnuncioFlash</button></a>
					<a><button name="InsertarAnuncioFlash" type="submit" id="button1"> InsertarAnuncioFlash</button></a>
					<a><button name="ModificarAnuncioFlash" type="submit" id="button1"> ModificarAnuncioFlash</button></a>
				<div class="dropdown-divider"></div>
				</div>
		
			</div>
			
			<div class="dropdown">
			
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosGenerales</a>
				<div class="dropdown-menu text-center">
					<a><button name="CrearAnuncioGeneral" type="submit" id="button1"> CrearAnuncioGeneral</button></a>
					<a><button name="EliminarAnuncioGeneral" type="submit" id="button1"> EliminarAnuncioGeneral</button></a>
					<a><button name="InsertarAnuncioGeneral" type="submit" id="button1"> InsertarAnuncioGeneral</button></a>
					<a><button name="ModificarAnuncioGeneral" type="submit" id="button1"> ModificarAnuncioGeneral</button></a>
				<div class="dropdown-divider"></div>
				</div>
		
			</div>
			
			<div class="dropdown">
			
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosIndividualizados</a>
				<div class="dropdown-menu text-center">
					<a><button name="CrearAnunciosIndividualizado" type="submit" id="button1"> CrearAnunciosIndividualizado</button></a>
					<a><button name="EliminarAnunciosIndividualizado" type="submit" id="button1"> EliminarAnunciosIndividualizado</button></a>
					<a><button name="InsertarAnunciosIndividualizado" type="submit" id="button1"> InsertarAnunciosIndividualizado</button></a>
					<a><button name="ModificarAnunciosIndividualizado" type="submit" id="button1"> ModificarAnunciosIndividualizado</button></a>
				<div class="dropdown-divider"></div>
				</div>
		
			</div>
			
			<div class="dropdown">
			
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosTematicos</a>
				<div class="dropdown-menu text-center">
					<a><button name="CrearAnunciosTematicos" type="submit" id="button1"> CrearAnunciosTematicos</button></a>
					<a><button name="EliminarAnunciosTematicos" type="submit" id="button1"> EliminarAnunciosTematicos</button></a>
					<a><button name="InsertarAnunciosTematicos" type="submit" id="button1"> InsertarAnunciosTematicos</button></a>
					<a><button name="ModificarAnunciosTematicos" type="submit" id="button1"> ModificarAnunciosTematicos</button></a>
				<div class="dropdown-divider"></div>
				</div>
		
			</div>  -->
			
			<div class="dropdown">
			
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="apartado"> Contacto</a>
				<div class="dropdown-menu text-center">
					<a href="../../view/user/modificarNombre.jsp"><button type="submit"   id="button1"> modificar Nombre</button></a>
					<a href="../../view/user/modificarApellido.jsp"><button type="submit" id="button1"> modificar Apellidos</button></a>
					<a href="../../view/user/modificarPassword.jsp"><button type="submit" id="button1"> modificar Password</button></a>
					<a href="../../view/user/modificarFecha.jsp"><button type="submit"    id="button1"> modificar FechaNacimiento</button></a>
					<a href="../../view/user/modificarInteres.jsp"><button type="submit"  id="button1"> modificar Interes(es)</button></a>
				<div class="dropdown-divider"></div>
				</div>
		
			</div>
			<div class="dropdown">
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="apartado">Cerrar sesion</a>
				<div class="dropdown-menu text-center">
					<a><img src= /webapplication/img/usuario.png height =80 width=80></a><br>
					<a href="${pageContext.request.contextPath}/acercaDe.html">About Us</a>
					<a><%=customerBean.getEmail() %></a>
					<br/>
					<div class="dropdown-divider"></div>
					<a href="../../view/user/Desconexion.jsp" class="dropdown-item">Salir</a> 
				</div>
			</div>
		
		</nav>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
		
	
	</body>
</html>