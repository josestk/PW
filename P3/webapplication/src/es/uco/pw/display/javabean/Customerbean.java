package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import es.uco.pw.bussiness.contacto.Intereses;

public class Customerbean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email = "";
	private String password = "";
	private ArrayList<Intereses> temas = new ArrayList<Intereses>();
	private Date Fecha = null;
	private String Filtro;
	private int nIntentos = 0;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String login) {
		this.email = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	
	public String getFiltro() {
		return Filtro;
	}

	public void setFiltro(String filtros) {
		
		this.Filtro = filtros;
	}

	public ArrayList<Intereses> getTemas() {
		return temas;
	}

	public void setTemas(ArrayList<Intereses> temas) {
		this.temas = temas;
	}

	public int getnIntentos() {
		return nIntentos;
	}

	public void setnIntentos(int nIntentos) {
		this.nIntentos = nIntentos;
	}



}
