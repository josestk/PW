<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page errorPage="${pageContext.request.contextPath}/include/errorpage.jsp" %>
 <jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.Customerbean"></jsp:useBean>
<%

String respuestaa = request.getParameter("Si");
String respuestab = request.getParameter("No");
String nextPage = "";

	/*Se desea desconectar y hacer un cierre de sesion*/
	if(respuestaa != null && customerBean != null){
		
		/*El customer de la sesion actual se ha puesto a null lo que permite que se cree otro cuando otro usuario se quiera conectar*/
		request.getSession().removeAttribute("customerBean");
		%>
		<jsp:forward page="../../../index.jsp"/>
		<%
	}
	
	if(respuestab != null && customerBean != null){
		
		%>
		<jsp:forward page="../../view/user/inicio.jsp"/>
		<%
	}
%>

