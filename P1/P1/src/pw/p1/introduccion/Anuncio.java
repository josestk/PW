package pw.p1.introduccion;

import java.util.Date;

public class Anuncio {
	
	/* Attributes */
	
	private String titulo;
	
	private String cuerpo;
	
	private String usuario;
	
	private Date fecha;
	
	/* Constructor */
	
	/**
	 * Empty (default) constructor
	 **/
	
	public Anuncio() {
		this.fecha = new Date(System.currentTimeMillis());
	}
	
	/** Parameterized constructor 
	 * 
	 * @param titulo
	 * @param cuerpo
	 * @param usuario
	 **/
	
	public Anuncio(String titulo, String cuerpo, String usuario) {
		this.fecha = new Date(System.currentTimeMillis());
	}

	/* Getters and setters */
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/* Other methods */
	
	@Override
	public String toString() {
		return "Anuncio [titulo=" + titulo + ", cuerpo=" + cuerpo + ", usuario=" + usuario + ", fecha=" + fecha + "]";
	}
	

	
}
