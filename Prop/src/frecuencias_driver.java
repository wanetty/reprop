import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class frecuencias_driver {
	
	private static Scanner opcion;
	private static Scanner texto;
	
	public static void main (String[] args) throws IOException{
		
		int var;
		texto = new Scanner(System.in);
		opcion = new Scanner(System.in);
		frecuencias_globales f=new frecuencias_globales();
		Documento d=null;

		do{
			System.out.println("1. anyadir_frecuencias(Documento d)");
			System.out.println("2. anyadir_frecuencias(String s, Documento d)");
			System.out.println("3. borrar_frecuencias(Documento d)");
			System.out.println("4. borrar_frecuencias(String s, Documento d)");
			System.out.println("5. valor_global(String p)");
			System.out.println("6. frecdocumentos(String p)");
			System.out.println("7. valor_documento(String p, Documento d)");
			System.out.println("8. valor_documento(String p, String a, String t)");
			System.out.println("9. apariencias_doc_palabra(String p)");
			System.out.println("10. lee un documento");
			System.out.println("0. Salir del driver.");
			var = opcion.nextInt();
			switch(var){
			case 1:
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					f.anyadir_frecuencias(d);
					System.out.println("Todas las palabras del documento tinenen sus frecuencias actualizadas");
				}
				break;
			case 2:
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra");
					System.out.println("La palabra introducida tendra sus frecuencias actualizadas");
					f.anyadir_frecuencias(texto.nextLine(),d);
				}
				break;
			case 3:
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Todas las palabras del documento tendran sus frecuencias actualizadas");
					f.borrar_frecuencias(d);
					d=null;
				}
				break;
			case 4:
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra");
					System.out.println("La palabra introducida tendra sus frecuencias actualizadas");
					f.borrar_frecuencias(texto.nextLine(),d);
				}
				break;
			case 5:
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra");
					System.out.println("La palabra aparece en total "+ f.valor_global(texto.nextLine()) + " veces");
				}
				break;
			case 6:	
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra");  
					System.out.println("fdoc contiene un mapa que guarda los documentos a los que pertenece la palabra introducida,"+
					"contiene como llave el autor y como valor una lista de titulos");
					Map<String,ArrayList<String>> fdoc=f.frecdocumentos(texto.nextLine());
					System.out.println(fdoc);
				}
				break;
			case 7:	
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra");
					System.out.println("La palabra introducida aparece en el documento " + f.valor_documento(texto.nextLine(),d)+ " veces");
				}
				break;
			case 8:	
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra");
					System.out.println("Introduce un autor");
					System.out.println("Introduce una titulo");
					System.out.println("La palabra introducida aparece en el documento "+ f.valor_documento(texto.nextLine(),texto.nextLine(),texto.nextLine())+" veces");
				}
				break;
			case 9:	
				if (d==null) System.out.println("No hay ningun documento leido");
				else {
					System.out.println("Introduce una palabra"); 
					System.out.println("La palabra introducida aparece en el documento " + f.apariencias_doc_palabra(texto.nextLine())+ " veces");
				}
				break;
			case 10:
				System.out.println("Introduce el directorio donde se encuentra el documento");
				String directorio=texto.nextLine();
				File carpeta=new File(directorio);
				if (carpeta.isDirectory()) {
					System.out.println("Introduce el nombre de un documento");
					String tituloint=texto.nextLine();
					File[] ficheros=carpeta.listFiles();
					int j=0;
					boolean trobat=false;
					while (j<ficheros.length && !trobat) {
						if (ficheros[j].getName().equals(tituloint)) trobat=true;
						++j;
					}
					if (trobat) {
						BufferedReader in = new BufferedReader(new FileReader(tituloint));
						String funcional = in.readLine();
						if (!funcional.isEmpty()) {
							Frase a=new Frase(funcional);
							funcional=in.readLine();
							if (!funcional.isEmpty()) {
								Frase ti=new Frase(funcional);
								funcional=in.readLine();
								Frase te=new Frase(funcional);
								funcional=in.readLine();
								ArrayList<Frase> c= new ArrayList<Frase>();
								String delimitadores= "[.;?!:]";
								while (funcional != null){
									String[] frasesseparadas = funcional.split(delimitadores);
									for (int i=0; i<frasesseparadas.length; ++i) {
										funcional=frasesseparadas[i];
										c.add(new Frase(funcional));
									}
									funcional = in.readLine();
								} 
								d=new Documento(ti,a,te,c);
								System.out.println("Documento leido correctamente");
							}
							else System.out.println("Formato de documento incorrecto: autor no puede ser vacio"); 
						}
						else System.out.println("Formato de documento incorrecto: titulo no puede ser vacio");
						in.close();
					}
					else System.out.println("El documento no existe en el directorio introducido");
				}
				else System.out.println("El directorio no existe o esta mal introducido");
				break;
			}
		}while(var!= 0);
	}
}
