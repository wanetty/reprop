import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver_documento {
	
	private static Scanner codigo;
	private static Scanner scanner;
	private static Scanner aux;
	
	public static void main(String[] args) throws IOException {
		
		Documento d = new Documento();
		Cjt_documentos cjt = new Cjt_documentos();
		Frase f = new Frase();
		
		Palabra pal = new Palabra();
		int accion;
		int k;
		String s;
		codigo = new Scanner(System.in);
		scanner = new Scanner(System.in);
		aux = new Scanner(System.in);
		
		do {
			System.out.println("1. Alta documento.");
			System.out.println("2. Consulta t�tulo.");
			System.out.println("3. Consulta autor.");
			System.out.println("4. Consulta tema.");
			System.out.println("5. Consulta fecha.");
			System.out.println("6. Consulta el n�mero de frases del contenido.");
			System.out.println("7. Consulta contenido.");
			System.out.println("8. Consulta la frase en la posici�n i.");
			System.out.println("9. Consulta el vector de frecuencias del documento.");
			System.out.println("10. Consulta el n�mero total de palabras no funcionales del contenido del documento.");
			System.out.println("11. Borrar la primera aparici�n de la palabra p en la frase i del contenido.");
			System.out.println("12. A�adir una palabra al final de la frase i del contenido.");
			System.out.println("13. Imprime el documento por pantalla.");
			System.out.println("0. Salir del driver.");
			
			accion = codigo.nextInt();
			switch (accion){
			
			case 1:
				System.out.println("Escribe la ruta del documento.");
				s = scanner.nextLine();
				if (cjt.get_cjt_size() == 1) cjt.baja_individual_doc(d);
				cjt.alta_doc(s);
				if (cjt.get_cjt_size() == 1) System.out.println("Alta correcta.");
				for(String clave1 : cjt.get_por_titulo().keySet()) {
					for (String clave2 : cjt.get_por_titulo().get(clave1).keySet()) {
						d = cjt.get_por_titulo().get(clave1).get(clave2);
					}
				}
				break;
				
			case 2:
				f = d.get_titulo();
				System.out.println("El t�tulo es: ");
				f.escribirfrase();
				System.out.println("");
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
				System.out.println("La fecha es: ");
				System.out.println(d.get_fecha());
				break;
			case 6:
				System.out.println("El n�mero de frases es: ");
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
				System.out.println("La frase en la posici�n i es: ");
				f.escribirfrase();
				break;
			case 9:
				Map<String, Double> a = new HashMap<String,Double>();
				a = d.get_pesos();
				System.out.println("El vector de frecuencias es: "+ a);
				break;
			case 10:
				System.out.println("El n�mero total de palabras no funcionales es: ");
				System.out.println(d.get_total_words());
				break;
			
			case 11:
				System.out.println("Escriba la palabra.");
				s = aux.nextLine();
				pal = new Palabra(s);
				System.out.println("Escriba el n�mero de frase.");
				k = scanner.nextInt();
				d.borrar_palabra(k, pal);
				System.out.println("Primera aparici�n de la palabra "+ pal.palabra() + " de la frase n�mero " +k+" borrada.");
				break;
			case 12:
				System.out.println("Escriba la palabra.");
				s = aux.nextLine();
				pal = new Palabra(s);
				System.out.println("Escriba el n�mero de frase.");
				k = scanner.nextInt();
				d.anyadir_palabra(k, pal);
				System.out.println("Palabra "+ pal.palabra()+" a�adida al dinal de la frase n�mero "+k+".");
				break;
			case 13:
				d.pintar_documento();
				break;
				
			}
		}while (accion != 0);
		
	}

}

