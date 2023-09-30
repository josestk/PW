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
		<!-- Formulario para modificar el apellido del usuario logeado. -->
		<div id="cuadro">
			<form id="form" method="post" action="../../control/user/ContactoController.jsp">
			<p id="titulo1">Cambio de Apellido</p>
			<br/><br/>
			<label id="subtitulo1" for="apellidos">NuevoApellido</label>
			<br/><br/>
			<input type="text" id="apellidos" name="apellidos"  id="NuevoApellido"  class="entrada" placeholder="Introduzca los apellidos del usuario" onkeydown="checkApellidos()"/>
			<span id="text3"></span>
			<br/><br/>
			<button type="submit" name ="Enviar consulta" id="buttom"  >Enviar </button>
			<br/><br/>	
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>	
			</form>
			
		
		</div>
		
		<script>
		function checkApellidos(){
			
			let form = document.getElementById("form");
			let apellidos = document.getElementById("apellidos").value;
			let text = document.getElementById("text3");
			let pattern = /[a-z]{3,4}/;
			
			if(pattern.test(apellidos)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Introduzca unos apellidos validos";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Apellidos validos";
				text.style.color = "#ff0000";
			}
		};
		
		</script>
	</body>
</html>