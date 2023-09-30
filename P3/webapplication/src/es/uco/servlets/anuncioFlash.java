package es.uco.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.bussiness.anuncios.AnuncioFlash;
import es.uco.pw.bussiness.anuncios.Fases;
import es.uco.pw.bussiness.contacto.Contacto;
import es.uco.pw.data.common.DAOException;
import es.uco.pw.data.dao.anuncios.AnuncioFlashDAO;
import es.uco.pw.display.javabean.Customerbean;

/**
 * Servlet implementation class anuncioFlash
 */

public class anuncioFlash extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sqlConfig = new String();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public anuncioFlash() {
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
		
		String opcion = request.getParameter("selector2");
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
		request.getRequestDispatcher("/mvc/view/user/anuncioFlash.jsp").forward(request, response);
		
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
			request.getRequestDispatcher("/mvc/view/user/anuncioFlash.jsp").forward(request, response);
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Session´s customer is saved in a variable and the information received from the jsp document is processed.*/

		Customerbean customer = (Customerbean)request.getSession().getAttribute("customerBean");
		String seleccion = request.getParameter("seleccion");
		AnuncioFlashDAO nuevo = new AnuncioFlashDAO(sqlConfig);
		Date fechaActual = new Date();

		/*The information received is from the option create.*/

		if(seleccion.equalsIgnoreCase("crear")) {
			
				Contacto nombre = new Contacto();
				int id = 0;
				String titulo = request.getParameter("tituloAnuncio");
				String cuerpo = request.getParameter("cuerpo");
				String Destinatarios = request.getParameter("destinatarios");
				String fechaString = request.getParameter("fechaPublicacion");
				String fechaInicioString = request.getParameter("fechaInicioPublicacion");
				String fechaFinalString = request.getParameter("fechaFinalPublicacion");
	
	
				nombre.setEmail(customer.getEmail());
				AnuncioFlash creado = new AnuncioFlash(id,titulo,cuerpo,nombre,new Date(0),new Date(1));
				creado.setDestinatarios(Destinatarios);
				
				SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaInicioPublicacion = new Date(0);
				Date fechaFinalPublicacion = new Date(0);
				Date fechaPublicacion = new Date(0);
				
	
			try {
				
				fechaInicioPublicacion = fechaAux.parse(fechaInicioString);
				fechaFinalPublicacion = fechaAux.parse(fechaFinalString);
				fechaPublicacion = fechaAux.parse(fechaString);
				creado.setFechaPublicacion(fechaPublicacion);		
				creado.setFechaInicio(fechaInicioPublicacion);
				creado.setFechaFinal(fechaFinalPublicacion);
				
				if((creado.getFechaInicio().compareTo(creado.getFechaPublicacion()) == 0) && (creado.getFechaInicio().before(creado.getFechaFinal()))){
					
					creado.setFase(Fases.publicado);
					creado.setFechaPublicacion(new Date());

	            }
				else if(creado.getFechaInicio().before(creado.getFechaPublicacion())){
					
					creado.setFase(Fases.espera);
				}
				else if(creado.getFechaFinal().before(fechaActual)) {
					
					creado.setFase(Fases.archivado);
				}
				nuevo.insertAnuncioFlash(creado);
	
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		
		/*The information received is from the option delete*/

		else if(seleccion.equalsIgnoreCase("eliminar")) {
			
			String id = request.getParameter("id");
			int idborrado = 0;
			try {
				
				idborrado = Integer.parseInt(id);
				nuevo.removeAnuncioFlash(idborrado);
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
				AnuncioFlash mostrado = nuevo.getAnuncioFlash(idobtener);
				request.setAttribute("actualizar", actualizar);
				request.setAttribute("id", mostrado.getId());
				request.setAttribute("titulo",mostrado.getTitulo());
				request.setAttribute("cuerpo", mostrado.getCuerpo());
				request.setAttribute("propietario", mostrado.getPropietario().getEmail());
				request.setAttribute("destinatarios", mostrado.getDestinatarios());
				request.setAttribute("tipo", mostrado.getTipo());
				request.setAttribute("fase", mostrado.getFase());
				request.setAttribute("fecha", mostrado.getFechaPublicacion());
				request.setAttribute("fechaInicio", mostrado.getFechaInicio());
				request.setAttribute("fechaFinal", mostrado.getFechaFinal());
				request.setAttribute("valopcion", 3);
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
						
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
			String fechaString = request.getParameter("fechaPublicacion");
			String fechaInicioString = request.getParameter("fechaInicioPublicacion");
			String fechaFinalString = request.getParameter("fechaFinalPublicacion");
			nombre.setEmail(customer.getEmail());


			
			AnuncioFlash modificado = new AnuncioFlash(id,titulo,cuerpo,nombre,new Date(0),new Date(1));
			try {

				modificado = nuevo.getAnuncioFlash(id);
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
				
				if((!fechaString.equalsIgnoreCase("")) || (!fechaInicioString.equalsIgnoreCase("")) || (!fechaFinalString.equalsIgnoreCase(""))) {

					SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
					
	
					try {
						
						if((!fechaString.equalsIgnoreCase(""))){
						Date fechaPublicacion = new Date(0);
						fechaPublicacion = fechaAux.parse(fechaString);
						modificado.setFechaPublicacion(fechaPublicacion);		

						}
						if((!fechaInicioString.equalsIgnoreCase(""))){
						Date fechaInicioPublicacion = new Date(0);
						fechaInicioPublicacion = fechaAux.parse(fechaInicioString);
						modificado.setFechaInicio(fechaInicioPublicacion);

						}
						if((!fechaFinalString.equalsIgnoreCase(""))){
						Date fechaFinalPublicacion = new Date(0);
						fechaFinalPublicacion = fechaAux.parse(fechaFinalString);
						modificado.setFechaFinal(fechaFinalPublicacion);

						}
						
	
					}catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				modificado.setFase(Fases.editado);
				nuevo.updateAnuncioFlash(modificado);

			}catch (DAOException e1) {
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
			
			
			AnuncioFlash modificado = new AnuncioFlash(id,null,null,nombre,new Date(0),new Date(1));
			try {
				modificado = nuevo.getAnuncioFlash(id);
				modificado.setFase(Fases.publicado);
				nuevo.updateAnuncioFlash(modificado);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}
		
		/*The information received is from the option store.*/

		else if(seleccion.equalsIgnoreCase("archivar")) {
			
			int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
			
			try {
				AnuncioFlash archivado = nuevo.getAnuncioFlash(id);
				archivado.setFase(Fases.archivado);
				nuevo.updateAnuncioFlash(archivado);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			

		}
		
		/*It is redirected at the beginning of the application with all Flash advertisements. */

		AnuncioFlashDAO a = new AnuncioFlashDAO(sqlConfig);
		ArrayList<AnuncioFlash> ListadoPropietario = new ArrayList<AnuncioFlash>();
		
		try {
			ListadoPropietario = a.getAnuncioFlashByFaseYPropietario("publicado", customer.getEmail());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		int filtrados = 2;
		
		request.setAttribute("filtrados", filtrados);
		request.setAttribute("anuncios", ListadoPropietario);
		request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
		}
	}
	
	


