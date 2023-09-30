<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.bussiness.contacto.Contacto,es.uco.pw.data.dao.contacto.ContactoDAO" %>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title></title>
	</head>
	<body>
		<!-- Formulario para modificar el nombre del usuario logeado. -->
		
		<div id="cuadro">
			<form id="form" method="post" action="../../control/user/ContactoController.jsp">
			<p id="titulo1">Cambio de nombre</p>
			<br/><br/>
			<label id="subtitulo1" for="nombre">NuevoNombre</label>
			<br/><br/>
			<input type="text" id="nombre" name="nombre"  class="entrada" placeholder="Introduzca un nombre" onkeydown="checkNombre()"/>
			<span id="text2"></span>
			<br/><br/>
			<button type="submit" name ="Enviar consulta" id="buttom">Enviar </button>
			<br/><br/>		
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>		
			</form>
		
		
		</div>
		
		<script>
		function checkNombre(){
			
			let form = document.getElementById("form");
			let nombre = document.getElementById("nombre").value;
			let text = document.getElementById("text2");
			let pattern = /[a-z]{3,4}/;
			
			if(pattern.test(nombre)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Nombre valido";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Introduzca un mombre valido";
				text.style.color = "#ff0000";
			}
		};
		
		</script>
	</body>
</html>