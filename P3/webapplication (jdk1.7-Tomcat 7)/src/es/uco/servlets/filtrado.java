package es.uco.servlets;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.data.dao.anuncios.AnuncioFlashDAO;
import es.uco.pw.data.dao.anuncios.AnuncioGeneralDAO;
import es.uco.pw.data.dao.anuncios.AnuncioIndividualizadoDAO;
import es.uco.pw.data.dao.anuncios.AnuncioTematicoDAO;
import es.uco.pw.data.dao.contacto.ContactoDAO;
import es.uco.pw.bussiness.anuncios.AnuncioFlash;
import es.uco.pw.bussiness.anuncios.AnuncioGeneral;
import es.uco.pw.bussiness.anuncios.AnuncioIndividualizado;
import es.uco.pw.bussiness.anuncios.AnuncioTematico;
import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.data.common.DAOException;
import es.uco.pw.display.javabean.*;
/**
 * Servlet implementation class filtrado
 */
//@WebServlet("/filtrado")
public class filtrado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sqlConfig = new String();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filtrado() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config)throws ServletException{
    	
    	/* Load the string with the configuration parameters to do the connection to SQL data base */
    	this.sqlConfig = config.getInitParameter("sqlConfig");

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		int opcion = Integer.parseInt(request.getParameter("Filtros"));
		ContactoDAO usuarioLogged = new ContactoDAO(sqlConfig);
		Contacto usuario = null;
		
		/* Check if the user is logged */
		 if(customer != null) {
			 
			 try {
				 
				 /* Get the user from the data base to apply the right filter */
				 
				 usuario = usuarioLogged.getContacto(customer.getEmail());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
			 
				/* The user's filter is "general" */ 
			if(opcion==1) {
			
			
			AnuncioGeneralDAO a = new AnuncioGeneralDAO(sqlConfig);
			ArrayList<AnuncioGeneral> ListadoPropietario = new ArrayList<AnuncioGeneral>();
			
			try {
				ListadoPropietario = a.getAnuncioGeneralByFaseYPropietario("publicado",customer.getEmail());
				usuario.setFiltro("general");
				usuarioLogged.updateContacto(usuario);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			/* "filtrados" takes the value 1, which means "general" */
			int filtrados = 1;
			
			request.setAttribute("filtrados", filtrados);
			request.setAttribute("anuncios", ListadoPropietario);
			}
			
			/* The user filter is "flash" */
			else if(opcion==2) {
								ArrayList<AnuncioFlash> ListadoAnuncioFlash = new ArrayList<AnuncioFlash>();
				AnuncioFlashDAO filtro = new AnuncioFlashDAO(sqlConfig);
				
				
				try {
					
					ListadoAnuncioFlash = filtro.getAnuncioFlashByFaseYPropietario("publicado", customer.getEmail());
					usuario.setFiltro("flash");
					usuarioLogged.updateContacto(usuario);
				}catch(DAOException e) {
					
					e.printStackTrace();
				}
				
				/* "filtrados" takes the value 2, which means "flash" */
				int filtrados = 2;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnuncioFlash);
						
			}
			/* The user filter is "individuales" */
			else if(opcion==3) {
				
				ArrayList<AnuncioIndividualizado> ListadoAnunciosIndiv = new ArrayList<AnuncioIndividualizado>();
				AnuncioIndividualizadoDAO filtrado = new AnuncioIndividualizadoDAO(sqlConfig);
				
				try {
					
					ListadoAnunciosIndiv = filtrado.getAnunciosIndividualizadosByFaseYPropietario("publicado", customer.getEmail());
					usuario.setFiltro("individuales");
					usuarioLogged.updateContacto(usuario);
				}catch(DAOException e) {
					
					e.printStackTrace();
				}
				
				/* "filtrados" takes the value 3, which means "individuales" */
				int filtrados = 3;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnunciosIndiv);
				
			/* The user filter is "tematicos" */
			}else if(opcion==4) {
				
				ArrayList<AnuncioTematico> ListadoAnunciosTem = new ArrayList<AnuncioTematico>();
				AnuncioTematicoDAO filtrado = new AnuncioTematicoDAO(sqlConfig);
				
				try {
					ListadoAnunciosTem = filtrado.getAnunciosTematicosByFaseYPropietario("publicado", customer.getEmail());
					usuario.setFiltro("tematicos");
					usuarioLogged.updateContacto(usuario);
				}catch(Exception e) {
					e.printStackTrace();
				}
				/* "filtrados" takes the value 4, which means "tematicos" */
				int filtrados = 4;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnunciosTem);

			}
			/* The user filter is "intereses" */
			else if(opcion == 5) {
				
				ArrayList<AnuncioTematico> ListadoAnunciosTem = new ArrayList<AnuncioTematico>();
				AnuncioTematicoDAO filtrado = new AnuncioTematicoDAO(sqlConfig);
				
				try {
					
					ListadoAnunciosTem = filtrado.getAnuncioTematicoByInteresesPropietario(customer.getTemas());
					usuario.setFiltro("intereses");
					usuarioLogged.updateContacto(usuario);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				/* "filtrados" takes the value 5, which means "tematicos" */
				int filtrados = 5;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnunciosTem);
			}
			
			/* The user filter is "fechaPublicacion" */
			else if(opcion == 6) {
				
				request.getRequestDispatcher("/mvc/view/user/fechaPublicacion.jsp").forward(request, response);
			}
			
			/* If there is no filter in the data base for the current user, load all the announcements for this user */ 
			else if(opcion == 7) {
				int filtrado = 0;
				int nuevo = 1;
				ArrayList<AnuncioGeneral> anunciosGenrUsuario = new ArrayList<AnuncioGeneral>();
				ArrayList<AnuncioFlash> anunciosFlashUsuario = new ArrayList<AnuncioFlash>();
				ArrayList<AnuncioTematico> anunciosTemUsuario = new ArrayList<AnuncioTematico>();
				ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = new ArrayList<AnuncioIndividualizado>();
				
				AnuncioGeneralDAO Generales = new AnuncioGeneralDAO(sqlConfig);
				AnuncioFlashDAO Flashs = new AnuncioFlashDAO(sqlConfig);
				AnuncioIndividualizadoDAO Individuales = new AnuncioIndividualizadoDAO(sqlConfig);
				AnuncioTematicoDAO Tematicos = new AnuncioTematicoDAO(sqlConfig);
				try {
					
				anunciosGenrUsuario = Generales.getAnuncioGeneralByFase("publicado");
				anunciosFlashUsuario = Flashs.getAnuncioFlashByFase("publicado");
				anunciosTemUsuario = Tematicos.getAnuncioTematicoByFase("publicado");
				anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFase("publicado");
				usuario.setFiltro("");
				usuarioLogged.updateContacto(usuario);
				}catch(Exception e) {
					
					e.printStackTrace();
				}
				request.setAttribute("anuncios1",anunciosGenrUsuario);
				request.setAttribute("anuncios2",anunciosFlashUsuario);
				request.setAttribute("anuncios3",anunciosTemUsuario);
				request.setAttribute("anuncios4",anunciosIndiUsuario);
				request.setAttribute("filtrados", filtrado);
				request.setAttribute("inicio", nuevo);
				
			}
				/* Redirect to "inicio.jsp" with the announcements found */
				request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
		 }else {
				/* Redirect to "inicio.jsp" because the user is not logged */
				request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
			 
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* This method search the announcements by an interval of time defined by two dates */
		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		
		Date inicio = new Date(0);
		Date fin = new Date(0);
		int filtrado = 6;
		ContactoDAO usuarioLogged = new ContactoDAO(sqlConfig);
		Contacto usuario = null;
		
		ArrayList<AnuncioGeneral> anunciosGenrUsuario = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> anunciosFlashUsuario = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioTematico> anunciosTemUsuario = new ArrayList<AnuncioTematico>();
		ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = new ArrayList<AnuncioIndividualizado>();
		
		AnuncioGeneralDAO Generales = new AnuncioGeneralDAO(sqlConfig);
		AnuncioFlashDAO Flashs = new AnuncioFlashDAO(sqlConfig);
		AnuncioIndividualizadoDAO Individuales = new AnuncioIndividualizadoDAO(sqlConfig);
		AnuncioTematicoDAO Tematicos = new AnuncioTematicoDAO(sqlConfig);
		
		SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");

		/* Check if the is not a final date */ 
		if(request.getParameter("FechaFinal").equalsIgnoreCase("")){
			
			try {
				inicio =  fechaAux.parse(request.getParameter("FechaInicio"));
				
				java.sql.Date inicioSQL = new java.sql.Date(inicio.getTime());
				
				anunciosGenrUsuario = Generales.getAnuncioGeneralByFecha(inicioSQL);
				anunciosFlashUsuario = Flashs.getAnuncioFlashByFecha(inicioSQL);
				anunciosTemUsuario = Tematicos.getAnuncioTematicoByFecha(inicioSQL);
				anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFecha(inicioSQL);
				usuario = usuarioLogged.getContacto(customer.getEmail());
				
				/* If there is not a final date, takes the publication date */
				usuario.setFiltro("FechaPublicacion");
				usuarioLogged.updateContacto(usuario);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* There is an initial and final date */
		else {
			
			try {
				inicio =  fechaAux.parse(request.getParameter("FechaInicio"));
				fin =  fechaAux.parse(request.getParameter("FechaFinal"));
				
				java.sql.Date inicioSQL = new java.sql.Date(inicio.getTime());
				java.sql.Date finSQL = new java.sql.Date(fin.getTime());
				
				anunciosGenrUsuario = Generales.getAnuncioGeneralByIntervalo(inicioSQL, finSQL);
				anunciosFlashUsuario = Flashs.getAnuncioFlashByIntervalo(inicioSQL, finSQL);
				anunciosTemUsuario = Tematicos.getAnuncioTematicoByIntervalo(inicioSQL, finSQL);
				anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByIntervalo(inicioSQL, finSQL);
				usuario = usuarioLogged.getContacto(customer.getEmail());
				
				usuario.setFiltro("FechaPublicacion");
				usuarioLogged.updateContacto(usuario);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("anuncios1",anunciosGenrUsuario);
		request.setAttribute("anuncios2",anunciosFlashUsuario);
		request.setAttribute("anuncios3",anunciosTemUsuario);
		request.setAttribute("anuncios4",anunciosIndiUsuario);
		request.setAttribute("filtrados", filtrado);
		
		/* Redirect to "inicio.jsp" with the announcements found */
		request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);

		
	}

}
