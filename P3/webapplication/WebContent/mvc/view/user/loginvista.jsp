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

int nintentos = 0;
try{
	
	if(request.getAttribute("intentos")!=null){
		nintentos = Integer.parseInt(request.getAttribute("intentos").toString());
	}
	
}catch(Exception e){
	
		e.printStackTrace();
	}

if (nintentos<=3)
{
	
%>
<!-- Aparecerá en primer lugar el formulario con las opciones de login y de register -->
		
		<div id="cuadro">
		<!-- Realizamos un formulario para el acceso a nuestra aplicacion -->
			
			<form  id="form" method="post" action="login">
				<p id="titulo">INICIO DE SESION</p>
				<hr>
				<br/><br/>
				<label id="subtitulo1" for="Usuario">USUARIO</label>
				<br/><br/>
				<input type="text" id="Usuario" name="Usuario" class="entrada" placeholder="Introduzca un email" onkeydown="validation()"/>
				<span id="text"></span>
				<br/><br/>
				<label id="subtitulo1" for="Password">PASSWORD</label>
				<br/><br/>
				<input type="password" id="Password" name="Password" class="entrada2" placeholder="Introduzca un email" onkeydown="validation2()"/>
				<span id="text2"></span>
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
		
		<script>
		
			
			
			function validation(){
				
				let form = document.getElementById("form");
				let email = document.getElementById("Usuario").value;
				let password = document.getElementById("Password").value;
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
			
			function validation2(){
				
				let form = document.getElementById("form");
				let text2 = document.getElementById("text2");
				let password = document.getElementById("Password").value;
				let pattern2 = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
				if(pattern2.test(password)){
					
					form.classList.remove("valid");
					form.classList.add("invalid");
					text2.innerHTML = "Introduzca una contraseña valida";
					text2.style.color = "#ff0000";
					
				}else{
					
					form.classList.add("valid");
					form.classList.remove("invalid");
					text2.innerHTML = "Contraseña valida";
					text2.style.color = "#008000";
				}
			};
		
		</script>
<%
}
%>	
			
	</body>
</html>




