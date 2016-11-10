import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;


public class Cjt_driver {


	public static void main (String[] args) throws IOException{
		Cjt_documentos cjt= new Cjt_documentos();
		
		cjt.alta_doc("DocumentoAlta1.txt");
		cjt.alta_doc("DocumentoAlta2.txt");
		cjt.alta_doc("DocumentoAlta5.txt");
		cjt.alta_doc("DocumentoAlta4.txt");
		cjt.alta_doc("DocumentoAlta3.txt");
		Documento d=new Documento();
		d=cjt.busqueda_por_auttit("wikipedia", "el mar");
		//d.pintar_documento();
		System.out.println(cjt.get_cjt_size());
		cjt.baja_individual_doc(d);
		System.out.println(cjt.get_cjt_size());
		Map<String,ArrayList<String>> m=cjt.list_doc_palabra("mar");
		System.out.print(m);
		d=cjt.busqueda_por_auttit("wikipedia", "el mar");
		cjt.baja_individual_doc(d);
		m=cjt.list_doc_palabra("mar");
		System.out.print(m);
		
		for (String clave : m.keySet()){
			System.out.println(m.get(clave).size());
			for (int i=0; i<m.get(clave).size(); ++i) System.out.println(clave+" "+m.get(clave).get(i));
		}
		//d=cjt.busqueda_por_auttit("wikipedia", "el mar");
		
		/*
		Map <String, Map<String,Integer>> m=new HashMap<String,Map<String,Integer>>();
		Map<String,Integer> aux=new HashMap<String,Integer>();
		aux.put("culo", 20);
		m.put("caca", aux);
		System.out.println(m.get("caca").get("culo"));
		*/
		/*
		BufferedReader in = new BufferedReader(new FileReader("DocumentoAlta1.txt"));
		//String todo = "";
		/*
		String delimitadores= "[.;?!]";
		while (funcional != null){
			String[] frasesseparadas = funcional.split(delimitadores);
			for (int i=0; i<frasesseparadas.length; ++i) {
				funcional=frasesseparadas[i];
				if (funcional.isEmpty()) 
				System.out.println("");
			}
			//todo=todo+funcional;
			funcional = in.readLine();
		} 
		in.close();*/
	}

}
