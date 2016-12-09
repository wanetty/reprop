package Prop;

//Creador: Eduard Gonzalez Moreno


import java.io.IOException;
import java.util.Scanner;


public class palabra_driver {

	private static Scanner opcion;
	private static Scanner texto;


	public static void main (String[] args) throws IOException{
		int var;
		String palabra;
		texto = new Scanner(System.in);
		opcion = new Scanner(System.in);
		Palabra pal = new Palabra();

		do{
			System.out.println("1. Añadir palabra.");
			System.out.println("2. Modificar Palabra");
			System.out.println("3. Comparar Palabras");
			System.out.println("4. Consultar si es funcional");
			System.out.println("5. Devuelve palabra en String.");
			System.out.println("6. Escribe palabra.");
			System.out.println("7. Tamaño palabra.");
			System.out.println("0. Salir del driver.");
			var = opcion.nextInt();
			switch(var){

			case 1:
				boolean primero = true;
				while(pal.palabra() == null || primero){
					if (primero) primero = false;
					System.out.println("Intr1oduce una sola palabra. Sin ningun espacio.");
					palabra = texto.nextLine();
					pal = new Palabra(palabra);
					if (pal.palabra() != null ) System.out.println("Introducida correctamente.");
					else System.out.println("Mal introducida, vuelve a escribir la palabra");
				}
				break;
			case 2:
				boolean sese = true;
				while(pal.palabra() == null || sese){
					if (sese) sese = false;
					System.out.println("Intr1oduce una sola palabra. Sin ningun espacio.");
					palabra = texto.nextLine();
					pal.modificar(palabra);
					if (pal.palabra() != null ) System.out.println("Introducida correctamente.");
					else System.out.println("Mal introducida, vuelve a escribir la palabra");
				}
				break;
				
			case 3:
				System.out.println("Introduce una palabra con la que comparar.");
				palabra = texto.nextLine();
				if(pal.son_iguales(palabra)) System.out.println("Son iguales");
				else System.out.println("No són iguales");
				break;
			case 4:
				System.out.print("La palabra "+ "\""+pal.palabra()+"\"");
				if(pal.esfuncional()) System.out.println(" es funcional");
				else System.out.println(" no es funcional");
				break;
			case 5:
				System.out.println("La palabra es "+ "\""+pal.palabra()+"\"");
				break;
			case 6:	
				System.out.println("La palabra "+ "\""+pal.palabra()+"\"");
				break;
			case 7:	
				System.out.println("La palabra "+ "\""+pal.palabra()+"\" tiene como tamaño "+ pal.midapalabra());
				break;
			}

		}while(var!= 0);


		//switch


	}
}
