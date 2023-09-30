package contacto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A main program to show the use of the contacto class
 * @author Jose Antonio Gonzalez Aguilera
 * @author Carlos Revuelto Quero
 * */


public class ContactoMainProgram {
	
	public static void main(String[] args) throws NullPointerException
	{
		Scanner teclado = new Scanner(System.in);
		SimpleDateFormat fechaAux = new SimpleDateFormat("dd/MM/yyyy");

		Contacto contacto = Contacto.getContacto();
		Contacto.fileToArray(args[0]);
		
		
		System.out.println("Aquí se muentran los contactos que hay en la base de datos...");
			contacto.ImprimirContactos();


		System.out.println("\nIntroduce los datos para crear un nuevo contacto:");
		System.out.println("----Introduce el email:");
			contacto.setEmail(teclado.nextLine());
		
		System.out.println("----Introduce el nombre:");
			contacto.setNombre(teclado.nextLine());
		
		System.out.println("----Introduce los apellidos:");
			contacto.setApellidos(teclado.nextLine());
		
		System.out.println("----Introduce la fecha de nacimiento (dd/MM/yyyy):");
		try {
			contacto.setFechaNacimiento(fechaAux.parse(teclado.nextLine()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("----Introduce los intereses (introduce alguno de los siguientes intereses separados por comas y sin espacios:moda, fitness, videojuegos, deporte, cine, series, arquitectura, baile, musica):");
			contacto.setIntereses(teclado.nextLine());
		
		System.out.println("La edad del contacto creado es: "+ contacto.getEdad());
		
		System.out.println("\nSe ha completado el relleno de datos de un contacto, se va a guardar en la base de datos...\n\n");
			contacto.DarDeAlta();
		
		
		System.out.println("Veamos la base de datos como está ahora");
			contacto.ImprimirContactos();
	

		System.out.println("Probemos a actualizar un contacto\n Introduce el campo que quieres modificar");
			String str1 = teclado.nextLine();
		System.out.println("Introduce el email del contacto que deseas modificar ");
			String str2 = teclado.nextLine();
		System.out.println("Introduce el nuevo dato del contacto");
			String str3 = teclado.nextLine();
			
			if(contacto.Actualizar(str1, str2, str3))
			{
				System.out.println("Se ha modificado correctamente");
			}
		
		System.out.print("Comprobemos que se ha actualizado\n");
			contacto.ImprimirContactos();

			
			
		System.out.println("\nProbemos a buscar contactos\nIntroduce el campo por el que quieres buscar");
			str1 = teclado.nextLine();
		System.out.println("Introduce el dato del/los contactos a buscar");
			str2 = teclado.nextLine();
				
		System.out.println("Aquí se muestran los contactos encontrados");
		ArrayList<Contacto> encontrados = new ArrayList<Contacto>();
		encontrados = contacto.Buscar(str1, str2);
		for(int i = 0; i < encontrados.size(); i++)
		{
			System.out.println(encontrados.get(i).ImprimirContacto());
		}
		
		System.out.println("\n\nCerrando programa....");
		
		teclado.close();
	}

}
