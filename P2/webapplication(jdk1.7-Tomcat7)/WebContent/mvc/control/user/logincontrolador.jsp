<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.contacto.Contacto,es.uco.pw.data.dao.contacto.ContactoDAO" %>
<%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>
<a href="<%= application.getInitParameter("url") %>">URL</a>

<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>

	
<%
int nintentos = 0;
String nextpage = "../../view/user/loginvista.jsp";
String mensaje = "";
	if (customerBean == null || customerBean.getEmail().equals("")) {
		String email = request.getParameter("Usuario");
		String Password = request.getParameter("Password");
				
		//Caso 2.a: Hay parámetros -> procede de la VISTA
		if (email != null) {
			//Se accede a bases de datos para obtener el usuario
			try{
				nintentos = Integer.parseInt(request.getParameter("nintentos"));
				
			}catch(Exception e)
			{
				System.out.println("Excepcion");
				nintentos = 0;
			}
			ContactoDAO usuarioDAO = new ContactoDAO(application.getInitParameter("RutaSQL"),application.getInitParameter("url"));
			Contacto user = usuarioDAO.getObject(email);
							
				/*Se ha obtenido un usuario comprobamos si el usuario obtenido es valido o no*/
				if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(Password)){
				
					/*El usuario obtenido es el introducido en la vista y por tanto su acceso a la aplicación es valido*/
				nextpage = "../../view/user/inicio.jsp";
				%>
				<jsp:setProperty property="email" value="<%=email%>" name="customerBean"/>
				<jsp:setProperty property="password" value="<%=Password%>" name="customerBean"/>
				<%
				}
				
				else{
					/*El usuario introducido no se corresponde con ninguno en la base de datos*/
					nextpage = "../../view/user/loginvista.jsp";
					mensaje = "usuario no valido";
					nintentos++;
				}
			
			
					//Caso 2.b -> se debe ir a la vista por primera vez
		}else{
					nextpage = "../../view/user/loginvista.jsp";
					nintentos = 0;
			 }
	}
	
%>

	<jsp:forward page ="<%=nextpage%>">
		<jsp:param value="<%=nintentos%>" name="nintentos" />
		<jsp:param value="<%=mensaje%>" name="mensaje" />
	</jsp:forward>

