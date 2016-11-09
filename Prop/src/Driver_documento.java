import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver_documento {
	
	private static Scanner codigo;
	private static Scanner scanner;
	
	public static void main(String[] args) throws IOException {
		
		Documento d = new Documento();
		Frase f = new Frase();
		
		Palabra pal = new Palabra();
		int accion;
		int k;
		String s;
		codigo = new Scanner(System.in);
		scanner = new Scanner(System.in);
		
		do {
			System.out.println("1. Crear documento.");
			System.out.println("2. Consulta título.");
			System.out.println("3. Consulta autor.");
			System.out.println("4. Consulta tema.");
			System.out.println("5. Consulta fecha.");
			System.out.println("6. Consulta el número de frases del contenido.");
			System.out.println("7. Consulta contenido.");
			System.out.println("8. Consulta la frase en la posición i.");
			System.out.println("9. Consulta el vector de frecuencias del documento.");
			System.out.println("10. Consulta el número total de palabras no funcionales del contenido del documento.");
			System.out.println("11. Modificar titulo.");
			System.out.println("12. Modificar autor.");
			System.out.println("13. Modificar tema.");
			System.out.println("14. Borrar la primera aparición de la palabra p en la frase i del contenido.");
			System.out.println("15. Añadir una palabra al final de la frase i del contenido.");
			System.out.println("16. Borrar la frase en la posición i.");
			System.out.println("17. Modificar la frase en la posición i por la frase f");
			System.out.println("18. Imprime el documento por pantalla.");
			System.out.println("0. Salir del driver.");
			
			accion = codigo.nextInt();
			switch (accion){
			
			case 1:
				//alta doc etc
				break;
				
			case 2:
				f = d.get_titulo();
				System.out.println("El título es: ");
				f.escribirfrase();
				break;
			case 3:
				f = d.get_autor();
				System.out.println("El autor es: ");
				f.escribirfrase();
				break;
			case 4:
				f = d.get_tema();
				System.out.println("El tema es: ");
				f.escribirfrase();
				break;
			case 5:
				Date da = d.get_fecha();
				System.out.println("La fecha es: ");
				System.out.println(da);
				break;
			case 6:
				System.out.println("El número de frases es: ");
				System.out.println(d.get_num_frases());
				break;
			case 7:
				ArrayList<Frase> c = new ArrayList<Frase>();
				c = d.get_contenido();
				for (int i = 0; i < c.size(); ++i) {
					c.get(i).escribirfrase();
				}
				break;
			case 8:
				System.out.println("Inserte un natural i.");
				k = scanner.nextInt();
				f = d.get_frase(k);
				System.out.println("La frase en la posición i es: ");
				f.escribirfrase();
				break;
			case 9:
				Map<String, Double> a = new HashMap<String,Double>();
				a = d.get_pesos();
				System.out.println("El vector de pesos es: "+ a);
				break;
			case 10:
				System.out.println("El número total de palabras no funcionales es: ");
				System.out.println(d.get_total_words());
				break;
			case 11:
				s = scanner.nextLine();
				Frase ti = new Frase(s);
				d.set_titulo(ti);
				break;
			case 12:
				s = scanner.nextLine();
				Frase au = new Frase(s);
				d.set_autor(au);
				break;
			case 13:
				s = scanner.nextLine();
				Frase te = new Frase(s);
				d.set_tema(te);
				break;
			case 14:
				System.out.println("Escriba la palabra.");
				s = scanner.nextLine();
				pal = new Palabra(s);
				System.out.println("Escriba el número de frase.");
				k = scanner.nextInt();
				d.borrar_palabra(k, pal);
				break;
			case 15:
				System.out.println("Escriba la palabra.");
				s = scanner.nextLine();
				pal = new Palabra(s);
				System.out.println("Escriba el número de frase.");
				k = scanner.nextInt();
				d.anyadir_palabra(k, pal);
				break;
			case 16:
				break;
			case 17:
				break;
			case 18:
				break;
				
			}
		}while (accion != 0);
		
	}

}
