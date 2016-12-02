import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.w3c.dom.Text;


public class Cjt_driver {

	private static Scanner opcion;
	private static Scanner texto;
	private static Scanner numero;
	
	public static void main (String[] args) throws IOException{
		
		int var;
		texto = new Scanner(System.in);
		opcion = new Scanner(System.in);
		numero = new Scanner(System.in);
		Cjt_documentos cjt= new Cjt_documentos();
		cjt.alta_doc("DocumentoAlta1.txt");
		cjt.alta_doc("DocumentoAlta2.txt");
		cjt.alta_doc("DocumentoAlta3.txt");
		cjt.alta_doc("DocumentoAlta4.txt");
		cjt.alta_doc("DocumentoAlta5.txt");
		System.out.println("Se han precargado los cinco documentos que estan en la raiz");

		do{
			System.out.println("1. Dar de alta un documento dado su directiva(String raiz)");
			System.out.println("2. alta_sin_fichero(String text)");
			System.out.println("3. alta_multiple(String text, int n)");
			System.out.println("4. existe_combinacion(String aut,String tit)");
			System.out.println("5. existe_autor(String aut)");
			System.out.println("6. existe_titulo(String tit)");
			System.out.println("7. existe_tema(String tem)");
			System.out.println("8. existe_fecha(String fec)");			
			System.out.println("9. baja_individual_doc(Documento d))");
			System.out.println("10. baja_multiple(String aut)"); 
			System.out.println("11. modificar_autor(String aut, String tit, String autmod)"); 
			System.out.println("12. modificar_titulo(String aut, String tit, String titmod)");
			System.out.println("13. borrar_palabra(Documento d, int numfras, Palabra pborr)");
			System.out.println("14. anyadir_palabra(Documento d, int numfras, Palabra panyad)");
			System.out.println("15. busqueda_por_auttit(String aut, String tit)");
			System.out.println("16. busqueda_por_autor(String aut)");
			System.out.println("17. busqueda_por_titulo(String t)");
			System.out.println("18. busqueda_por_tema (String tem)");
			System.out.println("19. busqueda_por_fecha(String fec)");
			System.out.println("20. busqueda_por_prefijo(String pref)");
			System.out.println("21. frecuencia_glob_palabra(String p)");
			System.out.println("22. apariencias_cjtdoc_palabra(String p)");
			System.out.println("23. list_doc_palabra(String s)");
			System.out.println("24. frecuenciadoc_palabra(Documento d, String p)");
			System.out.println("25. get_cjt_size()");
			System.out.println("26. get_por_titulo()");
			System.out.println("0. Salir del driver.");
			var = opcion.nextInt();
			switch(var){
			case 1:
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
						cjt.alta_doc(tituloint);
					}
					else System.out.println("El documento no existe en el directorio introducido");
				}
				else System.out.println("El directorio no existe o esta mal introducido");
				break;
			case 2:
				String del="\\n";
				System.out.println("Introduce el titulo");
				String titulo=texto.nextLine();
				String text="";
				if (!titulo.isEmpty()) {
					text+=titulo;
					System.out.println("Introduce el autor");
					String autor=texto.nextLine();
					if (!autor.isEmpty()) {
						text+='\n';
						text+=autor;
						 System.out.println("Introduce el tema");
						 String tema=texto.nextLine();
						 text+='\n';
						 text+=tema;	
						 System.out.println("escribe todo el texto a continuacion, no pulses intro hasta que acabes");
						 String contenido = texto.nextLine();
						 text+='\n';
						 text+=contenido;
						 cjt.alta_sin_fichero(text);
					}
					else  System.out.println("Formato de documento incorrecto: autor no puede ser vacio"); 
				}
				else System.out.println("Formato de documento incorrecto: titulo no puede ser vacio");
				break;
			case 3:
				System.out.println("Introduce el numero de documentos a leer");
				int num=numero.nextInt();
				System.out.println("Introduce el directorio donde se encuentran los documentos");
				String dir=texto.nextLine();
				System.out.println("Introduce los nombres de los documentos separados por un intro");
				File carp=new File(dir);
				File[] fichers=carp.listFiles();
				if (carp.isDirectory()) {
					ArrayList<String> arr= new ArrayList<String>();
					for (int i=0; i<num; ++i) {
						String nomdoc=texto.nextLine();
						int j=0;
						boolean trobat=false;
						while (j<fichers.length && !trobat) {
							if (fichers[j].getName().equals(nomdoc)) trobat=true;
							++j;
						}
						if (trobat) {
							arr.add(nomdoc);
						}
						else System.out.println("El documento no existe en el directorio introducido");
					}
					cjt.alta_multiple(arr);
				}
				else System.out.println("El directorio no existe o esta mal introducido");
				break;
			case 4:
				System.out.println("Introduce el autor que se desea consultar");
				String combaut=texto.nextLine();
				System.out.println("Introduce el titulo que se desea consultar");
				String comtit=texto.nextLine();
				if (cjt.existe_combinacion(combaut,comtit)) System.out.println("La combinacion de autor y titulo existe");
				else System.out.println("La combinacion de autor y titulo no existe");
				break;
			case 5:
				System.out.println("Introduce el autor que se desea consultar");
				String consaut=texto.nextLine();
				if (cjt.existe_autor(consaut)) System.out.println("El autor a consultar existe");
				else System.out.println("El autor a consultar no existe");
				break;
			case 6:
				System.out.println("Introduce el titulo que se desea consultar");
				String constit=texto.nextLine();
				if (cjt.existe_titulo(constit)) System.out.println("El titulo a consultar existe");
				else System.out.println("El titulo a consultar no existe");
				break;
			case 7:
				System.out.println("Introduce el tema que se desea consultar");
				String constem=texto.nextLine();
				if (cjt.existe_tema(constem)) System.out.println("El tema a consultar existe");
				else System.out.println("El tema a consultar no existe");
				break;
			case 8:
				System.out.println("Introduce la fecha que se desea consultar");
				String consfec=texto.nextLine();
				if (cjt.existe_fecha(consfec)) System.out.println("La fecha a consultar existe");
				else System.out.println("La fecha a consultar no existe");
				break;
			case 9:
				System.out.println("Introduce el autor del documento que se desea dar de baja");
				String aut=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea dar de baja");
				String tit=texto.nextLine();
				if (cjt.existe_combinacion(aut, tit)) {
					Documento d=cjt.busqueda_por_auttit(aut,tit);
					cjt.baja_individual_doc(d);
					d.pintar_documento();
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 10:
				System.out.println("Introduce el autor de los documentos que se desea dar de baja");
				String autores=texto.nextLine();
				cjt.baja_multiple(autores);
				break;
			case 11:
				System.out.println("Introduce el autor del documento que se desea modificar");
				String autamod=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea modificar");
				String titamod=texto.nextLine();
				System.out.println("Introduce el nuevo autor que se desea modificar");
				String nuevoaut=texto.nextLine();
				if (cjt.existe_combinacion(autamod, titamod)) {
					cjt.modificar_autor(autamod, titamod, nuevoaut);
					System.out.println("Modificacion hecha");
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 12:
				System.out.println("Introduce el autor del documento que se desea modificar");
				String autpmod=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea modificar");
				String titpmod=texto.nextLine();
				System.out.println("Introduce el nuevo titulo que se desea modificar");
				String nuevotit=texto.nextLine();
				if (cjt.existe_combinacion(autpmod, titpmod)) {
					cjt.modificar_autor(autpmod, titpmod, nuevotit);
					System.out.println("Modificacion hecha");
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 13:	
				System.out.println("Introduce el autor del documento que se desea borrar la palabra");
				String borraut=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea borrar la palabra");
				String borrtit=texto.nextLine();
				if (cjt.existe_combinacion(borraut, borrtit)) {
					Documento borrd=cjt.busqueda_por_auttit(borraut,borrtit);
					System.out.println("Introduce el numero de la frase a la que pertenece la palabra");
					int numfras=numero.nextInt();
					System.out.println("Introduce la palabra que se quiere eliminar");
					Palabra pborr=new Palabra(texto.nextLine());
					cjt.borrar_palabra(borrd,numfras,pborr);
					System.out.println("Palabra borrada");
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 14:	
				System.out.println("Introduce el autor del documento que se desea anyadir la palabra");
				String anyadaut=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea anyadir la palabra");
				String anyadtit=texto.nextLine();
				if (cjt.existe_combinacion(anyadaut, anyadtit)) {
					Documento anyadd=cjt.busqueda_por_auttit(anyadaut,anyadtit);
					System.out.println("Introduce el numero de la frase a la que pertenece la palabra");
					int anyadfras=numero.nextInt();
					System.out.println("Introduce la palabra que se quiere anyadir");
					Palabra panyad=new Palabra(texto.nextLine());
					cjt.anyadir_palabra(anyadd,anyadfras,panyad);
					System.out.println("Palabra anyadida");
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 15:
				System.out.println("Introduce el autor del documento que se desea buscar");
				String autbusc=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea buscar");
				String titbusc=texto.nextLine();
				if (cjt.existe_combinacion(autbusc, titbusc)) {
					Documento dbusc=cjt.busqueda_por_auttit(autbusc, titbusc);
					dbusc.pintar_documento();
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 16:	
				System.out.println("Introduce el autor de los documentos que se desea buscar");
				String buscpa=texto.nextLine();
				if (cjt.existe_autor(buscpa)) {
					Map<String,Documento> aux=cjt.busqueda_por_autor(buscpa);
					for (String clave :  aux.keySet()) {
						aux.get(clave).pintar_documento();
					}
				}
				else System.out.println("No existen documentos con el autor introducido");
				break;
			case 17:	
				System.out.println("Introduce el titulo de los documentos que se desea buscar");
				String buscpt=texto.nextLine();
				if (cjt.existe_titulo(buscpt)) {
					Map<String,Documento> aux=cjt.busqueda_por_titulo(buscpt);
					for (String clave :  aux.keySet()) {
						aux.get(clave).pintar_documento();
					}
				}
				else System.out.println("No existen documentos con el titulo introducido");
				break;
			case 18:
				System.out.println("Introduce el tema del documento que se desea buscar");
				String busctem=texto.nextLine();
				if (cjt.existe_tema(busctem)) {
					Map<String, Map<String, Documento>> aux=cjt.busqueda_por_tema(busctem);
					for (String clave1 :  aux.keySet()) {
						for (String clave2 : aux.get(clave1).keySet())
							aux.get(clave1).get(clave2).pintar_documento();
					}
				}
				else System.out.println("No existen documentos con el tema introducido");
				break;
			case 19:
				System.out.println("Introduce la fecha del documento que se desea buscar (Formato:dd/mm/yy)");
				String buscfec=texto.nextLine();
				if (cjt.existe_fecha(buscfec)) {
					Map<String, Map<String, Documento>> aux=cjt.busqueda_por_fecha(buscfec);
					for (String clave1 :  aux.keySet()) {
						for (String clave2 : aux.get(clave1).keySet())
							aux.get(clave1).get(clave2).pintar_documento();
					}
				}
				else System.out.println("No existen documentos con el tema introducido");
				break;
			case 20:
				System.out.println("Lista de autores ordenados alfabeticamente con sus correspondientes titulos:");
				TreeMap<String,Map<String,Documento>> t=cjt.autores_ordenados();
				for (String clave1 : t.keySet()) {
					for (String clave2 : t.get(clave1).keySet()) {
						System.out.println(clave1+" : "+clave2);
					}
				}
				break;
			case 21:
				System.out.println("Introduce la palabra que se desea consultar");
				String p=texto.nextLine();
				int resu=(int)cjt.frecuencia_glob_palabra(p);
				if (resu == 1) System.out.println("La palabra consultada aparece en el conjunto 1 vez");
				else System.out.println("La palabra consultada aparece en el conjunto "+resu+" veces");
				break;
			case 22:
				System.out.println("Introduce la palabra que se desea consultar");
				String pc=texto.nextLine();
				if (cjt.apariencias_cjtdoc_palabra(pc) == 1) System.out.println("La palabra consultada aparece en 1 documento del conjunto");
				else System.out.println("La palabra consultada aparece en "+cjt.apariencias_cjtdoc_palabra(pc)+" documentos del conjunto");
				break;
			case 23:
				System.out.println("Introduce la palabra que se desea consultar");
				String plist=texto.nextLine();
				Map<String,ArrayList<String>> mlist= cjt.list_doc_palabra(plist);
				System.out.println(mlist);
				break;
			case 24:
				System.out.println("Introduce el autor del documento que se desea buscar");
				String autfrec=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea buscar");
				String titfrec=texto.nextLine();
				System.out.println("Introduce la palabra que se desea consultar");
				String pfrec=texto.nextLine();
				if (cjt.existe_combinacion(autfrec, titfrec)) {
					Documento dfrec=cjt.busqueda_por_auttit(autfrec, titfrec);
					if (cjt.frecuenciadoc_palabra(dfrec,pfrec) == 1) System.out.println("La palabra introducida aparece en el documento 1 vez");
					else System.out.println("La palabra introducida aparece en el documento "+ cjt.frecuenciadoc_palabra(dfrec,pfrec)+ " veces");
				}
				else System.out.println("Combinacion de titulo o autor inexistente");
				break;
			case 25:
				if (cjt.get_cjt_size() == 1) System.out.println("Hay en total "+ cjt.get_cjt_size()+ " documento");
				else System.out.println("Hay en total "+ cjt.get_cjt_size()+ " documentos");
				break;
			case 26:
				 Map<String, Map<String,Documento>> mgpt=cjt.get_por_titulo();
				 System.out.println(mgpt);
				break;
			}
		}while(var!= 0);
	}
}


