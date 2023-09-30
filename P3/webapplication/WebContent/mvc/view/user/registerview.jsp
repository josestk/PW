<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page errorPage="{pageContext.request.contextPath}/include/errorpage.jsp" %>
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
		<!-- Realizamos un formulario para el registro de un nuevo usuario -->
			<form  id="form" method="post" action="../../control/user/registercontrolador.jsp">
				
				<p id="titulo">Registro de nuevo usuario</p>
				<hr>
				<br/><br/>
				<label id="subtitulo3" for="email">Email</label>
				<br/><br/>
				<input type="text" id="email" name="email" class="entrada" placeholder="Introduzca su email" onkeydown="validation()"/>
				<span id="text"></span>
				<br/><br/>
				<label id="subtitulo3" for="nombre">Nombre</label>
				<br/><br/>
				<input type="text" id="nombre" name="nombre" class="entrada" placeholder="Introduzca nombre del usuario" onkeydown="checkNombre()"/>
				<span id="text2"></span>
				<br/><br/>
				<label id="subtitulo3" for="apellidos">Apellidos</label>
				<br/><br/>
				<input type="text" id="apellidos" name="apellidos"  class="entrada" placeholder="Introduzca los del apellidos" onkeydown="checkApellidos()"/>
				<span id="text3"></span>
				<br/><br/>
				<label id="subtitulo3" for="password">Password</label>
				<br/><br/>
				<input type="password" id="password" name="password" class="entrada2" placeholder="Introduzca el password" onkeydown="checkPassword()"/>
				<span id="text4"></span>
				<br/><br/>
				<label id="subtitulo3" for="fechaNacimiento">FechaNacimiento</label>
				<br/><br/>
				<input type="text" id="fechaNacimiento" name="fechaNacimiento"  class="entrada2" placeholder="Introduzca la fecha de nacimiento" onkeydown="checkFecha()"/>
				<span id="text5"></span>
				<br/><br/>
				<label id="subtitulo3" for="interes">Intereses</label>
				<br/><br/>
				<input type="text" id="interes" name="interes"  class="entrada2" placeholder="Introduzca los intereses: moda,cine,musica" onkeydown="checkIntereses()"/>
				<span id="text6"></span>
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
		
		<script>
			function validation(){
				
				let form = document.getElementById("form");
				let email = document.getElementById("email").value;
				let text = document.getElementById("text");
				let pattern = /^[^]+@[^ ]+\.[a-z]{2,3}$/;
				
				if(pattern.test(email)){

					form.classList.add("valid");
					form.classList.remove("invalid");
					text.innerHTML = "Email valido";
					text.style.color = "#008000";
				}
				
				else{
					
					form.classList.remove("valid");
					form.classList.add("invalid");
					text.innerHTML = "Introduzca un email valido";
					text.style.color = "#ff0000";
				}
				
			};
			
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