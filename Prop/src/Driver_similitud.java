import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Driver_similitud {
	
	private static Scanner codigo;
	private static Scanner scanner;
	private static Scanner aux;
	private static Scanner aux2;
	
	public static void main (String[] args) throws IOException{
		 
		Similitud sim = new Similitud();
		int accion;
		Cjt_documentos cjt = new Cjt_documentos();
		String s;
		String s2;
		codigo = new Scanner(System.in);
		scanner = new Scanner(System.in);
		aux = new Scanner(System.in);
		aux2 = new Scanner(System.in);
		
		cjt.alta_doc("DocumentoAlta1.txt");
		cjt.alta_doc("DocumentoAlta2.txt");
		cjt.alta_doc("DocumentoAlta3.txt");
		cjt.alta_doc("DocumentoAlta5.txt");
		cjt.alta_doc("DocumentoAlta4.txt");
		cjt.alta_doc("DocumentoAlta6.txt");
		cjt.alta_doc("DocumentoAlta7.txt");
		
	
		do {
			
			System.out.println("1. Alta documento.");
			System.out.println("2. Dar de baja documento.");
			System.out.println("3. Imprimir documentos del conjunto.");
			System.out.println("4. Iniciar operación de parecido.");
			
			System.out.println("0. Salir del driver");
			accion = codigo.nextInt();
			switch (accion){
			case 1: 
				System.out.println("Escribe la ruta del documento.");
				int n1 = cjt.get_cjt_size();
				s = scanner.nextLine();
				cjt.alta_doc(s);
				if (cjt.get_cjt_size() > n1) System.out.println("Alta correcta.");
				break;
			case 2: 
				System.out.println("HOLA");
				break;
			case 3: 
				for (String clave1 : cjt.get_por_titulo().keySet()){
					for (String clave2 : cjt.get_por_titulo().get(clave1).keySet()) {
						cjt.get_por_titulo().get(clave1).get(clave2).pintar_documento();
					}
				}
				break;
			case 4:
				Documento T = new Documento();
				System.out.println("Dado un documento T y un natural k, obtener los k documentos más parecidos a T.");
				System.out.println("Introduce título del documento T");
				s = aux.nextLine();
				System.out.println("Introduce el autor del documento T");
				s2 = aux2.nextLine();
				T = cjt.busqueda_por_auttit(s2, s);
				if (T.get_titulo().toString() != null && T.get_autor().toString() != null) {
					System.out.println("Introduce un natural k");
					int k = scanner.nextInt();
					System.out.println("1. Utilizar inverse document frequency smooth");
					System.out.println("2. Utilizar probabilistic inverse document frequency");
					int metodo = scanner.nextInt();
					ArrayList<Documento> docs = new ArrayList();
					sim.similitud_n(T,k,cjt,metodo);
					docs = sim.get_resultado();
					print_resultado(T, k, docs,sim);	
				}
				break;
			}
		} while(accion != 0);
	}
	
	static void print_resultado(Documento T, int k, ArrayList<Documento> res, Similitud sim) throws IOException {
		
		if (k > 1){
			System.out.print("Los " + k +" documentos más parecidos a ");
		}
		else {
			System.out.print("El documento más parecido a ");
		}
		System.out.print("\"" + T.get_titulo().toString()+ "\"");
		System.out.print(" de ");
		System.out.print("\""+T.get_autor().toString()+"\"");
		if (k > 1) {
			System.out.println(" son:");
		}
		else System.out.println(" es:");
		
		/*for (int i = res.size()-1; i >= 0; --i){
			System.out.print(res.get(i).get_titulo());
			System.out.print(" de ");
			System.out.println(res.get(i).get_autor());
		}
		*/
		for (int i = 0; i < sim.get_resultado().size(); ++i) {
			System.out.println("\"" +sim.get_resultado().get(i).get_titulo()+"\"" + " de " + "\"" +sim.get_resultado().get(i).get_autor()+"\"");
		}
	}
}
