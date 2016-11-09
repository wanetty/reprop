import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Driver_similitud {
	
	private static Scanner codigo;
	private static Scanner scanner;
	
	public static void main (String[] args) throws IOException{
		 
		Similitud sim = new Similitud();
		int accion;
		Cjt_documentos cjt = new Cjt_documentos();
		codigo = new Scanner(System.in);
		scanner = new Scanner(System.in);
	
		do {
			
			System.out.println("1. Subir documento.");
			System.out.println("2. Subir múltiples documentos.");
			System.out.println("3. Dar de baja documento.");
			System.out.println("4. Dar de baja múltiples documentos.");
			System.out.println("5. Iniciar operación de parecido.");
			System.out.println("0. Salir del driver");
			accion = codigo.nextInt();
			switch (accion){
			
			case 1: 
				System.out.println("HOLA");
				cjt.alta_doc("C:\\Users\\win8\\Documents\\GitHub\\reprop\\Prop\\DocumentoAlta1.txt");
				
				
				break;
			case 2: 
				System.out.println("HOLA");
				break;
			case 3: 
				System.out.println("HOLA");
				break;
			case 4: 
				System.out.println("HOLA");
				break;
			case 5:
				Documento T = new Documento();
				System.out.println("Dado un documento T y un natural k, obtener los k documentos más parecidos a T.");
				System.out.println("Introduce un documento T");
				System.out.println("Introduce un natural k");
				int k = scanner.nextInt();
				System.out.println("1. Utilizar inverse document frequency smooth");
				System.out.println("2. Utilizar probabilistic inverse document frequency");
				int metodo = scanner.nextInt();
				ArrayList<Documento> docs = new ArrayList();
				sim.similitud_n(T,k,cjt,metodo);
				print_resultado(T, k, docs);	
				break;
			}
		} while(accion != 0);
	}
	
	static void print_resultado(Documento T, int k, ArrayList<Documento> res) throws IOException {
		if (k > 1){
			System.out.print("Los ");
			System.out.print(k);
			System.out.print(" documentos más parecidos a ");
		}
		else {
			System.out.print("El documento más parecido a ");
		}
		System.out.print(k);
		System.out.print(" documentos más parecidos a ");
		System.out.print(T.get_titulo().frase_to_string());
		System.out.print(" de ");
		System.out.print(T.get_autor().frase_to_string());
		System.out.println(" son:");
		for (int i = res.size()-1; i > 0; ++i){
			System.out.print(res.get(i).get_titulo());
			System.out.print(" de ");
			System.out.println(res.get(i).get_autor());
		}
	}
}
