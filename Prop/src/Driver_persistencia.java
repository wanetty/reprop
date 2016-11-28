//Creador: Gerard Heredia

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver_persistencia {
	private static Scanner opcion;
	private static Scanner texto;
	private static Scanner numero;
	
	public static void main (String[] args) throws IOException{
		
		int var;
		texto = new Scanner(System.in);
		opcion = new Scanner(System.in);
		numero = new Scanner(System.in);
		Persistencia ruta = new Persistencia();
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
			System.out.println("4. baja_individual_doc(Documento d))");
			System.out.println("5. baja_multiple(String aut)"); 
			System.out.println("6. borrar_palabra(Documento d, int numfras, Palabra pborr)");
			System.out.println("7. anyadir_palabra(Documento d, int numfras, Palabra panyad)");
			System.out.println("8. busqueda_por_auttit(String aut, String tit)");
			System.out.println("9. busqueda_por_titulo(String t)");
			System.out.println("10. busqueda_por_tema (String tem)");
			System.out.println("11. busqueda_por_prefijo(String pref)");
			System.out.println("12. frecuencia_glob_palabra(String p)");
			System.out.println("13. apariencias_cjtdoc_palabra(String p)");
			System.out.println("14. list_doc_palabra(String s)");
			System.out.println("15. frecuenciadoc_palabra(Documento d, String p)");
			System.out.println("16. get_cjt_size()");
			System.out.println("17. get_por_titulo()");
			System.out.println("18. guardar");
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
				System.out.println("Introduce el autor del documento que se desea dar de baja");
				String aut=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea dar de baja");
				String tit=texto.nextLine();
				Documento d=cjt.busqueda_por_auttit(aut,tit);
				if (d!= null) cjt.baja_individual_doc(d);
				break;
			case 5:
				System.out.println("Introduce el autor de los documentos que se desea dar de baja");
				String autores=texto.nextLine();
				cjt.baja_multiple(autores);
				break;
			case 6:	
				System.out.println("Introduce el autor del documento que se desea borrar la palabra");
				String borraut=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea borrar la palabra");
				String borrtit=texto.nextLine();
				Documento borrd=cjt.busqueda_por_auttit(borraut,borrtit);
				if (borrd!=null) {
					System.out.println("Introduce el numero de la frase a la que pertenece la palabra");
					int numfras=numero.nextInt();
					System.out.println("Introduce la palabra que se quiere eliminar");
					Palabra pborr=new Palabra(texto.nextLine());
					cjt.borrar_palabra(borrd,numfras,pborr);
					System.out.println("Palabra borrada");
				}
				break;
			case 7:	
				System.out.println("Introduce el autor del documento que se desea anyadir la palabra");
				String anyadraut=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea anyadir la palabra");
				String anyadtit=texto.nextLine();
				Documento anyadd=cjt.busqueda_por_auttit(anyadraut,anyadtit);
				if (anyadd != null) {
					System.out.println("Introduce el numero de la frase a la que pertenece la palabra");
					int anyadfras=numero.nextInt();
					System.out.println("Introduce la palabra que se quiere anyadir");
					Palabra panyad=new Palabra(texto.nextLine());
					cjt.anyadir_palabra(anyadd,anyadfras,panyad);
					System.out.println("Palabra anyadida");
				}
				break;
			case 8:	
				System.out.println("Introduce el autor del documento que se desea buscar");
				String autbusc=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea buscar");
				String titbusc=texto.nextLine();
				Documento dbusc=cjt.busqueda_por_auttit(autbusc, titbusc);
				if (dbusc!=null) dbusc.pintar_documento();
				break;
			case 9:	
				System.out.println("Introduce el titulo del documento que se desea buscar");
				String buscpt=texto.nextLine();
				ArrayList<Documento> dbuscpt=cjt.busqueda_por_titulo(buscpt);
				for (int i=0; i<dbuscpt.size(); ++i) {
					dbuscpt.get(i).pintar_documento();
				}
				break;
			case 10:
				System.out.println("Introduce el tema del documento que se desea buscar");
				String busctem=texto.nextLine();
				ArrayList<Documento> dbusctem=cjt.busqueda_por_tema(busctem);
				for (int i=0; i<dbusctem.size(); ++i) {
					dbusctem.get(i).pintar_documento();
				}
				break;
			case 11:
				System.out.println("Introduce el prefijo de autor");
				String pref=texto.nextLine();
				ArrayList<String> dpref=cjt.busqueda_por_prefijo(pref);
				if (dpref.size()>0) {
					System.out.println("Los autores que contienen el prefijo introducido son:");
					for (int i=0; i<dpref.size(); ++i) {
						System.out.println(dpref.get(i));
					}
				}
				else System.out.println("No existe ningun autor con el prefijo introducido");
				break;
			case 12:
				System.out.println("Introduce la palabra que se desea consultar");
				String p=texto.nextLine();
				int resu=(int)cjt.frecuencia_glob_palabra(p);
				if (resu == 1) System.out.println("La palabra consultada aparece en el conjunto 1 vez");
				else System.out.println("La palabra consultada aparece en el conjunto "+resu+" veces");
				break;
			case 13:
				System.out.println("Introduce la palabra que se desea consultar");
				String pc=texto.nextLine();
				if (cjt.apariencias_cjtdoc_palabra(pc) == 1) System.out.println("La palabra consultada aparece en 1 documento del conjunto");
				else System.out.println("La palabra consultada aparece en "+cjt.apariencias_cjtdoc_palabra(pc)+" documentos del conjunto");
				break;
			case 14:
				System.out.println("Introduce la palabra que se desea consultar");
				String plist=texto.nextLine();
				Map<String,ArrayList<String>> mlist= cjt.list_doc_palabra(plist);
				System.out.println(mlist);
				break;
			case 15:
				System.out.println("Introduce el autor del documento que se desea buscar");
				String autfrec=texto.nextLine();
				System.out.println("Introduce el titulo del documento que se desea buscar");
				String titfrec=texto.nextLine();
				System.out.println("Introduce la palabra que se desea consultar");
				String pfrec=texto.nextLine();
				Documento dfrec=cjt.busqueda_por_auttit(autfrec, titfrec);
				if (dfrec!=null) {
					if (cjt.frecuenciadoc_palabra(dfrec,pfrec) == 1) System.out.println("La palabra introducida aparece en el documento 1 vez");
					else System.out.println("La palabra introducida aparece en el documento "+ cjt.frecuenciadoc_palabra(dfrec,pfrec)+ " veces");
				}
				break;
			case 16:
				if (cjt.get_cjt_size() == 1) System.out.println("Hay en total "+ cjt.get_cjt_size()+ " documento");
				else System.out.println("Hay en total "+ cjt.get_cjt_size()+ " documentos");
				break;
			case 17:
				 Map<String, Map<String,Documento>> mgpt=cjt.get_por_titulo();
				 System.out.println(mgpt);
				break;
			case 18:
				ruta.setArchivo("guardado1.txt");
				ruta.guardar(cjt);
				break;
			}
			
				
		}while(var!= 0);
	}
}



