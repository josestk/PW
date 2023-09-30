<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.data.dao.contacto.ContactoDAO,es.uco.pw.data.dao.contacto.ContactoInteresDAO,es.uco.pw.business.contacto.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@page errorPage="errorPage.jsp" %>

<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>

<%
String nombreupdate = request.getParameter("NuevoNombre");
String apellidosupdate = request.getParameter("NuevoApellido");
String contrasenaupdate = request.getParameter("NuevaPassword");
String fechanacimientoupdate = request.getParameter("NuevaFechaNacimiento");
String interes = request.getParameter("Nuevo interes(es)");
String nextpage = "../../view/user/inicio.jsp";
ContactoDAO user = new ContactoDAO(application.getInitParameter("RutaSQL"),application.getInitParameter("url"));
Contacto usuarioactual = user.getObject(customerBean.getEmail());

	/*Se ha pedido una request para modificar el nombre*/
	if(nombreupdate != null && customerBean != null){
		
		
		
		/*Se cambia el nombre del usuario de la sesion actual y se actualiza en la BD */
		usuarioactual.setNombre(nombreupdate);
		user.update(usuarioactual);
		nextpage = "../../view/user/inicio.jsp";
	}

	/*Se ha pedido una request para modificar apellidos*/
	if(apellidosupdate != null && customerBean != null){
		
		usuarioactual.setApellidos(apellidosupdate);
		user.update(usuarioactual);
		nextpage = "../../view/user/inicio.jsp";
		
	}
	
	/*Se ha pedido una request para modificar contrasenas*/
	if(contrasenaupdate != null && customerBean != null){
		
		usuarioactual.setPassword(contrasenaupdate);
		user.update(usuarioactual);
		nextpage = "../../view/user/inicio.jsp";
	}
	
	/*Se ha pedido una request para modificar fechaNacimiento*/
	if(fechanacimientoupdate != null && customerBean != null){
		
		 try{
			 
				SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
				Date nuevaFechaNacimiento = new Date(0);
				nuevaFechaNacimiento = fechaAux.parse(fechanacimientoupdate);
				usuarioactual.setFechaNacimiento(nuevaFechaNacimiento);
		 }catch(ParseException e){
			 e.printStackTrace();
		 }
		 
		 user.update(usuarioactual);
		 nextpage = "../../view/user/inicio.jsp";
	}
	
	if(interes != null &&  customerBean != null)
	{
		 ContactoInteresDAO nuevos = new ContactoInteresDAO(application.getInitParameter("RutaSQL"),application.getInitParameter("url"));
		 ArrayList<Intereses> interesesContacto = GestorContactos.getGestor().stringToIntereses(interes);
		 usuarioactual.setIntereses(interesesContacto);  /*Se debe escribir de nuevo todos los intereses*/
		 nuevos.update(usuarioactual);
		
	}

%>

<jsp:forward page="<%=nextpage%>"/>

