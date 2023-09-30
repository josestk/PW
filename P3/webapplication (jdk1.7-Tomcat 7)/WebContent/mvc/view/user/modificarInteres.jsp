<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Cambio de Interes</title>
	</head>
	<body>
	
		<!-- Formulario para modificar los intereses del usuario logeado. -->
		
		<div id="cuadro">
			<form id="form" method="post" action="../../control/user/ContactoController.jsp">
			<p id="titulo1">Cambio de Interes</p>
			<br/><br/>
			<label id="subtitulo1" for="interes">Nuevo interes(es)</label>
			<br/><br/>
			<input type="text" id="interes" name="interes" class="entrada" placeholder="Introduzca los intereses del usuario" onkeydown="checkIntereses()"/>
			<span id="text6"></span>
			<br/><br/>
			<button type="submit" name ="Enviar consulta" id="buttom">Enviar </button>
			<br/><br/>		
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>		
			</form>
		
		
		</div>
		
		<script>
		function checkIntereses(){
			
			let form = document.getElementById("form");
			let interes = document.getElementById("interes").value;
			let text = document.getElementById("text6");
			let pattern = /[a-z]{3,4}/;
			
			if(pattern.test(interes)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Intereses validos";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Introduzca unos intereses validos";
				text.style.color = "#ff0000";
			}
			
		};
		
		
		</script>
	</body>
</html>