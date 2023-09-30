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

import es.uco.pw.bussiness.anuncios.AnuncioIndividualizado;
import es.uco.pw.bussiness.anuncios.Fases;
import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.data.common.DAOException;
import es.uco.pw.data.dao.anuncios.AnuncioIndividualizadoDAO;
import es.uco.pw.display.javabean.Customerbean;

/**
 * Servlet implementation class anuncioIndividualizado
 */
public class anuncioIndividualizado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sqlConfig = new String();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public anuncioIndividualizado() {
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
		
		String opcion = request.getParameter("selector3");
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
		request.getRequestDispatcher("/mvc/view/user/anuncioIndividualizado.jsp").forward(request, response);
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
			request.getRequestDispatcher("/mvc/view/user/anuncioIndividualizado.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Session´s customer is saved in a variable and the information received from the jsp document is processed.*/
		
		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		String seleccion = request.getParameter("seleccion");
		AnuncioIndividualizadoDAO nuevo = new AnuncioIndividualizadoDAO(sqlConfig);

		/*The information received is from the option create.*/
		
		if(seleccion.equalsIgnoreCase("crear")) {
			
			Contacto nombre = new Contacto();
			int id = 0;
			String titulo = request.getParameter("tituloAnuncio");
			String cuerpo = request.getParameter("cuerpo");
			String destinatarios = request.getParameter("destinatarios");
			String fecha = request.getParameter("fechaPublicacion");
			
			nombre.setEmail(customer.getEmail());
			AnuncioIndividualizado creado = new AnuncioIndividualizado(id,titulo,cuerpo,nombre,destinatarios);
			
			try {
				
				SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
				Date nuevaFechaPublicacion = new Date(0);
				nuevaFechaPublicacion = fechaAux.parse(fecha);
				creado.setFechaPublicacion(nuevaFechaPublicacion);
				nuevo.insertAnuncioIndividualizado(creado);

			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		/*The information received is from the option delete*/
		
		else if(seleccion.equalsIgnoreCase("eliminar")) {
			
			String id = request.getParameter("id");
			int idborrado = 0;
			try {
				
				idborrado = Integer.parseInt(id);
				nuevo.removeAnuncioIndividualizado(idborrado);
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			

		}
		/*The information received is from the option get.*/
		
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
				AnuncioIndividualizado mostrado = nuevo.getAnuncioIndividualizado(idobtener);
				request.setAttribute("actualizar", actualizar);
				request.setAttribute("id", mostrado.getId());
				request.setAttribute("titulo",mostrado.getTitulo());
				request.setAttribute("cuerpo", mostrado.getCuerpo());
				request.setAttribute("propietario", mostrado.getPropietario().getEmail());
				request.setAttribute("destinatarios", mostrado.getDestinatarios());
				request.setAttribute("tipo", mostrado.getTipo());
				request.setAttribute("fase", mostrado.getFase());
				request.setAttribute("fecha", mostrado.getFechaPublicacion());
				request.setAttribute("valopcion", 3);
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/mvc/view/user/anuncioIndividualizado.jsp").forward(request, response);
			
		}
		/*The information received is from the option modify.*/
		
		else if(seleccion.equalsIgnoreCase("modificar")) {
			
			Contacto nombre = new Contacto();
			int id = 0;
			try {
				
				id = Integer.parseInt(request.getParameter("id"));
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			String titulo = request.getParameter("tituloAnuncio");
			String cuerpo = request.getParameter("cuerpo");
			String propietario = customer.getEmail();
			String Destinatarios = request.getParameter("destinatarios");
			String fecha = request.getParameter("fechaPublicacion");
			nombre.setEmail(customer.getEmail());
			
			
			AnuncioIndividualizado modificado = new AnuncioIndividualizado(id,titulo,cuerpo,nombre,Destinatarios);
			try {
				modificado = nuevo.getAnuncioIndividualizado(id);
				if(!titulo.equalsIgnoreCase("")) {
					
					modificado.setTitulo(titulo);
				}
				
				if(!cuerpo.equalsIgnoreCase("")) {
									
					modificado.setCuerpo(cuerpo);		
								}
				
				if(!propietario.equalsIgnoreCase("")) {
					
					modificado.setPropietario(nombre);
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
				
				modificado.setFase(Fases.editado);
				nuevo.updateAnuncioIndividualizado(modificado);
				
			} catch (DAOException e1) {
				e1.printStackTrace();
			}

			
			
		}
		/*The information received is from the option published.*/
		
		else if(seleccion.equalsIgnoreCase("publicar")) {
			
			Contacto nombre = new Contacto();
			int id = 0;
			try {
				
				id = Integer.parseInt(request.getParameter("id"));
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
			
			AnuncioIndividualizado modificado = new AnuncioIndividualizado(id,"","",nombre,"");
			
			try {
				modificado = nuevo.getAnuncioIndividualizado(id);
				modificado.setFase(Fases.publicado);
				

				modificado.setFechaPublicacion(new java.util.Date());
				
				nuevo.updateAnuncioIndividualizado(modificado);
				
			} catch (DAOException e) {
				e.printStackTrace();
			}

		}
		/*The information received is from the option store.*/
		
		else if(seleccion.equalsIgnoreCase("archivar")) {
			
			int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
			
			try {
				AnuncioIndividualizado archivado = nuevo.getAnuncioIndividualizado(id);
				archivado.setFase(Fases.archivado);
				nuevo.updateAnuncioIndividualizado(archivado);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			

		}
		
		/*It is redirected at the beginning of the application with all individual advertisements. */
		
		AnuncioIndividualizadoDAO a = new AnuncioIndividualizadoDAO(sqlConfig);
		ArrayList<AnuncioIndividualizado> ListadoPropietario = new ArrayList<AnuncioIndividualizado>();
		
		try {
			ListadoPropietario = a.getAnunciosIndividualizadosByFaseYPropietario("publicado",customer.getEmail());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		int filtrados = 3;
		
		request.setAttribute("filtrados", filtrados);
		request.setAttribute("anuncios", ListadoPropietario);
		request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
	}
}


