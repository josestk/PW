<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
    
<%@ page import = "es.uco.pw.bussiness.anuncios.*" %>

<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>
 
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>
<!DOCTYPE html>
<html>
	
	<head>
		<meta charset="UTF-8">
		<title>Pagina de Inicio</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootcss/bootstrap-theme.css">
	</head>
	<body>

		 <nav class="navbar navbar-dark bg-dark">
			<a style="color: white" class="navbar-toggler"> <span class="navbar-toggler-icon"></span> HOME</a>
			
			
			<div class="dropdown">
			<!-- Menu desplegable para los anuncios Flash -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" > AnunciosFlash</a>
				<div class="dropdown-menu text-center">
						<form method="get" action="anuncioFlash">
					<input id="selector2" name="selector2" type="hidden" value=0>
					<a><button name="CrearAnuncioFlash" type="submit" id="button1" onclick="document.getElementById('selector2').value='1'"> Crear</button></a>
					<a><button name="EliminarAnuncioFlash" type="submit" id="button1" onclick="document.getElementById('selector2').value='2'"> Eliminar</button></a>
					<a><button name="ObtenerAnuncioFlash" type="submit" id="button1" onclick="document.getElementById('selector2').value='3'"> Obtener</button></a>
					<a><button name="ModificarAnuncioFlash" type="submit" id="button1" onclick="document.getElementById('selector2').value='4'"> Modificar</button></a>
					<a><button name="PublicarAnuncioFlash" type="submit" id="button1" onclick="document.getElementById('selector2').value='5'"> Publicar</button></a>
					</form>
				</div>
		
			</div>
			
			
			
			
			<div class="dropdown">
			<!-- Menu desplegable para los anuncios Generales -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosGenerales</a>
				<div class="dropdown-menu text-center">
					<form method="get" action="anuncioGeneral">
					<input id="selector" name="selector" type="hidden" value=0>
				<a>	<button name="CrearAnuncioGeneral" type="submit" id="button1" onclick="document.getElementById('selector').value='1'"> Crear</button></a>
				<a>	<button name="EliminarAnuncioGeneral" type="submit" id="button1" onclick="document.getElementById('selector').value='2'"> Eliminar</button></a>
				<a>	<button name="ObtenerAnuncioGeneral" type="submit" id="button1" onclick="document.getElementById('selector').value='3'"> Obtener</button></a>
				<a>	<button name="ModificarAnuncioGeneral" type="submit" id="button1" onclick="document.getElementById('selector').value='4'"> Modificar</button></a>
					<a>	<button name="PublicarAnuncioGeneral" type="submit" id="button1" onclick="document.getElementById('selector').value='5'"> Publicar</button></a>
					</form>
				
				</div>
		
			</div>
			
			
			
			
			
			<div class="dropdown">
			<!-- Menu desplegable para los anuncios Individualizados -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosIndividualizados</a>
				<div class="dropdown-menu text-center">
					<form method="get" action="anuncioIndividualizado">
					<input id="selector3" name="selector3" type="hidden" value=0>
					<a><button name="CrearAnunciosIndividualizados" type="submit" id="button1" onclick="document.getElementById('selector3').value='1'"> Crear</button></a>
					<a><button name="EliminarAnunciosIndividualizados" type="submit" id="button1" onclick="document.getElementById('selector3').value='2'"> Eliminar</button></a>
					<a><button name="ObtenerAnunciosIndividualizados" type="submit" id="button1" onclick="document.getElementById('selector3').value='3'"> Obtener</button></a>
					<a><button name="ModificarAnunciosIndividualizados" type="submit" id="button1" onclick="document.getElementById('selector3').value='4'"> Modificar</button></a>
					<a><button name="PublicarAnunciosIndividualizados" type="submit" id="button1" onclick="document.getElementById('selector3').value='5'"> Publicar</button></a>
					</form>
				</div>
		
			</div> 
			
			
			
			
			
			<div class="dropdown">
			<!-- Menu desplegable para los anuncios Tematicos -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> AnunciosTematicos</a>
				<div class="dropdown-menu text-center">
					<form method="get" action="anuncioTematico">
					<input id="selector1" name="selector1" type="hidden" value=0>
					<a><button name="CrearAnunciosTematicos" type="submit" id="button1" onclick="document.getElementById('selector1').value='1'"> Crear</button></a>
					<a><button name="EliminarAnunciosTematicos" type="submit" id="button1" onclick="document.getElementById('selector1').value='2'"> Eliminar</button></a>
					<a><button name="ObtenerAnunciosTematicos" type="submit" id="button1" onclick="document.getElementById('selector1').value='3'"> Obtener</button></a>
					<a><button name="ModificarAnunciosTematicos" type="submit" id="button1" onclick="document.getElementById('selector1').value='4'"> Modificar</button></a>
					<a><button name="PublicarAnunciosTematicos" type="submit" id="button1" onclick="document.getElementById('selector1').value='5'"> Publicar</button></a>
					</form>
				</div>
		
			</div> 
			
			<div class="dropdown">
			<!-- Menu desplegable para contacto -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="apartado" style="color:#007bff"> Contacto</a>
				<div class="dropdown-menu text-center">
					<a href="../../view/user/modificarNombre.jsp"><button type="submit"   id="button1"> MNombre</button></a>
					<a href="../../view/user/modificarApellido.jsp"><button type="submit" id="button1"> MApellidos</button></a>
					<a href="../../view/user/modificarPassword.jsp"><button type="submit" id="button1"> MPassword</button></a>
					<a href="../../view/user/modificarFecha.jsp"><button type="submit"    id="button1"> MFechaNacimiento</button></a>
					<a href="../../view/user/modificarInteres.jsp"><button type="submit"  id="button1"> MInteres(es)</button></a>
				</div>
		
			</div>
			
			<div class="dropdown">
			<!-- Menu desplegable para los filtrados -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="apartado" style="color:#007bff">Filtrado</a>
				<div class="dropdown-menu text-center">
					<form method="get" action="filtrado">
					<input type="hidden" id="Filtros" name="Filtros" value='0'>
					<button id="button1" onclick="document.getElementById('Filtros').value='1'">Generales</button>
					<button id="button1" onclick="document.getElementById('Filtros').value='2'">Flash</button>
					<button id="button1" onclick="document.getElementById('Filtros').value='3'">Individualizados</button>
					<button id="button1" onclick="document.getElementById('Filtros').value='4'">Tematicos</button>
					<button id="button1" onclick="document.getElementById('Filtros').value='5'">Intereses</button>
					<button id="button1" onclick="document.getElementById('Filtros').value='6'">FechaPublicacion</button>
					<button id="button1" onclick="document.getElementById('Filtros').value='7'">Todo</button>
					
					
					</form>
				</div>
			</div>
			
			
			<div class="dropdown">
			<!-- Menu desplegable para los Listados -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="apartado" style="color:#007bff">Listado</a>
				<div class="dropdown-menu text-center">
					<form method="get" action="listado">
					<input type="hidden" id="Listado" name="Listado" value='0'>
					<button id="button1" onclick="document.getElementById('Listado').value='1'">Editados</button>
					<button id="button1" onclick="document.getElementById('Listado').value='2'">Espera</button>
					<button id="button1" onclick="document.getElementById('Listado').value='3'">Publicados</button>
					<button id="button1" onclick="document.getElementById('Listado').value='4'">Archivados</button>			
					</form>
				</div>
			</div>
			
			<div class="dropdown">
			<!-- Menu desplegable para los abandonar la aplicacion -->
				<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="apartado" style="color:#007bff">Cerrar sesion</a>
				<div class="dropdown-menu text-center">
					<a><img src= /webapplication/img/usuario.png height =80 width=80></a><br>
					<a href="${pageContext.request.contextPath}/acercaDe.html">About Us</a>
					<a><%=customerBean.getEmail() %></a>
					<br/>
					<div class="dropdown-divider"></div>
					<a href="${pageContext.request.contextPath}/mvc/view/user/desconectar.jsp" class="dropdown-item">Salir</a> 
				</div>
			</div>
			
		
		
		</nav>
		<!-- Barra para realizar la búsqueda-->
		<form method="get" action="busqueda">	
			<div class="field" id="searchform">
		 		 <input type="text" name = "clave" placeholder="Introduce aquí la búsqueda" />
		 		 <button type="submit" id="search">Buscar</button>	
	 		</div>
		</form>
		 	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	<%
	int filtrado = 0;
	int nuevo = 0;
	int listar = 0;
	
	try{
		if(request.getAttribute("filtrados")!=null){
			 filtrado = Integer.parseInt(String.valueOf(request.getAttribute("filtrados")));
		}
		if(request.getAttribute("inicio")!=null){
			 nuevo = Integer.parseInt(String.valueOf(request.getAttribute("inicio")));
		}
		if(request.getAttribute("listados")!=null){
			 listar = Integer.parseInt(String.valueOf(request.getAttribute("listados")));
		}
	}catch(Exception e){
		
		e.printStackTrace();
	}
	if((filtrado == 1) && (nuevo == 0)){
		ArrayList<AnuncioGeneral> copia = new ArrayList<AnuncioGeneral>();
		if(request.getAttribute("anuncios")!=null){
			
			try{
			@SuppressWarnings("unchecked")	
			ArrayList<AnuncioGeneral> anunciosgenerales = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios");
			copia = anunciosgenerales;
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		
	%>
	<!-- Tabla para anuncios Generales -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Acciones</td>
			</tr>
		
				
			
				<% for(int i=0; i<copia.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=copia.get(i).getId()%></td>
				<td class="text-center"><%=copia.get(i).getTitulo()%></td>
				<td class="text-center"><%=copia.get(i).getCuerpo()%></td>
				<td class="text-center"><%=copia.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=copia.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=copia.get(i).getTipo()%></td>
				<td class="text-center"><%=copia.get(i).getFase()%></td>
				<td class="text-center"><%=copia.get(i).getFechaPublicacion()%></td>
				<td class="text-center">
				<form method="get" action="anuncioGeneral">
					<input type="hidden" id="accion" name="accion" value=0>
					<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
					<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
				</form>
				</td>
				</tr>
				
			<%} %>							
	
	
	
	</table>
	</div>
	<%} 
	else if((filtrado == 2) && (nuevo == 0)){
		ArrayList<AnuncioFlash> copia = new ArrayList<AnuncioFlash>();
		if(request.getAttribute("anuncios")!=null){
			
			try{
			@SuppressWarnings("unchecked")	
			ArrayList<AnuncioFlash> listado = (ArrayList<AnuncioFlash>)request.getAttribute("anuncios");
			copia = listado;
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		
		
	%>
	<div class="container">
	<!-- Tabla para anuncios Flash -->
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_inicio</td>
				<td class="text-center">Fecha_final</td>
				<td class="text-center">Acciones</td>
			</tr>
		
				
			
				<% for(int i=0; i<copia.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=copia.get(i).getId()%></td>
				<td class="text-center"><%=copia.get(i).getTitulo()%></td>
				<td class="text-center"><%=copia.get(i).getCuerpo()%></td>
				<td class="text-center"><%=copia.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=copia.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=copia.get(i).getTipo()%></td>
				<td class="text-center"><%=copia.get(i).getFase()%></td>
				<td class="text-center"><%=copia.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=copia.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=copia.get(i).getFechaFinal()%></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>							
	
	
	
	</table>
	</div>
	
	<%} else if((filtrado == 3) && (nuevo == 0)){
		ArrayList<AnuncioIndividualizado> copia = new ArrayList<AnuncioIndividualizado>();
		if(request.getAttribute("anuncios")!=null){
			
			try{
			@SuppressWarnings("unchecked")	
			ArrayList<AnuncioIndividualizado> listado = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios");
			copia = listado;
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
	
	%>
	<div class="container">
	<!-- Tabla para anuncios Individualizados -->
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Acciones</td>
			</tr>
		
				
			
				<% for(int i=0; i<copia.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=copia.get(i).getId()%></td>
				<td class="text-center"><%=copia.get(i).getTitulo()%></td>
				<td class="text-center"><%=copia.get(i).getCuerpo()%></td>
				<td class="text-center"><%=copia.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=copia.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=copia.get(i).getTipo()%></td>
				<td class="text-center"><%=copia.get(i).getFase()%></td>
				<td class="text-center"><%=copia.get(i).getFechaPublicacion()%></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>							
	
	
	
	</table>
	</div>
	<%} else if((filtrado == 4) && (nuevo == 0)){
		ArrayList<AnuncioTematico> copia = new ArrayList<AnuncioTematico>();
		if(request.getAttribute("anuncios")!=null){
			
			try{
			@SuppressWarnings("unchecked")	
			ArrayList<AnuncioTematico> listado = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios");
			copia = listado;
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
	%>
	<!-- Tabla para anuncios Tematicos -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
				
			
				<% for(int i=0; i<copia.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=copia.get(i).getId()%></td>
				<td class="text-center"><%=copia.get(i).getTitulo()%></td>
				<td class="text-center"><%=copia.get(i).getCuerpo()%></td>
				<td class="text-center"><%=copia.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=copia.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=copia.get(i).getTipo()%></td>
				<td class="text-center"><%=copia.get(i).getFase()%></td>
				<td class="text-center"><%=copia.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=copia.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>							
	
	
	
	</table>
	</div>
	
	<%} else if((filtrado == 5) && (nuevo == 0)){
		ArrayList<AnuncioTematico> copia = new ArrayList<AnuncioTematico>();
		if(request.getAttribute("anuncios")!=null){
			
			try{
			@SuppressWarnings("unchecked")	
			ArrayList<AnuncioTematico> listado = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios");
			copia = listado;
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
	%>
	<!-- Tabla para filtrado segun intereses del usuario -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
				
			
				<% for(int i=0; i<copia.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=copia.get(i).getId()%></td>
				<td class="text-center"><%=copia.get(i).getTitulo()%></td>
				<td class="text-center"><%=copia.get(i).getCuerpo()%></td>
				<td class="text-center"><%=copia.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=copia.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=copia.get(i).getTipo()%></td>
				<td class="text-center"><%=copia.get(i).getFase()%></td>
				<td class="text-center"><%=copia.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=copia.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>							
	
	
	
	</table>
	</div>
	<%}else if((filtrado == 0) && (nuevo == 1)) {
		ArrayList<AnuncioGeneral> anunciosGenrUsuario1 = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario1 = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario1 = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario1 = new ArrayList<AnuncioIndividualizado>();
		
		try{
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios1");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioFlash> anunciosFlashUsuario = 	(ArrayList<AnuncioFlash>)request.getAttribute("anuncios2");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioTematico> anunciosTemUsuario = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios3");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios4");
		
		anunciosGenrUsuario1 = anunciosGenrUsuario;
		anunciosFlashUsuario1 = anunciosFlashUsuario;
		anunciosTemUsuario1 = anunciosTemUsuario;
		anunciosIndiUsuario1 = anunciosIndiUsuario;
		
		}catch(Exception e){
		e.printStackTrace();
		}
	%>
	<!-- Tabla para mostrar el tablon entero -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_Inicio</td>
				<td class="text-center">Fecha_Final</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
					
			
			<% for(int i=0; i<anunciosGenrUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosGenrUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioGeneral">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>				
			
					<% for(int i=0; i<anunciosIndiUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosIndiUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>	
			
				<% for(int i=0; i<anunciosFlashUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosFlashUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaFinal()%></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>
			
			<% for(int i=0; i<anunciosTemUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosTemUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>					
	
	
	
	</table>
	</div>
	
	<%}else if((filtrado == 6) && (nuevo == 0)){
		ArrayList<AnuncioGeneral> anunciosGenrUsuario1 = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario1 = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario1 = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario1 = new ArrayList<AnuncioIndividualizado>();
		
		try{
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios1");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioFlash> anunciosFlashUsuario = 	(ArrayList<AnuncioFlash>)request.getAttribute("anuncios2");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioTematico> anunciosTemUsuario = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios3");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios4");
		
		anunciosGenrUsuario1 = anunciosGenrUsuario;
		anunciosFlashUsuario1 = anunciosFlashUsuario;
		anunciosTemUsuario1 = anunciosTemUsuario;
		anunciosIndiUsuario1 = anunciosIndiUsuario;
		
		}catch(Exception e){
		e.printStackTrace();
		}
	%>
	
	<!-- Tabla para mostrar los anuncios por una fecha de inicio - final indicada-->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_Inicio</td>
				<td class="text-center">Fecha_Final</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
					
			
			<% for(int i=0; i<anunciosGenrUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosGenrUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioGeneral">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>				
			
					<% for(int i=0; i<anunciosIndiUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosIndiUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>	
			
				<% for(int i=0; i<anunciosFlashUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosFlashUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaFinal()%></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>
			
			<% for(int i=0; i<anunciosTemUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosTemUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>					
	
	
	
	</table>
	</div>
	
	<%}else if((listar == 1) && (filtrado == 0) && (nuevo == 0)){
		ArrayList<AnuncioGeneral> anunciosGenrUsuario1 = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario1 = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario1 = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario1 = new ArrayList<AnuncioIndividualizado>();
		try{
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios1");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioFlash> anunciosFlashUsuario = 	(ArrayList<AnuncioFlash>)request.getAttribute("anuncios2");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioTematico> anunciosTemUsuario = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios3");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios4");
		
		anunciosGenrUsuario1 = anunciosGenrUsuario;
		anunciosFlashUsuario1 = anunciosFlashUsuario;
		anunciosTemUsuario1 = anunciosTemUsuario;
		anunciosIndiUsuario1 = anunciosIndiUsuario;
		
		}catch(Exception e){
		e.printStackTrace();
		}
	%>
	
	<!-- Tabla para mostrar anuncios editados -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_Inicio</td>
				<td class="text-center">Fecha_Final</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
					
			
			<% for(int i=0; i<anunciosGenrUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosGenrUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioGeneral">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>				
			
					<% for(int i=0; i<anunciosIndiUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosIndiUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>	
			
				<% for(int i=0; i<anunciosFlashUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosFlashUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaFinal()%></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>
			
			<% for(int i=0; i<anunciosTemUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosTemUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>					
	
	
	
	</table>
	</div>
	<%}else if((listar == 2) && (filtrado == 0) && (nuevo == 0)){
		ArrayList<AnuncioGeneral> anunciosGenrUsuario1 = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario1 = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario1 = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario1 = new ArrayList<AnuncioIndividualizado>();
		try{
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios1");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioFlash> anunciosFlashUsuario = 	(ArrayList<AnuncioFlash>)request.getAttribute("anuncios2");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioTematico> anunciosTemUsuario = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios3");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios4");
		
		anunciosGenrUsuario1 = anunciosGenrUsuario;
		anunciosFlashUsuario1 = anunciosFlashUsuario;
		anunciosTemUsuario1 = anunciosTemUsuario;
		anunciosIndiUsuario1 = anunciosIndiUsuario;
		
		}catch(Exception e){
		e.printStackTrace();
		}
	
	%>
	<!-- Tabla para mostrar anuncios en espera -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_Inicio</td>
				<td class="text-center">Fecha_Final</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
					
			
			<% for(int i=0; i<anunciosGenrUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosGenrUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioGeneral">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>				
			
					<% for(int i=0; i<anunciosIndiUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosIndiUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>	
			
				<% for(int i=0; i<anunciosFlashUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosFlashUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaFinal()%></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>
			
			<% for(int i=0; i<anunciosTemUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosTemUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>					
	
	
	
	</table>
	</div>
	<%}else if((listar == 3) && (filtrado == 0) && (nuevo == 0)){ 
		ArrayList<AnuncioGeneral> anunciosGenrUsuario1 = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario1 = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario1 = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario1 = new ArrayList<AnuncioIndividualizado>();
		try{
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios1");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioFlash> anunciosFlashUsuario = 	(ArrayList<AnuncioFlash>)request.getAttribute("anuncios2");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioTematico> anunciosTemUsuario = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios3");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios4");
		
		anunciosGenrUsuario1 = anunciosGenrUsuario;
		anunciosFlashUsuario1 = anunciosFlashUsuario;
		anunciosTemUsuario1 = anunciosTemUsuario;
		anunciosIndiUsuario1 = anunciosIndiUsuario;
		
		}catch(Exception e){
		e.printStackTrace();
		}
	%>
	
	<!-- Tabla para mostrar anuncios publicados -->
	<div class="container">
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_Inicio</td>
				<td class="text-center">Fecha_Final</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
					
			
			<% for(int i=0; i<anunciosGenrUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosGenrUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioGeneral">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>				
			
					<% for(int i=0; i<anunciosIndiUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosIndiUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>	
			
				<% for(int i=0; i<anunciosFlashUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosFlashUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaFinal()%></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>
			
			<% for(int i=0; i<anunciosTemUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosTemUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=1" class="btn btn-warning btn-sm">Editar</button>
						<button onclick = "this.parentNode.children[0].value=2" class="btn btn-danger btn-sm">Eliminar</button>
					</form>
				</td>
				</tr>
				
			<%} %>					
	
	
	
	</table>
	</div>
	<%}else if((listar == 4) && (filtrado == 0) && (nuevo == 0)){
		ArrayList<AnuncioGeneral> anunciosGenrUsuario1 = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario1 = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario1 = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario1 = new ArrayList<AnuncioIndividualizado>();
		try{
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = (ArrayList<AnuncioGeneral>)request.getAttribute("anuncios1");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioFlash> anunciosFlashUsuario = 	(ArrayList<AnuncioFlash>)request.getAttribute("anuncios2");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioTematico> anunciosTemUsuario = (ArrayList<AnuncioTematico>)request.getAttribute("anuncios3");
		@SuppressWarnings("unchecked")	
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = (ArrayList<AnuncioIndividualizado>)request.getAttribute("anuncios4");
		
		anunciosGenrUsuario1 = anunciosGenrUsuario;
		anunciosFlashUsuario1 = anunciosFlashUsuario;
		anunciosTemUsuario1 = anunciosTemUsuario;
		anunciosIndiUsuario1 = anunciosIndiUsuario;
		
		}catch(Exception e){
		e.printStackTrace();
		}
	%>
	<div class="container">
	<!-- Tabla para mostrar anuncios archivados -->
	<hr>
	<table class="table table-bordered">
		
			<tr>
				<td class="text-center">ID</td>
				<td class="text-center">Titulo</td>				
				<td class="text-center">Cuerpo</td>
				<td class="text-center">Propietario</td>
				<td class="text-center">Destinatarios</td>
				<td class="text-center">Tipo</td>
				<td class="text-center">Fase</td>
				<td class="text-center">Fecha_Publicacion</td>
				<td class="text-center">Fecha_Inicio</td>
				<td class="text-center">Fecha_Final</td>
				<td class="text-center">Intereses</td>
				
				<td class="text-center">Acciones</td>
			</tr>
		
					
			
			<% for(int i=0; i<anunciosGenrUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosGenrUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosGenrUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioGeneral">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=3" class="btn btn-warning btn-sm">Eliminar</button>
						<button onclick = "this.parentNode.children[0].value=4" class="btn btn-danger btn-sm">Recuperar</button>						
					</form>
				</td>
				</tr>
				
			<%} %>				
			
					<% for(int i=0; i<anunciosIndiUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosIndiUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosIndiUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioIndividualizado">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=3" class="btn btn-warning btn-sm">Eliminar</button>
						<button onclick = "this.parentNode.children[0].value=4" class="btn btn-danger btn-sm">Recuperar</button>
						
					</form>
				</td>
				</tr>
				
			<%} %>	
			
				<% for(int i=0; i<anunciosFlashUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosFlashUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaPublicacion()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaInicio()%></td>
				<td class="text-center"><%=anunciosFlashUsuario1.get(i).getFechaFinal()%></td>
				<td></td>
				<td class="text-center">
					<form method="get" action="anuncioFlash">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=3" class="btn btn-warning btn-sm">Eliminar</button>
						<button onclick = "this.parentNode.children[0].value=4" class="btn btn-danger btn-sm">Recuperar</button>						
					</form>
				</td>
				</tr>
				
			<%} %>
			
			<% for(int i=0; i<anunciosTemUsuario1.size(); i++){%>
				<tr> 
				
			    <td class="text-center"><%=anunciosTemUsuario1.get(i).getId()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTitulo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getCuerpo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getPropietario().getEmail()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getDestinatarios() %></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getTipo()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFase()%></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getFechaPublicacion()%></td>
				<td></td>
				<td></td>
				<td class="text-center"><%=anunciosTemUsuario1.get(i).getIntereses()%></td>
				<td class="text-center">
					<form method="get" action="anuncioTematico">
						<input type="hidden" id="accion" name="accion" value=0>
						<button onclick = "this.parentNode.children[0].value=3" class="btn btn-warning btn-sm">Eliminar</button>
						<button onclick = "this.parentNode.children[0].value=4" class="btn btn-danger btn-sm">Recuperar</button>
						
					</form>
				</td>
				</tr>
				
			<%} %>					
	
	
	
	</table>
	</div>
	<%} %>
	</body>
	

</html>