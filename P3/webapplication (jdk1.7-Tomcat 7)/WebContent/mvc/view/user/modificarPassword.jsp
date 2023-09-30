<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
		<title>Cambio de Password</title>
	</head>
	<body>
		<!-- Formulario para modificar la password del usuario logeado. -->
		
		<div id="cuadro">
			<form  id="form" method="post" action="../../control/user/ContactoController.jsp">
			<p id="titulo1">Cambio de Password</p>
			<br/><br/>
			<label id="subtitulo1" for="password">NuevaPassword</label>
			<br/><br/>
			<input type="Password" id="password" name="password" class="entrada" placeholder="Introduzca la nueva password" onkeydown="checkPassword()"/>
			<span id="text4"></span>
			<br/><br/>
			<button type="submit" name ="Enviar consulta" id="buttom">Enviar </button>
			<br/><br/>	
			<a href="${pageContext.request.contextPath}/mvc/view/user/inicio.jsp">Volver</a>			
			</form>
		
		
		</div>
		<script>
			function checkPassword(){
				
				let form = document.getElementById("form");
				let text2 = document.getElementById("text4");
				let password = document.getElementById("password").value;
				let pattern2 = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
				if(pattern2.test(password)){
					
					form.classList.remove("valid");
					form.classList.add("invalid");
					text2.innerHTML = "Introduzca una password valida";
					text2.style.color = "#ff0000";
					
				}else{
					
					form.classList.add("valid");
					form.classList.remove("invalid");
					text2.innerHTML = "Password valida";
					text2.style.color = "#008000";
				}
			}; 
		</script>
		
	</body>
</html>