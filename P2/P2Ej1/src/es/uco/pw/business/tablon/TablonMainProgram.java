package es.uco.pw.business.tablon;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.business.contacto.Intereses;
import es.uco.pw.data.dao.DAOException;


public class TablonMainProgram {

	public static void main(String[] args) throws DAOException {
		
				GestorContactos gestor = GestorContactos.getGestor();
				Contacto usuario = new Contacto();
				
				File miDir = new File(".");
				String sqlPath = new String();
				String configPath = new String();
				try {
					sqlPath = miDir.getCanonicalPath() + "\\sql.properties";
					configPath = miDir.getCanonicalPath() + "\\config.properties";
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
				AnunciosFactory factory = new AnunciosFactory();
				Tablon tablon = new Tablon(factory);
				tablon.setPaths(sqlPath,configPath);
				gestor.setPaths(sqlPath,configPath);

				/* Variables auxiliares */ 
				@SuppressWarnings("unused")
				String aux = new String();
				ArrayList<Contacto> contactosList = new ArrayList<Contacto>();			
				int opcion = 0;
				Scanner teclado = new Scanner(System.in);
				
				System.out.println("Bienvenido al programa del tablon de anuncios. Ahora disponemos de base de datos!!");
				
				do {
					
					if(opcion != 0)
					{			
						System.out.println("\nBienvenido al gestor de su tablon de anuncios. Introduzca cualquiera de las siguientes opciones:");
						System.out.println("1. Crear anuncio\n2. Publicar anuncio\n3. Editar anuncio\n4. Mostrar tablon de anuncios\n5. Subscribirse a tema de interes\n6. Buscar anuncio\n7. Cambiar de usuario\n8. Salir del programa");
							opcion=teclado.nextInt();
							aux = teclado.nextLine();	
					}
					
					
					
					if(opcion == 0)
					{
						
						System.out.println("Introduzca su email para acceder:");		
							


							contactosList = gestor.BuscarContacto("email", teclado.nextLine());

							if(contactosList.isEmpty()){
							
							System.out.println("El usuario que ha introducido no existe en la base de datos...\n");
							opcion = 0;
							
							}
							
							else{
							
								usuario = contactosList.get(0);//asi tenemos el usuario guardado
								opcion = 20;
							
						}

					}
					
					
					
					if(opcion == 1)//crear anuncio
					{
						int id;
						String titulo;
						String cuerpo;
						Date fechaInicio;
						Date fechaFinal;
						System.out.println("Introduzca el tipo de anuncio que desea crear (General, Flash, Individual o Tematico) o -1 para volver al menu");
				
						String tipoAnuncio = teclado.nextLine();
						
						if(!tipoAnuncio.equals("-1")){
							
							if(tipoAnuncio.equalsIgnoreCase("general")){
								System.out.print("Introduzca el id (numero natural y positivo) del nuevo anuncio flash: ");
									id = teclado.nextInt();
									aux = teclado.nextLine();
									
								if((tablon.buscarAnuncioFlashPorId(id)==null)&&(tablon.buscarAnuncioGeneralPorId(id)==null) 
										&&(tablon.buscarAnuncioIndividualizadoPorId(id) == null)&&(tablon.buscarAnuncioTematicoPorId(id) == null)) {
										
									System.out.print("Introduzca el titulo del nuevo anuncio general: ");
										titulo = teclado.nextLine();
									System.out.print("Introduzca el cuerpo del anuncio: ");
										cuerpo = teclado.nextLine();
									
									AnuncioGeneral anuncioG = factory.createAnuncioGeneral();
									anuncioG.setId(id);
									anuncioG.setPropietario(usuario);
									anuncioG.setTitulo(titulo);
									anuncioG.setCuerpo(cuerpo);		
	
									tablon.guardarAnuncioGeneral(anuncioG);
									
									System.out.println("Se ha creado el anuncio...");
								
								}
								else {
									System.out.println("El id ya corresponde a un anuncio, introduzca otro id. (numero natural y positivo)");
									opcion = 1;
								}	
							}
							
							else if(tipoAnuncio.equalsIgnoreCase("flash")){
								
								String fechaStringI;
								String fechaStringF;
								
								System.out.print("Introduzca el id (numero natural y positivo) del nuevo anuncio flash: ");
									id = teclado.nextInt();
									aux = teclado.nextLine();
									
								if((tablon.buscarAnuncioFlashPorId(id)==null)&&(tablon.buscarAnuncioGeneralPorId(id)==null) 
										&&(tablon.buscarAnuncioIndividualizadoPorId(id) == null)&&(tablon.buscarAnuncioTematicoPorId(id) == null)) {
									
									System.out.print("Introduzca el titulo del nuevo anuncio flash: ");
										titulo = teclado.nextLine();
									System.out.print("Introduzca el cuerpo del anuncio: ");
										cuerpo = teclado.nextLine();
									System.out.print("Introduzca la fecha de publicacion (formato 'dd/MM/yyyy hh:mm:ss'): ");
										fechaStringI = teclado.nextLine();
									System.out.print("Introduzca la fecha de final de publicacion (formato 'dd/MM/yyyy hh:mm:ss'): ");
										fechaStringF = teclado.nextLine();
									
									SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
									
									try {
										fechaInicio = fechaAux.parse(fechaStringI);
										fechaFinal = fechaAux.parse(fechaStringF);
										
										AnuncioFlash anuncioF= factory.createAnuncioFlash();
										anuncioF.setId(id);
										anuncioF.setPropietario(usuario);
										anuncioF.setTitulo(titulo);
										anuncioF.setCuerpo(cuerpo);	
										anuncioF.setFechaInicio(fechaInicio);
										anuncioF.setFechaFinal(fechaFinal);
										
										tablon.guardarAnuncioFlash(anuncioF);
										
									} catch (ParseException e) {
										e.printStackTrace();
									}						
									
									System.out.println("Se ha creado el anuncio...");
								}
								else {
									System.out.println("El id ya corresponde a un anuncio, introduzca otro id. (numero natural y positivo)");
									opcion = 1;
								}	
							}
							
							else if(tipoAnuncio.equalsIgnoreCase("individual")){
															
								System.out.print("Introduzca el id (numero natural y positivo) del nuevo anuncio individualizado: ");
									id = teclado.nextInt();
									aux = teclado.nextLine();
									
								if((tablon.buscarAnuncioFlashPorId(id)==null)&&(tablon.buscarAnuncioGeneralPorId(id)==null) 
										&&(tablon.buscarAnuncioIndividualizadoPorId(id) == null)&&(tablon.buscarAnuncioTematicoPorId(id) == null)) {
									
									System.out.print("Introduzca el titulo del nuevo anuncio: ");
										titulo = teclado.nextLine();
									System.out.print("Introduzca el cuerpo del nuevo anuncio: ");
										cuerpo = teclado.nextLine();
									System.out.print("Introduzca los emails de los destinatarios del anuncio (deben estar registrados en la base de datos) separados por comas (ana@gmail.com, pedro@hotmail.com,...): ");
										String destinatarios = teclado.nextLine();

									AnuncioIndividualizado anuncioI = factory.createAnuncioIndividualizado();
									anuncioI.setId(id);
									anuncioI.setPropietario(usuario);
									anuncioI.setTitulo(titulo);
									anuncioI.setCuerpo(cuerpo);	
									anuncioI.setDestinatarios(destinatarios);
									
									tablon.guardarAnuncioIndividualizado(anuncioI);
									
									System.out.println("Se ha creado el anuncio...");
								}
								else {
									System.out.println("El id ya corresponde a un anuncio, introduzca otro id. (numero natural y positivo)");
									opcion = 1;
								}	
							}
							
							else if(tipoAnuncio.equalsIgnoreCase("tematico")){
								
								ArrayList<Intereses> intereses = new ArrayList<Intereses>();
								
								System.out.print("Introduzca el id (numero natural y positivo) del nuevo anuncio tematico: ");
									id = teclado.nextInt();
									aux = teclado.nextLine();
									
								if((tablon.buscarAnuncioFlashPorId(id)==null)&&(tablon.buscarAnuncioGeneralPorId(id)==null) 
											&&(tablon.buscarAnuncioIndividualizadoPorId(id) == null)&&(tablon.buscarAnuncioTematicoPorId(id) == null)) {
									
									System.out.print("Introduzca el titulo del nuevo anuncio: ");
										titulo = teclado.nextLine();
									System.out.print("Introduzca el cuerpo del anuncio: ");
										cuerpo = teclado.nextLine();
									System.out.print("Introduzca los temas relacionados con el anuncio (moda,fitness,videojuegos,deporte,cine,series,arquitectura,baile,musica): ");
										aux = teclado.nextLine();
										String[] interesesParts = aux.split(",");
									
									for(int i = 0; i < interesesParts.length; i++) {
										
										intereses.add(Intereses.valueOf(interesesParts[i]));
										
									}
									
									AnuncioTematico anuncioT= factory.createAnuncioTematico();
									anuncioT.setId(id);
									anuncioT.setPropietario(usuario);
									anuncioT.setTitulo(titulo);
									anuncioT.setCuerpo(cuerpo);	
									anuncioT.setIntereses(intereses);
	
									tablon.guardarAnuncioTematico(anuncioT);
									
									System.out.println("Se ha creado el anuncio...");
								}
								else {
									System.out.println("El id ya corresponde a un anuncio, introduzca otro id. (numero natural y positivo).");
									opcion = 1;
								}	
							}
							
						}

					}
					
					
					
					if(opcion == 2) {//publicar anuncio
						
						System.out.println("Estos son sus anuncios");
						
						String anuncios = new String();
						
						anuncios = tablon.buscarAnunciosFlashPorPropietario(usuario.getEmail()).toString();
						anuncios = anuncios + tablon.buscarAnunciosGeneralPorPropietario(usuario.getEmail()).toString();
						anuncios = anuncios + tablon.buscarAnunciosIndividualizadoPorPropietario(usuario.getEmail()).toString();
						anuncios = anuncios + tablon.buscarAnunciosTematicoPorPropietario(usuario.getEmail()).toString();
						
						System.out.println(anuncios);
						
						System.out.println("\n\nIntroduzca el tipo del anuncio que desea publicar o -1 para volver al menu");
						String tipo = teclado.nextLine();
						if(!tipo.contains("-1")) {
							System.out.println("Introduzca el id del anuncio que desea publicar");
							int id = teclado.nextInt();
							aux = teclado.nextLine();
							
							if(tipo.equalsIgnoreCase("flash")) {
								tablon.publicarAnuncioFlash(tablon.buscarAnuncioFlashPorId(id));
							}
							else if(tipo.equalsIgnoreCase("general")) {
								tablon.publicarAnuncioGeneral(tablon.buscarAnuncioGeneralPorId(id));
							}
							else if(tipo.equalsIgnoreCase("individualizado")) {
								tablon.publicarAnuncioIndividualizado(tablon.buscarAnuncioIndividualizadoPorId(id));
							}
							else if(tipo.equalsIgnoreCase("tematico")) {
								AnuncioTematico anuncioT = factory.createAnuncioTematico();
								anuncioT = tablon.buscarAnuncioTematicoPorId(id);
								tablon.publicarAnuncioTematico(anuncioT);
							}
							
							System.out.println("Anucio publicado...");
						}
						else {
							opcion = 20;
						}
					}
					
					
					if(opcion == 3) { //editar anuncio
						
						
						System.out.println("\n\nEste es el editor de anuncios. Aquí se muestran sus anuncios");
						
						String anuncios = new String();
						
						anuncios = tablon.buscarAnunciosFlashPorPropietario(usuario.getEmail()).toString();
						anuncios = anuncios + tablon.buscarAnunciosGeneralPorPropietario(usuario.getEmail()).toString();
						anuncios = anuncios + tablon.buscarAnunciosIndividualizadoPorPropietario(usuario.getEmail()).toString();
						anuncios = anuncios + tablon.buscarAnunciosTematicoPorPropietario(usuario.getEmail()).toString();
						
						System.out.println(anuncios);
						
						
						System.out.println("\nIntroduzca el tipo del anuncio que desea editar o -1 para salir");
							String tipo = teclado.nextLine();
						if(!tipo.contentEquals("-1")) {
							System.out.println("Introduzca el id del anuncio que desea editar");
								int id = teclado.nextInt();
								aux = teclado.nextLine();
	
							System.out.println("Introduzca el campo que desea editar");
									String campo = teclado.nextLine();
							System.out.println("Introduzca el nuevo dato");
									String nuevoDato = teclado.nextLine();
									
							if(tipo.equalsIgnoreCase("flash")) {
								if(campo.contains("fecha")||campo.contains("Fecha")){
									
									try {	
	
										SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
										Date fecha = fechaAux.parse(nuevoDato);		
										tablon.editarAnuncioFlash(tablon.buscarAnuncioFlashPorId(id), campo, nuevoDato, fecha);	
										
									} catch (ParseException e) {
										e.printStackTrace();
									}
								}
								else{
										tablon.editarAnuncioFlash(tablon.buscarAnuncioFlashPorId(id), campo, nuevoDato, null);
								}
								
							}
							else if(tipo.equalsIgnoreCase("general")) {
								
								tablon.editarAnuncioGeneral(tablon.buscarAnuncioGeneralPorId(id),campo,nuevoDato);
							}
							else if(tipo.equalsIgnoreCase("individualizado")) {
								
								if(campo.contains("destinatarios")||campo.contains("Destinatarios")) {
									
									String[] stringDestinatarios = nuevoDato.split(",");
									ArrayList<Contacto> nuevos = new ArrayList<Contacto>();
									
									for(int j = 0; j < stringDestinatarios.length; j++) {
										
											nuevos.add(gestor.BuscarContacto("email", stringDestinatarios[j]).get(0));
				
									}
									
									tablon.editarAnuncioIndividualizado(tablon.buscarAnuncioIndividualizadoPorId(id), campo, nuevoDato);
									
									}
							}
							else if(tipo.equalsIgnoreCase("tematico")) {
								
								if(campo.contains("intereses")||campo.contains("Intereses")) {
									
									String[] stringInteresesParts = nuevoDato.split(",");
									ArrayList<Intereses> nuevos = new ArrayList<Intereses>();
									
									for(int j = 0; j < stringInteresesParts.length; j++){
	
										nuevos.add(Intereses.valueOf(stringInteresesParts[j]));
									}
									
									tablon.editarAnuncioTematico(tablon.buscarAnuncioTematicoPorId(id), campo, nuevoDato, nuevos);
								}
								else {
									
									tablon.editarAnuncioTematico(tablon.buscarAnuncioTematicoPorId(id), campo, nuevoDato, null);
								}
	
							}
							
									System.out.println("Anuncio editado...");
						}
					}
						
					
					
					
					if(opcion == 4) {//mostrar tablon general
						
						System.out.println(tablon.tablonUsuarioToString(usuario).toString());
					}
					
					
					
					if(opcion == 5) {//subscribirse a tema de interes
						
						System.out.println("Introduzca todos los temas de interes que quiera el usuario: interes1,interes2,... o -1 para salir");
						String interesAnadir = teclado.nextLine();
						
						if(!interesAnadir.equals("-1")) {
							
							ArrayList <Intereses> interesesContacto = new ArrayList<Intereses>();
							String[] interesAnadirparts = interesAnadir.split(",");
							
							for(int i=0; i<interesAnadirparts.length; i++) {
	
									interesesContacto.add(Intereses.valueOf(interesAnadirparts[i]));
	
							}
								usuario.setIntereses(interesesContacto);
								gestor.ActualizarContacto(usuario);
								System.out.println("Se han cambiado los intereses del usuario...");
							}
						}
					
					
					
					
					if(opcion == 6) {//buscar
						
						int opcion2 = 0;

						do {
							System.out.println("Bienvenido al buscador de anuncios publicados.\n1. Buscar por propietario\n2. Buscar por fecha\n3. Buscar por destinatario\n4. Buscar por interes\n5. Salir");
							opcion2= teclado.nextInt();
							aux = teclado.nextLine();
	
								
							if(opcion2 == 1) {
								
								System.out.println("Introduzca el email del propietario que quiere buscar: \n");
								ArrayList<Contacto> auxContactos = gestor.BuscarContacto("email", teclado.nextLine());
								if(auxContactos.isEmpty())
								{
									System.out.println("Usuario introducido no existe en la base de datos, por favor introduzca otro....\n");
									opcion2 = 1;
								}
								else {
									ArrayList<String> tablonUsuario = new ArrayList<String>();
									
									for(AnuncioFlash i: tablon.buscarAnunciosFlashPorPropietario(auxContactos.get(0).getEmail())) {
										tablonUsuario.add(i.toString());
									}
									for(AnuncioGeneral i: tablon.buscarAnunciosGeneralPorPropietario(auxContactos.get(0).getEmail())) {
										tablonUsuario.add(i.toString());
									}
									for(AnuncioIndividualizado i: tablon.buscarAnunciosIndividualizadoPorPropietario(auxContactos.get(0).getEmail())) {
										tablonUsuario.add(i.toString());
									}
									
									for(AnuncioTematico i: tablon.buscarAnunciosTematicoPorPropietario(auxContactos.get(0).getEmail())) {
										tablonUsuario.add(i.toString());
									}
									
									System.out.println("Aquí los anuncios de " + auxContactos.get(0).getEmail()+ ": \n"+ tablonUsuario);
								}
				
							}
									
							else if(opcion2 == 2) {
										
								System.out.println("Introduzca la fecha(dd/MM/yyyy) por la que quiere filtrar:");
								Date fechaAnuncio = new Date();
								SimpleDateFormat fechaAux = new SimpleDateFormat ("dd/MM/yyyy");
									String fechaFiltro = teclado.nextLine();
								
								try {
										
									fechaAnuncio = fechaAux.parse(fechaFiltro);
									
									ArrayList<String> tablonUsuario = new ArrayList<String>();
									
									for(AnuncioFlash i: tablon.buscarAnunciosFlashPorFecha(fechaAnuncio)) {
										tablonUsuario.add(i.toString());
									}
									for(AnuncioGeneral i: tablon.buscarAnunciosGeneralPorFecha(fechaAnuncio)) {
										tablonUsuario.add(i.toString());
									}
									for(AnuncioIndividualizado i: tablon.buscarAnunciosIndividualizadoPorFecha(fechaAnuncio)) {
										tablonUsuario.add(i.toString());
									}
									
									for(AnuncioTematico i: tablon.buscarAnunciosTematicoPorFecha(fechaAnuncio)) {
										tablonUsuario.add(i.toString());
									}
									
									System.out.println("Aquí los anuncios de la fecha " + fechaAnuncio + ": \n"+ tablonUsuario);
										
								}catch(ParseException e) {
											
									e.printStackTrace();
								}
									
								}
								
							else if(opcion2 == 3) {
									
									System.out.println("Introduzca el email del destinatario que quiera buscar: ");
				
									String destinatario= teclado.nextLine();
										
									ArrayList<String> tablonUsuario = new ArrayList<String>();
									
									for(AnuncioFlash i: tablon.buscarAnunciosFlashPorDestinatario(destinatario)) {
										tablonUsuario.add(i.toString());
									}
									for(AnuncioGeneral i: tablon.buscarAnunciosGeneralPorDestinatario(destinatario)) {
										tablonUsuario.add(i.toString());
									}
									for(AnuncioIndividualizado i: tablon.buscarAnunciosIndividualizadoPorDestinatario(destinatario)) {
										tablonUsuario.add(i.toString());
									}
									
									for(AnuncioTematico i: tablon.buscarAnunciosTematicoPorDestinatario(destinatario)) {
										tablonUsuario.add(i.toString());
									}
									
									System.out.println("Aquí los anuncios de  " + destinatario + ": \n"+ tablonUsuario);

								}
									
							else if(opcion2 == 4) {
										
									System.out.println("Introduzca el interes a buscar (moda,fitness,videojuegos,deporte,cine,series,arquitectura,baile,musica,deportes): ");
									
									String interes= teclado.nextLine();
									
									ArrayList<String> tablonUsuario = new ArrayList<String>();
									
									for(AnuncioTematico i: tablon.buscarAnunciosPorIntereses(Intereses.valueOf(interes))) {
										tablonUsuario.add(i.toString());
									}
									System.out.println("Aquí se muestran los anuncios del interes: " + interes+ "\n" + tablonUsuario);
								}
						}while(opcion2 != 5);
					}
					
					
					
					if(opcion == 7) {//cambiar de usuario
						
						System.out.println("Sesion finalizada\n");
						opcion = 0;
					}

				}while(opcion !=8);
					
				teclado.close();
			}
	}
