package es.uco.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.data.common.DAOException;
import es.uco.pw.data.dao.anuncios.*;
import es.uco.pw.data.dao.contacto.ContactoDAO;
import es.uco.pw.display.javabean.Customerbean;

/**
 * Servlet implementation class desconexion
 */
public class desconexionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String sqlConfig = new String();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public desconexionController() {
        super();
    }

    public void init(ServletConfig config)throws ServletException{
    	
    	/* Load the string with the configuration parameters to do the connection to SQL data base */
    	
    	this.sqlConfig = config.getInitParameter("sqlConfig");
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int decision = Integer.parseInt(request.getParameter("opcion"));
		
		/* The chosen option for logout form was yes */
		if(decision == 1) {
			
			request.getSession().removeAttribute("customerBean");
			
			/* Redirect to index.jsp */
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
			/* The chosen option for logout form was yes, the announcements table needs to be load again with the filter chosen */
		}else {
			
			ContactoDAO usuarioDAO = new ContactoDAO(sqlConfig);
			Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
			Contacto user = null;
			try {
				user = usuarioDAO.getContacto(customer.getEmail());
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int filtrado = 0;
			
			
			request.setAttribute("filtrados", filtrado);
			int nuevo = 0;
			
			/* If there is no filter in the data base for the current user, load all the announcements for this user */ 
			if(user.getFiltro().equalsIgnoreCase("")) {
				
				
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
				}catch(Exception e) {
					
					e.printStackTrace();
				}
				request.setAttribute("anuncios1",anunciosGenrUsuario);
				request.setAttribute("anuncios2",anunciosFlashUsuario);
				request.setAttribute("anuncios3",anunciosTemUsuario);
				request.setAttribute("anuncios4",anunciosIndiUsuario);

				nuevo = 1;
				
			
			/* The user's filter is "general" */ 
			}else if(user.getFiltro().equalsIgnoreCase("general")) {
				
				AnuncioGeneralDAO a = new AnuncioGeneralDAO(sqlConfig);
				ArrayList<AnuncioGeneral> ListadoPropietario = new ArrayList<AnuncioGeneral>();
				
				try {
					ListadoPropietario = a.getAnuncioGeneralByFaseYPropietario("publicado",customer.getEmail());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/* "filtrados" takes the value 1, which means "general" */

				int filtrados = 1;
				
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoPropietario);
				
				/* The user filter is "flash" */
			}else if(user.getFiltro().equalsIgnoreCase("flash")) {
				
				ArrayList<AnuncioFlash> ListadoAnuncioFlash = new ArrayList<AnuncioFlash>();
				AnuncioFlashDAO filtro = new AnuncioFlashDAO(sqlConfig);
				
				
				try {
					
					ListadoAnuncioFlash = filtro.getAnuncioFlashByFaseYPropietario("publicado", customer.getEmail());
					
				}catch(DAOException e) {
					
					e.printStackTrace();
				}
				
			/* "filtrados" takes the value 2, which means "flash" */
			int filtrados = 2;
			request.setAttribute("filtrados", filtrados);
			request.setAttribute("anuncios", ListadoAnuncioFlash);
				
			/* The user's filter is "individuales" */ 
			}else if(user.getFiltro().equalsIgnoreCase("individuales")) {
				
				ArrayList<AnuncioIndividualizado> ListadoAnunciosIndiv = new ArrayList<AnuncioIndividualizado>();
				AnuncioIndividualizadoDAO filtrado1 = new AnuncioIndividualizadoDAO(sqlConfig);
				
				try {
					
					ListadoAnunciosIndiv = filtrado1.getAnunciosIndividualizadosByFaseYPropietario("publicado",customer.getEmail());
					
				}catch(DAOException e) {
					
					e.printStackTrace();
				}
				
				int filtrados = 3;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnunciosIndiv);
				
			/* The user's filter is "intereses" */ 
			}else if(user.getFiltro().equalsIgnoreCase("intereses")) {
				
				ArrayList<AnuncioTematico> ListadoAnunciosTem = new ArrayList<AnuncioTematico>();
				AnuncioTematicoDAO filtrado1 = new AnuncioTematicoDAO(sqlConfig);
				
				try {
					
					ListadoAnunciosTem = filtrado1.getAnuncioTematicoByInteresesPropietario(customer.getTemas());
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				/* "filtrados" takes the value 5, which means "intereses" */
				int filtrados = 5;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnunciosTem);
			}
			
			/* The user's filter is "tematicos" */ 
			else if(user.getFiltro().equalsIgnoreCase("tematicos")) {
				
				ArrayList<AnuncioTematico> ListadoAnunciosTem = new ArrayList<AnuncioTematico>();
				AnuncioTematicoDAO filtrado1 = new AnuncioTematicoDAO(sqlConfig);
				
				try {
					
					ListadoAnunciosTem = filtrado1.getAnunciosTematicosByFaseYPropietario("publicado",customer.getEmail());
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				/* "filtrados" takes the value 4, which means "tematicos" */
				int filtrados = 4;
				request.setAttribute("filtrados", filtrados);
				request.setAttribute("anuncios", ListadoAnunciosTem);
			}
			
			/* Redirect to "inicio.jsp" with the announcements found */
			request.setAttribute("inicio", nuevo);
			request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
