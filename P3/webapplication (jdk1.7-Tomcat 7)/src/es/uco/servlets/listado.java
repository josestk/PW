package es.uco.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.bussiness.anuncios.AnuncioFlash;
import es.uco.pw.bussiness.anuncios.AnuncioGeneral;
import es.uco.pw.bussiness.anuncios.AnuncioIndividualizado;
import es.uco.pw.bussiness.anuncios.AnuncioTematico;
import es.uco.pw.data.dao.anuncios.AnuncioFlashDAO;
import es.uco.pw.data.dao.anuncios.AnuncioGeneralDAO;
import es.uco.pw.data.dao.anuncios.AnuncioIndividualizadoDAO;
import es.uco.pw.data.dao.anuncios.AnuncioTematicoDAO;

/**
 * Servlet implementation class listado
 */
public class listado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sqlConfig = new String();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listado() {
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
		// TODO Auto-generated method stub
		int listado = 0;
		int filtrado = 0;
		int nuevo = 0;
		
		if(request.getParameter("Listado")!=null) {
			
			ArrayList<AnuncioGeneral> anunciosGenrUsuario = new ArrayList<AnuncioGeneral>();
			ArrayList<AnuncioFlash> anunciosFlashUsuario = new ArrayList<AnuncioFlash>();
			ArrayList<AnuncioTematico> anunciosTemUsuario = new ArrayList<AnuncioTematico>();
			ArrayList<AnuncioIndividualizado> anunciosIndiUsuario = new ArrayList<AnuncioIndividualizado>();
			listado = Integer.parseInt(String.valueOf(request.getParameter("Listado")));
			AnuncioGeneralDAO Generales = new AnuncioGeneralDAO(sqlConfig);
			AnuncioFlashDAO Flashs = new AnuncioFlashDAO(sqlConfig);
			AnuncioIndividualizadoDAO Individuales = new AnuncioIndividualizadoDAO(sqlConfig);
			AnuncioTematicoDAO Tematicos = new AnuncioTematicoDAO(sqlConfig);
			
			/* The chosen option for listed announcements is "editado" */
			if(listado == 1) {
			
				try {
				anunciosGenrUsuario = Generales.getAnuncioGeneralByFase("editado");
				anunciosFlashUsuario = Flashs.getAnuncioFlashByFase("editado");
				anunciosTemUsuario = Tematicos.getAnuncioTematicoByFase("editado");
				anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFase("editado");
				}catch(Exception e) {
					
					e.printStackTrace();
				}
			
			}
			
			/* The chosen option for listed announcements is "espera" */
			else if(listado == 2) {
				
				try {
					anunciosGenrUsuario = Generales.getAnuncioGeneralByFase("espera");
					anunciosFlashUsuario = Flashs.getAnuncioFlashByFase("espera");
					anunciosTemUsuario = Tematicos.getAnuncioTematicoByFase("espera");
					anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFase("espera");
					}catch(Exception e) {
						
						e.printStackTrace();
					}
				
			}

			/* The chosen option for listed announcements is "publicado" */
			else if(listado == 3) {
				
				try {
					anunciosGenrUsuario = Generales.getAnuncioGeneralByFase("publicado");
					anunciosFlashUsuario = Flashs.getAnuncioFlashByFase("publicado");
					anunciosTemUsuario = Tematicos.getAnuncioTematicoByFase("publicado");
					anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFase("publicado");
					}catch(Exception e) {
						
						e.printStackTrace();
					}
				
			}
			
			/* The chosen option for listed announcements is "archivado" */
			else if(listado == 4) {
				
				try {
					anunciosGenrUsuario = Generales.getAnuncioGeneralByFase("archivado");
					anunciosFlashUsuario = Flashs.getAnuncioFlashByFase("archivado");
					anunciosTemUsuario = Tematicos.getAnuncioTematicoByFase("archivado");
					anunciosIndiUsuario = Individuales.getAnuncioIndividualizadoByFase("archivado");
					}catch(Exception e) {
						
						e.printStackTrace();
					}
			}
			
			request.setAttribute("anuncios1",anunciosGenrUsuario);
			request.setAttribute("anuncios2",anunciosFlashUsuario);
			request.setAttribute("anuncios3",anunciosTemUsuario);
			request.setAttribute("anuncios4",anunciosIndiUsuario);
			request.setAttribute("listados", listado);
			request.setAttribute("filtrados", filtrado);
			request.setAttribute("inicio", nuevo);

			/* Redirect to "inicio.jsp" with the announcements found */
			request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
			
		}else {
			
			request.getRequestDispatcher("/mvc/view/user/inicio.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
