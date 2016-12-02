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
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.w3c.dom.Text;


public class busquedas_driver {

	private static Scanner opcion;
	private static Scanner texto;
	private static Scanner numero;
	
	public static void main (String[] args) throws IOException{
		
		int var;
		texto = new Scanner(System.in);
		numero = new Scanner(System.in);
		opcion = new Scanner(System.in);
		Cjt_documentos cjt= new Cjt_documentos();
		Busquedas bus= new Busquedas();
		cjt.alta_doc("DocumentoAlta1.txt");
		cjt.alta_doc("DocumentoAlta2.txt");
		cjt.alta_doc("DocumentoAlta3.txt");
		cjt.alta_doc("DocumentoAlta4.txt");
		cjt.alta_doc("DocumentoAlta5.txt");
		System.out.println("Se han precargado los cinco documentos que estan en la raiz");

		do{
			System.out.println("1. por_similitud");
			System.out.println("2. por_booleano");
			System.out.println("3. por_auttit(Cjt_documentos c, String aut, String tit)");
			System.out.println("4. por_autor(Cjt_documentos c, String aut)");
			System.out.println("5. por_titulo(Cjt_documentos c, String tit)"); 
			System.out.println("6. por_tema(Cjt_documentos c, String tem)");
			System.out.println("7. por_fecha(Cjt_documentos c, String fec)");
			System.out.println("8. por_prefijo(Cjt_documentos c,String pref)");
			System.out.println("0. Salir del driver.");
			
			var = opcion.nextInt();
			switch(var){
			case 1:
				System.out.println("Dado un documento T y un natural k, obtener los k documentos mas parecidos a T.");
				System.out.println("Introduce el autor del documento T");
				String aut=texto.nextLine();
				System.out.println("Introduce el titulo del documento T");
				String tit=texto.nextLine();
				if (cjt.existe_combinacion(aut,tit)) {
					Documento T = new Documento();
					T = cjt.busqueda_por_auttit(aut,tit);
					System.out.println("Introduce un natural k");
					int k = numero.nextInt();
					if (k < 1) System.out.println("Numero invalido.");
					else {
						System.out.println("1. Utilizar inverse document frequency smooth");
						System.out.println("2. Utilizar probabilistic inverse document frequency");
						int metodo = numero.nextInt();
						if (metodo != 1 && metodo != 2) System.out.println("No existe este metodo.");
						else {
							ArrayList<Documento> docs=bus.por_similitud(cjt, aut, tit, k, metodo);
						}
					}
				}
				else System.out.println("El documento no existe.");
			case 2:
				break;
			case 3:
				System.out.println("Introduce el autor del documento a buscar");
				aut=texto.nextLine();
				System.out.println("Introduce el titulo del documento a buscar");
				tit=texto.nextLine();
				Documento auttit=bus.por_auttit(cjt, aut, tit);
				auttit.pintar_documento();
				break;
			case 4:
				System.out.println("Introduce el autor de los documento a buscar");
				aut=texto.nextLine();
				ArrayList<Documento> res=bus.por_autor(cjt, aut);
				for(int i=0; i<res.size(); ++i) res.get(i).pintar_documento();
				break;
			case 5:
				System.out.println("Introduce el titulo de los documento a buscar");
				tit=texto.nextLine();
				res=bus.por_titulo(cjt, tit);
				for(int i=0; i<res.size(); ++i) res.get(i).pintar_documento();
				break;
			case 6:	
				System.out.println("Introduce el tema de los documento a buscar");
				String tem=texto.nextLine();
				res=bus.por_tema(cjt, tem);
				for(int i=0; i<res.size(); ++i) res.get(i).pintar_documento();
				break;
			case 7:	
				System.out.println("Introduce la fecha de los documento a buscar");
				String fec=texto.nextLine();
				res=bus.por_fecha(cjt, fec);
				for(int i=0; i<res.size(); ++i) res.get(i).pintar_documento();
				break;
			case 8:	
				System.out.println("Introduce un prefijo de autor que se desea buscar");
				String pref=texto.nextLine();
				Set<String> s=bus.por_prefijo(cjt, pref);
				System.out.println(s);
				break;
			}
		}while(var!= 0);
	}
	
}


