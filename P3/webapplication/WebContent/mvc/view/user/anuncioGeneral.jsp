<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Creacion AnuncioGeneral</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo1.css">	
	</head>
	<body>
	
	<%
		/**
			La informacion obtenida del servlet es procesada y dependiendo de la opcion escogida se mostrara un formulario u otro
		**/
		
		int crear = 0;
		int modificar = 0;
		int eliminar = 0;
		int obtener = 0;
		int publicar = 0;
		int archivar = 0;
			try{	
				String opcion1 = String.valueOf(request.getAttribute("valopcion"));  
				if(opcion1.equalsIgnoreCase("1")){
					
					crear = 1;
				}
				else if(opcion1.equalsIgnoreCase("2")){
					
					eliminar = 1;
				}
				
				else if(opcion1.equalsIgnoreCase("3")){
					
					obtener = 1;
				}
				
				else if(opcion1.equalsIgnoreCase("4")){
					
					modificar = 1;
				}
				else if(opcion1.equalsIgnoreCase("5")){
					
					publicar = 1;
				}
				else if(opcion1.equalsIgnoreCase("6")){
					
					archivar = 1;
				}

			}catch(Exception e){
				
				response.sendRedirect("/inicio.jsp");
			}
		String actualizar = String.valueOf(request.getAttribute("actualizar"));

		if(crear == 1){
		
		%>
			<!-- Formulario para la creacion de un Anuncio General-->
		
			<div id="cuadro">
					<form id="form" method="post" action="anuncioGeneral">
						
						<p id="titulo">Crear Anuncio General</p>
						<hr>
						<input id="seleccion" name="seleccion" type="hidden" value="crear"/>
						<br/><br/>
						<label id="subtitulo3" for="tituloAnuncio">Titulo</label>
						<br/><br/>
						<input type="text" id="tituloAnuncio" name="tituloAnuncio" class="entrada" placeholder="Introduzca el titulo" onkeydown="checkTitulo()"/>
						<span id=text2></span>
						<br/><br/>
						<label id="subtitulo3" for="cuerpo">Cuerpo</label>
						<br/><br/>
						<input type="text" id="cuerpo" name="cuerpo" class="entrada" placeholder="Introduzca el cuerpo" onkeydown="checkCuerpo()"/>
						<span id=text3></span>						
						<br/><br/>
						<label id="subtitulo3" for="propietario">Propietario</label>
						<br/><br/>
						<input type="text" id="propietario" name="propietario" class="entrada" placeholder="Introduzca el propietario" onkeydown="checkPropietario()"/>
						<span id=text4></span>
						<br/><br/>
						<label id="subtitulo3" for="destinatarios">Destinatarios</label> 
						<br/><br/>
						<input type="text" id="destinatarios" name="destinatarios" class="entrada2" placeholder="Introduzca los destinatarios" onkeydown="checkDestinatarios()"/>
						<span id=text5></span>
						<br/><br/> 
						<label id="subtitulo3" for="fechaPublicacion">FechaPublicacion</label>
						<br/><br/>
						<input type="text" id="fechaPublicacion" name="fechaPublicacion"  class="entrada2" placeholder="Introduzca la fecha publicacion" onkeydown="checkFechaP()">
						<span id=text6></span>
						<br/><br/>
						<button type="submit" name="Boton_Crear" id="buttom">Crear</button>
					</form>
			 </div>
		<%} 
		
		else if(eliminar == 1){
		%>
		<!-- Formulario para la eliminacion de un Anuncio General-->
		
		<div id="cuadro">
			<form id="form" method="post" action="anuncioGeneral">
				<p id="titulo">Eliminar Anuncio General</p>
				<hr>
				<input id="seleccion" name="seleccion" type="hidden" value="eliminar"/>
				<br/><br/>
				<label id="subtitulo3" for="id">Id Anuncio</label>
				<br/><br/>
				<input type="text" name="id" id="id" class="entrada" placeholder="Introduzca el id de un anuncio" onkeydown="checkId()"/>
				<span id=text1></span>
				<br/><br/>
			    <button type="submit" name="Boton_Borrado" id="buttom">Borrar</button>
			</form>
		</div>	
						
		<%

		}else if(modificar == 1){
		%>
		
		<!-- Formulario para la modificacion de un Anuncio General-->
		
		<div id="cuadro">
			<form id="form" method="post" action="anuncioGeneral">
				<p id="titulo">Modificar Anuncio General</p>
				<hr>
				<input id="seleccion" name="seleccion" type="hidden" value="modificar"/>
				<br/><br/>
				<label id="subtitulo3" for="id">ID A Modificar</label>
				<br/><br/>
				<input type="text" name="id" id="id" class="entrada" placeholder="Introduzca el id de un anuncio" onkeydown="checkId()"/>
				<span id=text1></span>
				<br/><br/>
				<label id="subtitulo3" for="tituloAnuncio">Titulo</label>
				<br/><br/>
				<input type="text" id="tituloAnuncio" name="tituloAnuncio" class="entrada" placeholder="Introduzca el titulo" onkeydown="checkTitulo()"/>
				<span id=text2></span>
				<br/><br/>
				<label id="subtitulo3" for="cuerpo">Cuerpo</label>
				<br/><br/>
				<input type="text" id="cuerpo" name="cuerpo" class="entrada" placeholder="Introduzca el cuerpo" onkeydown="checkCuerpo()"/>
				<span id=text3></span>						
				<br/><br/>
				<label id="subtitulo3" for="propietario">Propietario</label>
				<br/><br/>
				<input type="text" id="propietario" name="propietario" class="entrada" placeholder="Introduzca el propietario" onkeydown="checkPropietario()"/>
				<span id=text4></span>
				<br/><br/>
				<label id="subtitulo3" for="destinatarios">Destinatarios</label> 
				<br/><br/>
				<input type="text" id="destinatarios" name="destinatarios" class="entrada2" placeholder="Introduzca los destinatarios" onkeydown="checkDestinatarios()"/>
				<span id=text5></span>
				<br/><br/> 
				<label id="subtitulo3" for="fechaPublicacion">FechaPublicacion</label>
				<br/><br/>
				<input type="text" id="fechaPublicacion" name="fechaPublicacion"  class="entrada2" placeholder="Introduzca la fecha publicacion" onkeydown="checkFechaP()">
				<span id=text6></span>
				<br/><br/>
				<button type="submit" name="Boton_Modificiar" id="buttom">Modificar</button>
			</form>
		</div>
		<%
		}else if(obtener == 1){
			
			
			if(actualizar.equalsIgnoreCase("0")){
		%>
		
				<!-- Formulario para la obtencion de un Anuncio General-->
		
		<div id="cuadro">
			<form id="form"  method="post" action="anuncioGeneral">
				<p id="titulo">Obtener Anuncio General</p>
				<hr>
				<input id="seleccion" name="seleccion" type="hidden" value="obtener"/>
				<br/><br/>
				<label id="subtitulo3" for="id">Id Anuncio</label>
				<br/><br/>
				<input type="text" id="id" name="id" class="entrada" placeholder="Introduzca el id de un anuncio" onkeydown="checkId()"/>
				<span id=text1></span>
				<br/><br/>
			    <button type="submit" name="Boton_Obtener" id="buttom">Obtener</button>
			</form>
		</div>	
		<%
		}else if(actualizar.equalsIgnoreCase("1")){
		%>
		
				<!-- Formulario para la actualizacion de un Anuncio General-->
		
			<div id="cuadro">
			<form id="form"  method="get" action="anuncioGeneral">
				<p id="titulo">Anuncio General obtenido</p>
				<hr>
				<input id="inicio" name="inicio" type="hidden" value="volver"/>
				<br/><br/>
				<label id="subtitulo3" for="id"><%=request.getAttribute("id")%></label>
				<br/><br/>
				<label id="subtitulo3" for="tituloAnuncio"><%=request.getAttribute("titulo")%></label>
				<br/><br/>
				<label id="subtitulo3" for="cuerpo"><%=request.getAttribute("cuerpo")%></label>
				<br/><br/>
				<label id="subtitulo3" for="propietario"><%=request.getAttribute("propietario")%></label>
				<br/><br/>
				<label id="subtitulo3" for="destinatarios"><%=request.getAttribute("destinatarios")%></label>
				<br/><br/>
				<label id="subtitulo3" for="fechaPublicacion"><%=request.getAttribute("fecha")%></label>
				<br/><br/>
				<label id="subtitulo3" for="tipo"><%=request.getAttribute("tipo")%></label>
				<br/><br/>
				<label id="subtitulo3" for="fase"><%=request.getAttribute("fase")%></label>
				<br/><br/>
				<button id="buttom" type="submit" name="Boton_volver">Volver a Inicio</button>
			</form>
		</div>
	<%}%>
		<%
		}else if(publicar == 1){
		%>
		
				<!-- Formulario para la publicacion de un Anuncio General-->
		
		<div id="cuadro">
			<form id="form" method="post" action="anuncioGeneral">
				<p id="titulo">Publicar Anuncio General</p>
				<hr>
				<input id="seleccion" name="seleccion" type="hidden" value="publicar"/>
				<br/><br/>
				<label id="subtitulo3" for="id">Id Anuncio</label>
				<br/><br/>
				<input type="text" id="id" name="id" class="entrada" placeholder="Introduzca el id de un anuncio" onkeydown="checkId()"/>
				<span id="text1"></span>
				<br/><br/>
			    <button type="submit" name="Boton_Publicar" id="buttom">Publicar</button>
			</form>
		</div>	
		<%}else if(archivar == 1){%>
		
				<!-- Formulario para la archivacion de un Anuncio General-->
		
			<div id="cuadro">
			<form id="form" method="post" action="anuncioGeneral">
				<p id="titulo">Archivar Anuncio General</p>
				<hr>
				<input id="seleccion" name="seleccion" type="hidden" value="archivar"/>
				<br/><br/>
				<label id="subtitulo3" for="id">Id Anuncio</label>
				<br/><br/>
				<input type="text" id= "id" name="id"  class="entrada" placeholder="Introduzca el id de un anuncio" onkeydown="checkId()"/>
				<span id="text1"></span>
				<br/><br/>
			    <button type="submit" name="Boton_Archivar" id="buttom">Archivar</button>
			</form>
		</div>
		<%} %>
		
		<script>
		function checkId(){

			let form = document.getElementById("form");
			let id = document.getElementById("id").value;
			let text = document.getElementById("text1");
			let pattern = /^[0-9]*/;
			
			if(pattern.test(id)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Id valido";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Introduzca un id validos";
				text.style.color = "#ff0000";
			}
		};
		
		
		
		function checkTitulo(){
			
			let form = document.getElementById("form");
			let titulo = document.getElementById("tituloAnuncio").value;
			let text = document.getElementById("text2");
			let pattern = /[a-z]{3,4}/;
			
			if(pattern.test(titulo)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Titulo valido";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Introduzca un titulo valido";
				text.style.color = "#ff0000";
			}
		};
		
		function checkCuerpo(){
			
			let form = document.getElementById("form");
			let cuerpo = document.getElementById("cuerpo").value;
			let text = document.getElementById("text3");
			let pattern = /[a-z]{3,4}/;
			
			if(pattern.test(cuerpo)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Cuerpo valido";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Introduzca un cuerpo valido";
				text.style.color = "#ff0000";
			}
		};

		function checkPropietario(){
			let form = document.getElementById("form");
			let email = document.getElementById("propietario").value;
			let text = document.getElementById("text4");
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
		function checkDestinatarios(){
			
			let form = document.getElementById("form");
			let destinatarios = document.getElementById("destinatarios").value;
			let text = document.getElementById("text5");
			let pattern = /^[^]+@[^ ]+\.[a-z]{2,3}$/;
			
			if(pattern.test(destinatarios)){

				form.classList.add("valid");
				form.classList.remove("invalid");
				text.innerHTML = "Destinatarios validos";
				text.style.color = "#008000";
			}
			
			else{
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text.innerHTML = "Introduzca destinatarios validos";
				text.style.color = "#ff0000";
			}
			
		};
		
		function checkFechaP(){
			
			let form = document.getElementById("form");
			let text2 = document.getElementById("text6");
			let fecha = document.getElementById("fechaPublicacion").value;
			let pattern = /^(?:3[01]|[12][0-9]|0?[1-9])([\-/.])(0?[1-9]|1[1-2])\1\d{4}$/;
			if(pattern.test(fecha)){
				
				form.classList.remove("valid");
				form.classList.add("invalid");
				text2.innerHTML = "Introduzca una fecha de publicacion valida";
				text2.style.color = "#ff0000";
				
			}else{
				
				form.classList.add("valid");
				form.classList.remove("invalid");
				text2.innerHTML = "Fecha de publicacion valida";
				text2.style.color = "#008000";
			}
			
		};
	
	</script>
	</body>
</html>