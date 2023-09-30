<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.bussiness.contacto.Contacto,es.uco.pw.data.dao.contacto.ContactoDAO,es.uco.pw.bussiness.contacto.*" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException" %>
<%@page errorPage="{pageContext.request.contextPath}/include/errorpage.jsp" %>

<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>
<%
String nextPage="../../view/user/registerview.jsp";
int registrado = 0;

 /*Primera vez que aparece en la vista el usuario se redirige a la vista para introducir los datos para el registro*/
 if(customerBean == null || customerBean.getEmail().equals("")){
	 
	 String email = request.getParameter("Email");
	 String nombre = request.getParameter("Nombre");
	 String apellidos = request.getParameter("Apellidos");
	 String Password = request.getParameter("Password");
	 String fecha = request.getParameter("FechaNacimiento");
	 String intereses = request.getParameter("Intereses");
	
	 /*Procede de la vista ya que trae parametros*/
	 if((email != null) && (nombre !=null) && (apellidos != null) && (Password != null) && (fecha != null) && (intereses !=  null)){
		 
		 ContactoDAO registro = new ContactoDAO(application.getInitParameter("sqlConfig"));
		 Contacto user = new Contacto();
		 /*Se comprueba que no existe el mismo email en la base de datos*/
		 if(registro.getContacto(email).getEmail().equals(""))
		 {
			 
			 user.setNombre(nombre);
			 user.setApellidos(apellidos);
			 user.setPassword(Password);
			 user.setEmail(email);
			 ArrayList<Intereses> interesesContacto = GestorContactos.getGestor().stringToIntereses(intereses);
			 user.setIntereses(interesesContacto);
			 try{
				 
					SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
					Date nuevaFechaNacimiento = new Date(0);
					nuevaFechaNacimiento = fechaAux.parse(fecha);
					user.setFechaNacimiento(nuevaFechaNacimiento);
			 }catch(ParseException e){
				 e.printStackTrace();
			 }
			 
			 registro.insertContacto(user);
			 request.getRequestDispatcher("../../../index.jsp").forward(request,response);
	
		 }
		 
		 else
		 {
			 
			registrado = 1;
		 }
		 
		 
	
	 }
	 	 
 }
 
 
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=registrado%>" name="registrado"/>
</jsp:forward>