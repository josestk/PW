<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.data.dao.contacto.ContactoDAO,es.uco.pw.data.dao.contacto.ContactoInteresDAO,es.uco.pw.bussiness.contacto.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@page errorPage="errorPage.jsp" %>

<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>

<%
String nombreupdate = request.getParameter("nombre");
String apellidosupdate = request.getParameter("apellidos");
String contrasenaupdate = request.getParameter("password");
String fechanacimientoupdate = request.getParameter("fechaNacimiento");
String interes = request.getParameter("interes");
String nextpage = "../../view/user/inicio.jsp";
ContactoDAO user = new ContactoDAO(application.getInitParameter("sqlConfig"));
Contacto usuarioactual = user.getContacto(customerBean.getEmail());

	/*Se ha pedido una request para modificar el nombre*/
	if(nombreupdate != null && customerBean != null){
		
		
		
		/*Se cambia el nombre del usuario de la sesion actual y se actualiza en la BD */
		usuarioactual.setNombre(nombreupdate);
		user.updateContacto(usuarioactual);
		nextpage = "../../view/user/inicio.jsp";
	}

	/*Se ha pedido una request para modificar apellidos*/
	if(apellidosupdate != null && customerBean != null){
		
		usuarioactual.setApellidos(apellidosupdate);
		user.updateContacto(usuarioactual);
		nextpage = "../../view/user/inicio.jsp";
		
	}
	
	/*Se ha pedido una request para modificar contrasenas*/
	if(contrasenaupdate != null && customerBean != null){
		
		usuarioactual.setPassword(contrasenaupdate);
		user.updateContacto(usuarioactual);
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
		 
		user.updateContacto(usuarioactual);
		nextpage = "../../view/user/inicio.jsp";
	}
	
	if(interes != null &&  customerBean != null)
	{
		ContactoInteresDAO nuevos = new ContactoInteresDAO(application.getInitParameter("sqlConfig"));
		ArrayList<Intereses> interesesContacto = GestorContactos.getGestor().stringToIntereses(interes);
		usuarioactual.setIntereses(interesesContacto);  /*Se debe escribir de nuevo todos los intereses*/
		nuevos.updateContactoInteres(usuarioactual);
		
	}

%>

<jsp:forward page="<%=nextpage%>"/>

