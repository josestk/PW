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
import es.uco.pw.data.dao.contacto.*;
import es.uco.pw.display.javabean.*;
/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String sqlConfig = new String();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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

		/* Check if the customer exists */
		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		if(customer == null) {
		
			request.getRequestDispatcher("/mvc/view/user/loginvista.jsp").forward(request, response);
			
		}else {
			
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Creates the customer bean for the new user */
		ContactoDAO usuarioDAO = new ContactoDAO(sqlConfig);
		ContactoInteresDAO userDAO = new ContactoInteresDAO(sqlConfig);

		String email = request.getParameter("Usuario");
		String password = request.getParameter("Password");
		int intentos = 0;

		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		
		try {
			/* Search in the data base for the user to log in */
			Contacto user = usuarioDAO.getContacto(email);

			/* If the user is valid, copy the attributes to the customer bean */
			if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
				
				customer.setEmail(email);
				customer.setPassword(password);
				customer.setnIntentos(0);
				customer.setTemas(userDAO.getContactoInteres(email).getIntereses());
				customer.setFiltro(user.getFiltro());
				int filtrado = 0;
				
				/* Do the user's announcements filter */
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
					
					anunciosGenrUsuario = Generales.getAnuncioGeneralByFase("publicado");
					anunciosFlashUsuario = Flashs.getAnuncioFlashByFase("publicado");
					anunciosTemUsuario = Tematicos.getAnuncioTematicoByFase("publicado");
					anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFase("publicado");
					
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
					/* "filtrados" takes the value 5, which means "tematicos" */
					int filtrados = 4;
					request.setAttribute("filtrados", filtrados);
					request.setAttribute("anuncios", ListadoAnunciosTem);
					request.setAttribute("intentos", intentos);
				}
				
				/* Redirect to "inicio.jsp" with the announcements found */
				request.setAttribute("inicio", nuevo);
				request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
			}

			/* The user is no valid, the attempt is increased by 1 */
			else {
				
				customer.setnIntentos(customer.getnIntentos()+1);
				intentos = customer.getnIntentos();
				
				/* When the user attempt to log in 3 times, the app redirects to "www.uco.es" */
				if(intentos >3) {
					
					customer.setnIntentos(0);
					response.sendRedirect("http://www.uco.es/");
					intentos = customer.getnIntentos();
				}
				
				else {
					request.setAttribute("intentos", intentos);
					request.getRequestDispatcher("/mvc/view/user/loginvista.jsp").forward(request, response);;
				}
			}
				
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
