package es.uco.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.bussiness.contacto.GestorContactos;
import es.uco.pw.bussiness.contacto.Intereses;
import es.uco.pw.data.common.DAOException;
import es.uco.pw.data.dao.anuncios.*;
import es.uco.pw.display.javabean.Customerbean;

/**
 * Servlet implementation class anuncioTematico
 */
public class anuncioTematico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sqlConfig = new String();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public anuncioTematico() {
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
		/*The chosen option its processed*/
		
		String opcion = request.getParameter("selector1");
		String opcion2 = request.getParameter("inicio");
		int opcion3 = 0;
		
		if(request.getParameter("accion") != null) {
		opcion3 = Integer.parseInt(String.valueOf(request.getParameter("accion")));
		}
		
		/*In this case an option of the menu was selected*/
		
		if(opcion != null && opcion2 == null) {
		int actualizar = 0;
		request.setAttribute("valopcion", opcion);
		request.setAttribute("actualizar", actualizar);
		request.getRequestDispatcher("/mvc/view/user/anuncioTematico.jsp").forward(request, response);
		}
		
		else if(opcion == null && opcion2 != null) {
			

			request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
		}
		/*In this case, the user logged selected one the actions showed on the board.*/
		
		else if(opcion == null && opcion2 == null && opcion3 != 0) {
			
			if(opcion3 == 1) {
				
				int editar = 4;
				request.setAttribute("valopcion", editar);

			}
			
			else if(opcion3 == 2) {
				
				int archivar = 6;
				request.setAttribute("valopcion", archivar);
				
			}
			
			else if(opcion3 == 3) {
				
				int eliminar = 2;
				request.setAttribute("valopcion", eliminar);
				
			}else if(opcion3 == 4) {
				
				int recuperar = 5;
				request.setAttribute("valopcion", recuperar);
			}
			request.getRequestDispatcher("/mvc/view/user/anuncioTematico.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Session´s customer is saved in a variable and the information received from the jsp document is processed.*/
		
		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		String seleccion = request.getParameter("selector1");
		AnuncioTematicoDAO nuevo1 = new AnuncioTematicoDAO(sqlConfig);
		
		/*The information received is from the option create.*/
		
		if(seleccion.equalsIgnoreCase("crear")) {
			
			Contacto nombre = new Contacto();
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("tituloAnuncio");
			String cuerpo = request.getParameter("cuerpo");
			String Destinatarios = request.getParameter("destinatarios");
			String fecha = request.getParameter("fechaPublicacion");
			String interes = request.getParameter("interes");
			nombre.setEmail(customer.getEmail());
		    ArrayList<Intereses> interesesAnuncio = GestorContactos.getGestor().stringToIntereses(interes);
			AnuncioTematico creado = new AnuncioTematico(id,titulo,cuerpo,nombre,interesesAnuncio);
			try {
				
				SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
				Date nuevaFechaPublicacion = new Date(0);
				nuevaFechaPublicacion = fechaAux.parse(fecha);
				creado.setFechaPublicacion(nuevaFechaPublicacion);
				creado.setDestinatarios(Destinatarios);
				nuevo1.insertAnuncioTematico(creado);

			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
		}
		/*The information received is from the option delete*/
		
		else if(seleccion.equalsIgnoreCase("eliminar")) {
			
			int idBuscar = Integer.parseInt(request.getParameter("id"));
			try {
				nuevo1.removeAnuncioTematico(idBuscar);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);

			
		}
		/*The information received is from the option get.*/
		
		else if(seleccion.equalsIgnoreCase("modificar")) {
			
			int idModificar = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("tituloAnuncio");
			String cuerpo = request.getParameter("cuerpo");
			String Destinatarios = request.getParameter("destinatarios");
			String fecha = request.getParameter("fechaPublicacion");
			String interes = request.getParameter("interes");
			
			try {
				AnuncioTematico modificado = nuevo1.getAnuncioTematico(idModificar);
				if(!titulo.equalsIgnoreCase("")) {
					modificado.setTitulo(titulo);
				}
				if(!cuerpo.equalsIgnoreCase("")) {
									
					modificado.setCuerpo(cuerpo);		
				}
				if(!Destinatarios.equalsIgnoreCase("")) {
					
					modificado.setDestinatarios(Destinatarios);
				}
				if(!fecha.equalsIgnoreCase("")) {
					
					try {
						
						SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
						Date nuevaFechaPublicacion = new Date(0);
						nuevaFechaPublicacion = fechaAux.parse(fecha);
						modificado.setFechaPublicacion(nuevaFechaPublicacion);

					}catch(Exception e) {
						
						e.printStackTrace();
					}
				}
				
				if(!interes.equalsIgnoreCase("")) {
					
					ArrayList<Intereses> nuevoInteres = GestorContactos.getGestor().stringToIntereses(interes);
					modificado.setIntereses(nuevoInteres);
				}
				
				modificado.setFase(Fases.editado);
				nuevo1.updateAnuncioTematico(modificado);
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			
		}
		/*The information received is from the option modify.*/
		
		else if(seleccion.equalsIgnoreCase("obtener")) {
			
			int idobtener = 0;
			int actualizar = 0;
			try {
				idobtener = Integer.parseInt(request.getParameter("id"));
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			try {
				actualizar = 1;
				AnuncioTematico mostrado = nuevo1.getAnuncioTematico(idobtener);
				request.setAttribute("actualizar", actualizar);
				request.setAttribute("id", mostrado.getId());
				request.setAttribute("titulo",mostrado.getTitulo());
				request.setAttribute("cuerpo", mostrado.getCuerpo());
				request.setAttribute("propietario", mostrado.getPropietario().getEmail());
				request.setAttribute("destinatarios", mostrado.getDestinatarios());
				request.setAttribute("tipo", mostrado.getTipo());
				request.setAttribute("fase", mostrado.getFase());
				request.setAttribute("fecha", mostrado.getFechaPublicacion());
				String interesesAnuncio = GestorContactos.getGestor().interesesToString(mostrado.getIntereses());
				request.setAttribute("Interes", interesesAnuncio);
				request.setAttribute("valopcion", 3);
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/mvc/view/user/anuncioTematico.jsp").forward(request, response);
		}
		
		/*The information received is from the option published.*/
		
		else if(seleccion.equalsIgnoreCase("publicar")) {
			
			int id = 0;
			try {
				
				id = Integer.parseInt(request.getParameter("id"));
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
			
			
			
			try {
				AnuncioTematico modificado = nuevo1.getAnuncioTematico(id);
				modificado = nuevo1.getAnuncioTematico(id);
				modificado.setFase(Fases.publicado);
				

				modificado.setFechaPublicacion(new java.util.Date());
				
				nuevo1.updateAnuncioTematico(modificado);
				
			} catch (DAOException e) {
				e.printStackTrace();
			}

		}
		/*The information received is from the option store.*/
		
		else if(seleccion.equalsIgnoreCase("archivar")) {
			
			int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
			
			try {
				AnuncioTematico archivado = nuevo1.getAnuncioTematico(id);
				archivado.setFase(Fases.archivado);
				nuevo1.updateAnuncioTematico(archivado);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			

		}
		
		/*It is redirected at the beginning of the application with all thematic advertisements. */
		
		AnuncioTematicoDAO a = new AnuncioTematicoDAO(sqlConfig);
		ArrayList<AnuncioTematico> ListadoPropietario = new ArrayList<AnuncioTematico>();
		
		try {
			ListadoPropietario = a.getAnunciosTematicosByFaseYPropietario("publicado",customer.getEmail());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		int filtrados = 4;
		request.setAttribute("filtrados", filtrados);
		request.setAttribute("anuncios", ListadoPropietario);
		request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
	}

}
