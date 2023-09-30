package es.uco.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.bussiness.anuncios.*;
import es.uco.pw.bussiness.contacto.Interes;
import es.uco.pw.data.common.DAOException;
import es.uco.pw.data.dao.anuncios.*;


/**
 * Servlet implementation class busqueda
 */

public class busqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sqlConfig = new String();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busqueda() {
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
		
		/* Search in the data base for announcements which have "clave" in one of their attributes */
		
		String clave = request.getParameter("clave");

		
		ArrayList<AnuncioGeneral> generalList = new ArrayList<AnuncioGeneral>();
		ArrayList<AnuncioFlash> flashList = new ArrayList<AnuncioFlash>();
		ArrayList<AnuncioIndividualizado> individualizadoList = new ArrayList<AnuncioIndividualizado>();
		ArrayList<AnuncioTematico> tematicoList = new ArrayList<AnuncioTematico>();
		
		AnuncioGeneralDAO anuncioG = new AnuncioGeneralDAO(sqlConfig);
		AnuncioFlashDAO anuncioF = new AnuncioFlashDAO(sqlConfig);
		AnuncioTematicoDAO anuncioT = new AnuncioTematicoDAO(sqlConfig);
		AnuncioIndividualizadoDAO anuncioI = new AnuncioIndividualizadoDAO(sqlConfig);
		
		/* Search by publication date */
		
		if(clave.contains("/")) {
			java.util.Date fecha = new java.util.Date();
			java.text.SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
			
			try {
				
				fecha = fechaAux.parse(clave);
				java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
				flashList = anuncioF.getAnuncioFlashByFecha(fechaSQL);
				generalList = anuncioG.getAnuncioGeneralByFecha(fechaSQL);
				individualizadoList = anuncioI.getAnuncioIndividualizadoByFecha(fechaSQL);
				tematicoList = anuncioT.getAnuncioTematicoByFecha(fechaSQL);
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}catch (DAOException e) {
				
			}
			
						
		}
		
		/* Search by announcements ownner's email  */

		else if(clave.contains("@")){
			
			try {
				generalList = anuncioG.getAnuncioGeneralByPropietario(clave);
				flashList = anuncioF.getAnuncioFlashByPropietario(clave);
				individualizadoList = anuncioI.getAnuncioIndividualizadoByPropietario(clave);
				tematicoList = anuncioT.getAnuncioTematicoByPropietario(clave);

			} catch (DAOException e) {
				
			}
			

		}
		
		/* Search by hobbies */

		else {

			Interes interes = new Interes();
			interes.setInteres(clave);
			try {
				
				tematicoList = anuncioT.getAnuncioTematicoByIntereses(interes);
				
			} catch (DAOException e) {
				
			}
		}

		request.setAttribute("anuncios1",generalList);
		request.setAttribute("anuncios2",flashList);
		request.setAttribute("anuncios3",tematicoList);
		request.setAttribute("anuncios4",individualizadoList);
		request.setAttribute("filtrados", 6);
		
		/* Redirect to inicio.jsp with the announcements found */
		request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
