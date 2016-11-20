//Creador: Gerard Heredia

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver_documento {

	private static Scanner codigo;
	private static Scanner scanner;
	private static Scanner aux;

	public static void main(String[] args) throws IOException {

		System.out.println("Bienvenido/a al driver de la clase Documento. Por favor dé de alta un documento.");

		Documento d = new Documento();
		Frase f = new Frase();
		Palabra pal = new Palabra();
		int accion;
		int k;
		boolean hay_doc = false;
		String s;
		codigo = new Scanner(System.in);
		scanner = new Scanner(System.in);
		aux = new Scanner(System.in);

		do {
			System.out.println("1. Alta documento.");
			System.out.println("2. Consulta título.");
			System.out.println("3. Consulta autor.");
			System.out.println("4. Consulta tema.");
			System.out.println("5. Consulta fecha.");
			System.out.println("6. Consulta el número de frases del contenido.");
			System.out.println("7. Consulta contenido.");
			System.out.println("8. Consulta la frase en la posición i.");
			System.out.println("9. Consulta el vector de frecuencias del documento.");
			System.out.println("10. Consulta el número total de palabras no funcionales del contenido del documento.");
			System.out.println("11. Borrar la primera aparición de la palabra p en la frase i del contenido.");
			System.out.println("12. Añadir una palabra al final de la frase i del contenido.");
			System.out.println("13. Imprime el documento por pantalla.");
			System.out.println("0. Salir del driver.");

			accion = codigo.nextInt();
			switch (accion){

			case 1:
				System.out.println("Escribe la ruta del documento.");
				s = scanner.nextLine();
				File fi = new File(s);
				if (!fi.isFile()) System.out.println("No existe el documento.");
				else {
					d = new Documento(s);
					hay_doc = true;
				}
				break;
			case 2:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					f = d.get_titulo();
					System.out.println("El título es: ");
					f.escribirfrase();
					System.out.println("");
				}
				break;
			case 3:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					f = d.get_autor();
					System.out.println("El autor es: ");
					f.escribirfrase();
				}
				break;
			case 4:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					f = d.get_tema();
					System.out.println("El tema es: ");
					f.escribirfrase();
				}
				break;
			case 5:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("La fecha es: ");
					System.out.println(d.get_fecha_date());
				}
				break;
			case 6:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("El número de frases es: ");
					System.out.println(d.get_num_frases());
				}
				break;
			case 7:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("El contenido del documento es: ");
					ArrayList<Frase> c = new ArrayList<Frase>();
					c = d.get_contenido();
					for (int i = 0; i < c.size(); ++i) {
						c.get(i).escribirfrase();
					}
				}
				break;
			case 8:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("Inserte un natural i, el número 0 equivale a la primera frase.");
					k = scanner.nextInt();
					f = d.get_frase(k);
					if(f == null) System.out.println("Natural i incorrecto, no se ha podido realizar la accion");
					else {System.out.println("La frase en la posición "+ k + " es: ");
					f.escribirfrase();}
				}
				break;
			case 9:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					Map<String, Double> a = new HashMap<String,Double>();
					a = d.get_pesos();
					System.out.println("El vector de frecuencias es: "+ a);
				}
				break;
			case 10:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("El número total de palabras no funcionales es: ");
					System.out.println(d.get_total_words());
				}
				break;

			case 11:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("Escriba la palabra.");
					s = aux.nextLine();
					pal = new Palabra(s);
					System.out.println("Escriba el número de frase, el número 0 equivale a la primera.");
					k = scanner.nextInt();
					if (k < 0 || k >= d.get_contenido().size()) {
						System.out.println("Número de frase incorrecto.");
					}
					else {
						int aux = d.get_contenido().get(k).midafrase();
						d.borrar_palabra(k, pal);
						if (! (aux == d.get_contenido().get(k).midafrase()))
							System.out.println("Primera aparición de la palabra "+ pal.palabra() + " de la frase número " +k+" borrada.");
					}
				}
				break;
			case 12:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else {
					System.out.println("Escriba la palabra.");
					s = aux.nextLine();
					pal = new Palabra(s);
					System.out.println("Escriba el número de frase, el número 0 equivale a la primera.");
					k = scanner.nextInt();
					if (k < 0 || k >= d.get_contenido().size()) {
						System.out.println("Número de frase incorrecto.");
					}
					else {
						d.anyadir_palabra(k, pal);
						System.out.println("Palabra "+ pal.palabra()+" añadida al final de la frase número "+k+".");
					}
				}
				break;
			case 13:
				if (!hay_doc) System.out.println("Da de alta un documento primero.");
				else d.pintar_documento();
				break;

			}
		}while (accion != 0);

	}


}

