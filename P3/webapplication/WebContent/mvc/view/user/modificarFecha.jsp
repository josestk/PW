<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Cambio de FechaNacimiento</title>
	</head>
	<body>
		<!-- Formulario para modificar la fecha de nacimiento del usuario logeado. -->
		
		<div id="cuadro">
			<form id="form" method="post" action="../../control/user/ContactoController.jsp">
			<p id="titulo2">Cambio FechaNacimiento</p>
			<br/><br/>
			<label id="subtitulo2" for="fechaNacimiento">NuevaFechaNacimiento</label>
			<br/><br/>
			<input type="text" name="fechaNacimiento"  id="fechaNacimiento"  class="entrada" placeholder="Introduzca una fecha de Nacimiento" onkeydown="checkFecha()"/>
			<span id="text5"></span>
			<br/><br/>
			<button type="submit" name ="Enviar consulta" id="buttom">Enviar </button>
			<br/><br/>	
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>			
			</form>
		
		
		</div>
		
		<script>
		function checkFecha(){
			
			let form = document.getElementById("form");
			let text2 = document.getElementById("text5");
			let fecha = document.getElementById("fechaNacimiento").value;
			let pattern = /^(?:3[01]|[12][0-9]|0?[1-9])([\-/.])(0?[1-9]|1[1-2])\1\d{4}$/;
			if(pattern.test(fecha)){
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text2.innerHTML = "Introduzca una fecha de nacimiento valida";
				text2.style.color = "#ff0000";
				
			}else{
				
				form.classList.add("valid");
				form.classList.remove("invalid");
				text2.innerHTML = "Fecha de nacimiento valida";
				text2.style.color = "#008000";
			}
			
		};
		
		</script>
	</body>
</html>