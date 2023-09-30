package tablon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import contacto.Contacto;
import contacto.Intereses;


public class TablonMainProgram {

	public static void main(String[] args) {
		
				Scanner teclado = new Scanner(System.in);
		
				Contacto usuario = Contacto.getContacto();
				Contacto.fileToArray(args[0]);
				ArrayList<Contacto> contactoAuxList = new ArrayList<Contacto>();
				
				String aux = new String();
				
				AnunciosFactory factory = new AnunciosFactory();
				Tablon tablon = new Tablon(factory);
				tablon.crearAnuncios();	
							
				int opcion = 0;
				
				System.out.println("Bienvenido al programa del tablon de anuncios.");
				
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
							aux = teclado.nextLine();
							contactoAuxList = usuario.Buscar("email", aux);

							if(contactoAuxList.isEmpty()){
							
							System.out.println("El usuario que ha introducido no existe en la base de datos...\n");
							opcion = 0;
							
							}
							
							else{
							
								usuario = contactoAuxList.get(0);//asi tenemos el usuario guardado
								opcion = 20;
							
						}

					}
					
					
					
					if(opcion == 1)//crear anuncio
					{
						
						String titulo;
						String cuerpo;
						Date fechaInicio;
						Date fechaFinal;
						System.out.println("Introduzca el tipo de anuncio que desea crear (General, Flash, Individual o Tematico) o -1 para volver al menu");
				
						String tipoAnuncio = teclado.nextLine();
						
						if(!tipoAnuncio.equals("-1")){
							
							if(tipoAnuncio.equalsIgnoreCase("general")){
								
								System.out.print("Introduzca el titulo del nuevo anuncio general:");
									titulo = teclado.nextLine();
								System.out.println("Introduzca el cuerpo del anuncio:");
									cuerpo = teclado.nextLine();
								
								AnuncioGeneral anuncioG = factory.createAnuncioGeneral();
								anuncioG.setPropietario(usuario);
								anuncioG.setTitulo(titulo);
								anuncioG.setCuerpo(cuerpo);		

								tablon.anunciosGenerales.add(anuncioG);
								
								System.out.println("Se ha creado el anuncio...");
							}
							
							else if(tipoAnuncio.equalsIgnoreCase("flash")){
								
								String fechaStringI;
								String fechaStringF;
								
								System.out.print("Introduzca el titulo del nuevo anuncio flash:");
									titulo = teclado.nextLine();
								System.out.println("Introduzca el cuerpo del anuncio:");
									cuerpo = teclado.nextLine();
								System.out.print("Introduzca la fecha de publicacion (formato 'dd/MM/yyyy hh:mm:ss'):");
									fechaStringI = teclado.nextLine();
								System.out.print("Introduzca la fecha de final de publicacion (formato 'dd/MM/yyyy hh:mm:ss'):");
									fechaStringF = teclado.nextLine();
								
								SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
								
								try {
									fechaInicio = fechaAux.parse(fechaStringI);
									fechaFinal = fechaAux.parse(fechaStringF);
									
									AnuncioFlash anuncioF= factory.createAnuncioFlash();
									anuncioF.setPropietario(usuario);
									anuncioF.setTitulo(titulo);
									anuncioF.setCuerpo(cuerpo);	
									anuncioF.setFechaInicio(fechaInicio);
									anuncioF.setFechaFinal(fechaFinal);
									tablon.anunciosFlash.add(anuncioF);
								} catch (ParseException e) {
									e.printStackTrace();
								}						
								
								System.out.println("Se ha creado el anuncio...");
							}
							
							else if(tipoAnuncio.equalsIgnoreCase("individual")){
								
								ArrayList<Contacto> destinatarios = new ArrayList<Contacto>();
								ArrayList<Contacto> auxList = new ArrayList<Contacto>();
								
								System.out.print("Introduzca el titulo del nuevo anuncio individualizado:");
									titulo = teclado.nextLine();
								System.out.println("Introduzca el cuerpo del anuncio:");
									cuerpo = teclado.nextLine();
								System.out.println("Introduzca los emails de los destinatarios del anuncio separados por comas (ana@gmail.com, pedro@hotmail.com,...):");
									String[] destinatariosParts = teclado.nextLine().split(",");
								
								for(int i = 0; i< destinatariosParts.length; i++)
								{
									auxList = usuario.Buscar("email", destinatariosParts[i]);
									destinatarios.add(auxList.get(0));
								}
								
								AnuncioIndividualizado anuncioI= factory.createAnuncioIndividualizado();
								anuncioI.setPropietario(usuario);
								anuncioI.setTitulo(titulo);
								anuncioI.setCuerpo(cuerpo);	
								anuncioI.setDestinatarios(destinatarios);
								
								tablon.anunciosIndividualizados.add(anuncioI);
								
								System.out.println("Se ha creado el anuncio...");
							}
							
							else if(tipoAnuncio.equalsIgnoreCase("tematico")){
								
								ArrayList<Intereses> intereses = new ArrayList<Intereses>();
								System.out.print("Introduzca el titulo del nuevo anuncio tematico:");
									titulo = teclado.nextLine();
								System.out.println("Introduzca el cuerpo del anuncio:");
									cuerpo = teclado.nextLine();
								System.out.println("Introduzca los temas relacionados con el anuncio (moda,fitness,videojuegos,deporte,cine,series,arquitectura,baile,musica,deportes):");
									String[] interesesParts = teclado.nextLine().split(",");
								
								for(int i = 0; i < interesesParts.length; i++) {
									
									intereses.add(Intereses.valueOf(interesesParts[0]));
									
								}
								
								AnuncioTematico anuncioT= factory.createAnuncioTematico();
								anuncioT.setPropietario(usuario);
								anuncioT.setTitulo(titulo);
								anuncioT.setCuerpo(cuerpo);	
								anuncioT.setIntereses(intereses);

								tablon.anunciosTematicos.add(anuncioT);
								
								System.out.println("Se ha creado el anuncio...");
							}
							
						}

					}
					
					
					
					if(opcion == 2) {//publicar anuncio
						
						System.out.println("Estos son sus anuncios");
						
						for(int i = 0; i < tablon.anunciosFlash.size(); i++){
							
							if(tablon.anunciosFlash.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosFlash.get(i).toString());
								
							}
						}
						for(int i = 0; i < tablon.anunciosGenerales.size(); i++){
							
							if(tablon.anunciosGenerales.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosGenerales.get(i).toString());
								
							}
						}
						for(int i = 0; i < tablon.anunciosTematicos.size(); i++){
							
							if(tablon.anunciosTematicos.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosTematicos.get(i).toString());
							}
						}
						for(int i = 0; i < tablon.anunciosIndividualizados.size(); i++){
							
							if(tablon.anunciosIndividualizados.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosIndividualizados.get(i).toString());
							}
						}
						
						
						System.out.println("\n\nIntroduzca el id del anuncio que desea publicar o -1 para volver al menu");
						int id = teclado.nextInt();
						aux = teclado.nextLine();
						
						if(id != -1 ) {
							
							for(int i = 0; i < tablon.anunciosFlash.size(); i++){
								
								if(tablon.anunciosFlash.get(i).getId() == id){
									
									tablon.publicarAnuncioFlash(tablon.anunciosFlash.get(i));
									System.out.println("Se ha publicado el anuncio...");
									id = -2;
								}
							}
							for(int i = 0; i < tablon.anunciosGenerales.size(); i++){
								
								if(tablon.anunciosGenerales.get(i).getId() == id) {
									
									tablon.publicarAnuncioGeneral(tablon.anunciosGenerales.get(i));
									System.out.println("Se ha publicado el anuncio...");
									id = -2;
								}
							}
							for(int i = 0; i < tablon.anunciosTematicos.size(); i++){
								
								if(tablon.anunciosTematicos.get(i).getId() == id){
									
									tablon.publicarAnuncioTematico(tablon.anunciosTematicos.get(i));
									System.out.println("Se ha publicado el anuncio...");
									id = -2;
								}
							}
							for(int i = 0; i < tablon.anunciosIndividualizados.size(); i++){
								
								if(tablon.anunciosIndividualizados.get(i).getId() == id){
									
									tablon.publicarAnuncioIndividualizado(tablon.anunciosIndividualizados.get(i));
									System.out.println("Se ha publicado el anuncio...");
									id = -2;
								}
							}
							if(id != -2) {
								System.out.println("No se ha podido publicar el anuncio...");
							}
						}
					}
					
					
					if(opcion == 3) { //editar anuncio
						
						System.out.println("\n\nEste es el editor de anuncios. Aqu� se muestran sus anuncios");
						
						for(int i = 0; i < tablon.anunciosFlash.size(); i++){
							
							if(tablon.anunciosFlash.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosFlash.get(i).toString());
							}
						}
						for(int i = 0; i < tablon.anunciosGenerales.size(); i++){
							
							if(tablon.anunciosGenerales.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosGenerales.get(i).toString());
							}
						}
						for(int i = 0; i < tablon.anunciosTematicos.size(); i++){
							
							if(tablon.anunciosTematicos.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosTematicos.get(i).toString());
							}
						}
						for(int i = 0; i < tablon.anunciosIndividualizados.size(); i++){
							
							if(tablon.anunciosIndividualizados.get(i).getPropietario().equals(usuario)){
								
								System.out.println(tablon.anunciosIndividualizados.get(i).toString());
							}
						}
						
						
						System.out.println("\nIntroduzca el id del anuncio que desea editar o -1 para salir");
							int id = teclado.nextInt();
							aux = teclado.nextLine();
							if(id != -1) {
								System.out.println("Introduzca el campo que desea editar");
									String campo = teclado.nextLine();
								System.out.println("Introduzca el nuevo dato");
									String nuevoDato = teclado.nextLine();
								
								for(int i = 0; i < tablon.anunciosFlash.size(); i++){
									
									if(tablon.anunciosFlash.get(i).getId()==id){		
										
										if(campo.contains("fecha")||campo.contains("Fecha")){
											
											try {	

												SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");
												Date fecha = fechaAux.parse(nuevoDato);		
												tablon.editarAnuncio(tablon.anunciosFlash.get(i), campo, nuevoDato, fecha);	
												
											} catch (ParseException e) {
												e.printStackTrace();
											}
										}
										else {
											
												tablon.editarAnuncio(tablon.anunciosFlash.get(i), campo, nuevoDato, new Date());
												
										}	
									}
								}
						
								
								for(int i = 0; i < tablon.anunciosGenerales.size(); i++){
									
									if(tablon.anunciosGenerales.get(i).getId()==id){
										
										tablon.editarAnuncio(tablon.anunciosGenerales.get(i),campo,nuevoDato);
									}
								}
								
								for(int i = 0; i < tablon.anunciosTematicos.size(); i++){
									
									if(tablon.anunciosTematicos.get(i).getId() == id){
										
										if(campo.contains("intereses")||campo.contains("Intereses")) {
											
											String[] stringInteresesParts = nuevoDato.split(",");
											ArrayList<Intereses> nuevos = new ArrayList<Intereses>();
											for(int j = 0; j < stringInteresesParts.length; j++){
			
												nuevos.add(Intereses.valueOf(stringInteresesParts[i]));
											}
											
											tablon.editarAnuncio(tablon.anunciosTematicos.get(i), campo, nuevoDato, nuevos);
										}
										else {
											
											tablon.editarAnuncio(tablon.anunciosTematicos.get(i), campo, nuevoDato, null);
										}
									}	
								}
								
								for(int i = 0; i < tablon.anunciosIndividualizados.size(); i++){
									
									if(tablon.anunciosIndividualizados.get(i).getId() == id){
										
										if(campo.contains("destinatarios")||campo.contains("Destinatarios")) {
											
											String[] stringDestinatarios = nuevoDato.split(",");
											ArrayList<Contacto> nuevos = new ArrayList<Contacto>();
											
											for(int j = 0; j < stringDestinatarios.length; j++) {
												
													nuevos.add(usuario.Buscar("email", stringDestinatarios[j]).get(0));
						
												}
											tablon.editarAnuncio(tablon.anunciosIndividualizados.get(i), campo, nuevoDato, nuevos);
											
											}
										
										}
								
										else {
											
											tablon.editarAnuncio(tablon.anunciosTematicos.get(i), campo, nuevoDato, null);
										}
									}
								}	
								
								System.out.println("Anuncio editado...");
					}
						
					
					
					
					if(opcion == 4) {//mostrar tablon general
						
						ArrayList<String> tablonusuario = new ArrayList<String>();
						
						for(int i=0; i<tablon.anunciosGenerales.size(); i++) {
							
							if( tablon.anunciosGenerales.get(i).getFase().equals(Fases.publicado) ) {
								
								tablonusuario.add(tablon.anunciosGenerales.get(i).toString());
							}
						}
						
						System.out.println("Anuncios generales\n");
							tablon.LeerTablon(tablonusuario);
							tablonusuario.clear();
						
						for(int i=0; i<tablon.anunciosIndividualizados.size(); i++) {
							
							if(tablon.anunciosIndividualizados.get(i).getPropietario().getEmail().equalsIgnoreCase(usuario.getEmail()))
							{
								tablonusuario.add(tablon.anunciosIndividualizados.get(i).toString());
							}
														
							if( (tablon.anunciosIndividualizados.get(i).getDestinatarios().contains(usuario)) && (tablon.anunciosIndividualizados.get(i).getFase().equals(Fases.publicado)) ) {
								
								tablonusuario.add(tablon.anunciosIndividualizados.get(i).toString());
							}
						}
						
						System.out.println("Anuncios individualizados\n");
							tablon.LeerTablon(tablonusuario);
							tablonusuario.clear();
						
						for(int i=0; i<tablon.anunciosFlash.size(); i++) {
							
							if( tablon.anunciosFlash.get(i).getFase().equals(Fases.publicado) ) {
								
								tablonusuario.add(tablon.anunciosFlash.get(i).toString());
							}
						}
						
						System.out.println("Anuncios Flash\n");
							tablon.LeerTablon(tablonusuario);
							tablonusuario.clear();
						
						for(int i=0; i<tablon.anunciosTematicos.size(); i++) {
							
							for(int j = 0; j < usuario.getIntereses().size(); j++) {
								for(int k = 0; k< tablon.anunciosTematicos.get(i).getIntereses().size(); k++) {
									if( (tablon.anunciosTematicos.get(i).getIntereses().contains(usuario.getIntereses().get(j))) && (tablon.anunciosTematicos.get(i).getFase().equals(Fases.publicado)) ) {
										
										tablonusuario.add(tablon.anunciosTematicos.get(i).toString());
									}
								}
								
							}
							
						}
						
						System.out.println("Anuncios Tematicos\n");
						tablon.LeerTablon(tablonusuario);
						tablonusuario.clear();
					}
					
					
					
					if(opcion == 5) {//subscribirse a tema de interes
						
						System.out.println("Introduzca los nuevos temas de interes: interes1,interes2,... o -1 para salir");
						String interesAnadir = teclado.nextLine();
						
						if(!interesAnadir.equals("-1")) {
							
							ArrayList <Intereses> interesesContacto = new ArrayList<Intereses>();
								interesesContacto = usuario.getIntereses();
							String[] interesAnadirparts = interesAnadir.split(",");
							
							for(int i=0; i<interesAnadirparts.length; i++) {
	
									interesesContacto.add(Intereses.valueOf(interesAnadirparts[i]));
	
							}
								System.out.println("Se han cambiado los intereses del usuario...");
							}
						}
					
					
					
					
					if(opcion == 6) {//buscar
						
						int opcion2 = 0;

						do {
							System.out.println("Bienvenido al buscador de auncios publicados.\n1. Buscar por propietario\n2. Buscar por fecha\n3. Buscar por destinatarios\n4. Buscar por intereses\n5. Salir");
							opcion2= teclado.nextInt();
							aux = teclado.nextLine();
	
								
							if(opcion2 == 1) {
								
								System.out.println("Introduzca el email del propietario que quiere buscar: \n");
								ArrayList<Contacto> auxContactos = usuario.Buscar("email", teclado.nextLine());
								if(auxContactos.isEmpty())
								{
									System.out.println("Usuario introducido no existe en la base de datos, por favor introduzca otro....\n");
									opcion2 = 1;
								}
								else {
									System.out.println("Aqu� se muestra el anuncio buscado:\n" + tablon.buscarAnuncioPropietario(auxContactos.get(0))+ "\n");
								}
				
								}
									
							else if(opcion2 == 2) {
										
								System.out.println("Introduzca la fecha(dd/MM/yyyy) por la que quiere filtrar:");
								Date fechaAnuncio = new Date();
								SimpleDateFormat fechaAux = new SimpleDateFormat ("dd/MM/yyyy");
									String fechaFiltro = teclado.nextLine();
								
								try {
										
									fechaAnuncio = fechaAux.parse(fechaFiltro);
									System.out.println("Aqu� se muestra el anuncio buscado:\n" +tablon.buscarAnuncioFecha(fechaAnuncio)+"\n");
										
								}catch(ParseException e) {
											
									e.printStackTrace();
								}
									
								}
								
							else if(opcion2 == 3) {
									
									System.out.println("Introduzca el email de todos aquellos destinatarios que quiera buscar: email1,email2...");
				
									String[] destinatariosABuscarparts = teclado.nextLine().split(",");
									
									ArrayList<Contacto> auxContactos = Contacto.getContactos();
									ArrayList<Contacto> destinatarios = new ArrayList<Contacto>();
										
									for(int i=0; i<destinatariosABuscarparts.length; i++) {
											
										for(int j=0; j<auxContactos.size(); j++) {
											
											if(destinatariosABuscarparts[i].equalsIgnoreCase(auxContactos.get(j).getEmail())) {
													
												destinatarios.add(auxContactos.get(j));
											}
										}
									}
									
									System.out.println("Aqu� se muestra el anuncio buscado:\n" +tablon.buscarAnuncioDestinatarios(destinatarios)+"\n");
								}
									
							else if(opcion2 == 4) {
										
									System.out.println("Introduzca los intereses a buscar: interes1,interes2...");
									String[] interesABuscarparts = teclado.nextLine().split(",");
									ArrayList<Intereses> interesesbuscar = new ArrayList<Intereses>();
										
									for(int i=0; i<interesABuscarparts.length; i++) {
											
										if(!interesABuscarparts[i].equals(" ")) {
												
											interesesbuscar.add(Intereses.valueOf(interesABuscarparts[i]));
										}
									}
										
									System.out.println("Aqu� se muestra el anuncio buscado:\n" +tablon.buscarAnuncioIntereses(interesesbuscar)+"\n");
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
