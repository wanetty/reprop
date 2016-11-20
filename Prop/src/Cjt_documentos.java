import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cjt_documentos {
	private Map<String, Map<String,Documento>> por_titulo = new HashMap<String,Map<String,Documento>>();//guarda todos los documentos por titulo y los autores que lo tienen y su documento
	private Map<String, Map<String,Documento>> por_autor = new HashMap<String,Map<String,Documento>>();//guarda todos 	los documentos de un autor
	private Map<String, Map<String, Map<String,Documento>>> por_tema = new HashMap<String, Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un tema concreto
	private Map<String, Map<String, Map<String,Documento>>> por_fecha = new HashMap<String,Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un dia concreto
	private frecuencias_globales frecuencias= new frecuencias_globales();
	private int cjt_size;
	
	//Constructora
	public Cjt_documentos() {
		cjt_size=0;
	}

	//Altas

	public void alta_doc(String raiz) throws IOException{
		Documento d= new Documento(raiz);
		String ti=d.get_titulo().toString();
		String a=d.get_autor().toString();
		String te=d.get_tema().toString();
		//Rellenar las estructuras por_autor,por_titulo,por_tema,por_fecha
		if (!por_titulo.containsKey(ti)){ //si titulo no exsite crear un titulo con el autor correspondiente
			Map<String,Documento> autdoc = new HashMap<String,Documento>();
			autdoc.put(a, d);
			por_titulo.put(ti, autdoc);
		}
		else por_titulo.get(ti).put(a, d);//si titulo ya existe anyadir el autor (un mismo titulo no puede tener el mismo autor por eso el documento no sustituira a uno antiguo )

		if (!por_autor.containsKey(a)) {
			Map<String,Documento> titulodocaut = new HashMap<String,Documento>();
			titulodocaut.put(ti,d);
			Map<String,Map<String,Documento>> auttitdocaut = new HashMap<String,Map<String,Documento>>();
			auttitdocaut.put(a, titulodocaut);
			por_autor.put(a, titulodocaut); //si el autor es nuevo,anyade una lista nueva de autor, titulo con su documento
		}
		else por_autor.get(a).put(ti,d); //si autor ya existe,anyade un titulo junto a su documento

		Map<String,Documento> titulodoctem = new HashMap<String,Documento>();
		titulodoctem.put(ti,d);
		Map<String,Map<String,Documento>> auttitdoctem = new HashMap<String,Map<String,Documento>>();
		auttitdoctem.put(a, titulodoctem);
		if (!por_tema.containsKey(te.toString())) por_tema.put(te,auttitdoctem); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
		else {//si el tema ya existia, tengo que mirar si los documentos de este tema contiene este autor
			if (!por_tema.get(te).containsKey(a)) por_tema.get(te).put(a,titulodoctem); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_tema.get(te).get(a).put(ti, d); //si lo contiene, anyado solo el titulo y el documento
		}

		//creo un nuevo calendario para guardar solo la fecha y no las horas
		Date f=new Date();
		int anyoaux=f.getYear();
		int mesaux=f.getMonth();
		int diaaux=f.getDate();
		String anyo = null, mes=null, dia=null;
		anyo=anyo.valueOf(anyoaux);
		mes=mes.valueOf(mesaux);
		dia=dia.valueOf(diaaux);
		String nuevo=anyo+mes+dia;
		Map<String,Documento> titulodocdat = new HashMap<String,Documento>();
		titulodocdat.put(ti,d);
		Map<String,Map<String,Documento>> auttitdocdat = new HashMap<String,Map<String,Documento>>();
		auttitdocdat.put(a, titulodocdat);
		if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdocdat); //si la fecha es nueva, anyado una lista nueva de documentos para esta fecha	
		else {//si la fecha ya existia, tengo que mirar si los documentos de esta fecha contiene este autor
			if (!por_fecha.get(nuevo).containsKey(a)) por_fecha.get(nuevo).put(a,titulodocdat); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_fecha.get(nuevo).get(a).put(ti, d); //si lo contiene, anyado solo el titulo y el documento
		}

		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
		++cjt_size;
	}
	
	@SuppressWarnings("deprecation")
	public void alta_sin_fichero(String text) throws IOException{
		Documento d= new Documento();
		d.guardar_documento(text);
		String ti=d.get_titulo().toString();
		String a=d.get_autor().toString();
		String te=d.get_tema().toString();
		if (!por_titulo.containsKey(ti)){ //si titulo no exsite crear un titulo con el autor correspondiente
			Map<String,Documento> autdoc = new HashMap<String,Documento>();
			autdoc.put(a, d);
			por_titulo.put(ti, autdoc);
		}
		else {//si titulo ya existe anyadir el autor (un mismo titulo no puede tener el mismo autor por eso el documento no sustituira a uno antiguo )
			por_titulo.get(ti).put(a, d);
		}
		++cjt_size;

		Map<String,Documento> titulodoc = new HashMap<String,Documento>();
		titulodoc.put(ti,d);
		Map<String,Map<String,Documento>> auttitdoc = new HashMap<String,Map<String,Documento>>();
		auttitdoc.put(a, titulodoc);

		if (!por_autor.containsKey(toString())) por_autor.put(a, titulodoc); //si el autor es nuevo,anyade una lista nueva de autor, titulo con su documento
		else por_autor.get(a).put(ti,d); //si autor ya existe,anyade un titulo junto a su documento

		if (!por_tema.containsKey(te.toString())) por_tema.put(te.toString(),auttitdoc); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
		else {//si el tema ya existia, tengo que mirar si los documentos de este tema contiene este autor
			if (!por_tema.get(te.toString()).containsKey(toString())) por_tema.get(te.toString()).put(toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_tema.get(te.toString()).get(toString()).put(ti, d); //si lo contiene, anyado solo el titulo y el documento
		}

		//creo un nuevo calendario para guardar solo la fecha y no las horas
		//Calendar caux = null;
		Date f= new Date();
		int anyoaux=f.getYear();
		int mesaux=f.getMonth();
		int diaaux=f.getDate();
		String anyo = null, mes=null, dia=null;
		anyo=String.valueOf(anyoaux);
		mes=String.valueOf(mesaux);
		dia=String.valueOf(diaaux); 
		String nuevo=anyo+mes+dia;
		if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdoc); //si la fecha es nueva, anyado una lista nueva de documentos para esta fecha	
		else {//si la fecha ya existia, tengo que mirar si los documentos de esta fecha contiene este autor
			if (!por_fecha.get(nuevo).containsKey(a)) por_fecha.get(nuevo).put(a,titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_fecha.get(nuevo).get(a).put(ti, d); //si lo contiene, anyado solo el titulo y el documento
		}
		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
	}

	//Cuando sea una alta convencional n=0, cuando sea multiple n>0;
	public void alta_multiple(ArrayList<String> docs) throws IOException {
		for (int i=0; i<docs.size(); ++i) alta_doc(docs.get(i));
	}
	
	
	//Bajas

	//Da de baja un documento
	public void baja_individual_doc(Documento d) throws IOException{
		if (d!=null) {
			String aut=d.get_autor().toString();
			String tit=d.get_titulo().toString();
			String tem = d.get_tema().toString();
			String fec=d.get_fecha(); 
			if (por_titulo.containsKey(tit)) {
				if (por_titulo.get(tit).containsKey(aut)) {
					--cjt_size;
					por_titulo.get(tit).remove(aut);
					if (por_titulo.get(tit).isEmpty()) por_titulo.remove(tit);
					
					if (por_autor.containsKey(aut)) {
						por_autor.get(aut).remove(tit);
						if (por_autor.get(aut).isEmpty()) por_autor.remove(aut);
					}
					
					if (por_tema.containsKey(tem)) {
						por_tema.get(tem).get(aut).remove(tit);
						if (por_tema.get(tem).get(aut).isEmpty()) por_tema.get(tem).remove(aut);
						if (por_tema.get(tem).isEmpty()) por_tema.remove(tem);
					}
	
					if (por_fecha.containsKey(fec)){
						por_fecha.get(fec).get(aut).remove(tit);
						if (por_fecha.get(fec).get(aut).isEmpty()) por_fecha.get(fec).remove(aut);
						if (por_fecha.get(fec).isEmpty()) por_fecha.remove(fec);
					}
					
					frecuencias.borrar_frecuencias(d);
					System.out.println("Baja realizada");
				}
				else System.out.println("No existe el documento con el autor introducido");
			}
			else System.out.println("No existe el documento con el titulo introducido");
		}
		else System.out.println("No existe el documento introducido");
		
	}

	//Da de baja todos los documentos del autor aut
	public void baja_multiple(String aut) throws IOException{
		if (por_autor.containsKey(aut)) {
			for(String clave1 : por_autor.get(aut).keySet()) {
				String tit=clave1;
				String tem = por_autor.get(aut).get(clave1).get_tema().toString();
				String fec=por_autor.get(aut).get(clave1).get_fecha();
				--cjt_size;
				frecuencias.borrar_frecuencias(por_autor.get(aut).get(tit));
				por_titulo.get(tit).remove(aut);
				if (por_titulo.get(tit).isEmpty()) por_titulo.remove(tit);
				por_tema.get(tem).get(aut).remove(tit);
				if (por_tema.get(tem).get(aut).isEmpty()) por_tema.get(tem).remove(aut);
				if (por_tema.get(tem).isEmpty()) por_tema.remove(tem);
				por_fecha.get(fec).get(aut).remove(tit);
				if (por_fecha.get(fec).get(aut).isEmpty()) por_fecha.get(fec).remove(aut);
				if (por_fecha.get(fec).isEmpty()) por_fecha.remove(fec);
			}
			System.out.println("Baja realizada");
		}
		else System.out.println("No existe ningun documento con el autor introducido");
		por_autor.remove(aut);
		System.out.println(por_autor);
	}


	//Modificaciones

	public void borrar_palabra(Documento d, int numfras, Palabra pborr) throws IOException{
		d.borrar_palabra(numfras, pborr);
		frecuencias.borrar_frecuencias(pborr.palabra(), d);
	}
	public void anyadir_palabra(Documento d, int numfras, Palabra panyad) throws IOException{
		d.anyadir_palabra(numfras, panyad);
		frecuencias.anyadir_frecuencias(panyad.palabra(), d);
	}

	//Busquedas

	public Documento busqueda_por_auttit(String aut, String tit) {
		aut = aut.toLowerCase();
		tit = tit.toLowerCase();
		if (por_autor.containsKey(aut)) {
			if (por_autor.get(aut).containsKey(tit)) return por_autor.get(aut).get(tit); 
			System.out.println("Combinacion de titulo o autor inexistente");
		}
		else System.out.println("Combinacion de titulo o autor inexistente");
		Documento nada= null;
		return nada;
	}

	public ArrayList<Documento> busqueda_por_titulo(String t){
		ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();
		if (por_titulo.containsKey(t)) {
			for (String clave1 : por_titulo.get(t).keySet()) conjDocumento_res.add(por_titulo.get(t).get(clave1));
			return conjDocumento_res;
		}
		System.out.println("No existe documento con el titulo introducido");
		return conjDocumento_res;
	}
	
	public ArrayList<Documento> busqueda_por_tema(String tem){
		ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();
		if (por_tema.containsKey(tem)) {
			for (String clave1 : por_tema.get(tem).keySet()) {
				for (String clave2 : por_tema.get(tem).get(clave1).keySet())
				conjDocumento_res.add(por_tema.get(tem).get(clave1).get(clave2));
			}
		}
		else System.out.println("No existe documento con el tema introducido");
		return conjDocumento_res;
	}


	//Devuelve si un prefijo s es prefijo de aut;
	private boolean es_prefijo(String aut, String s) throws IOException {
		if (s.length() <= aut.length()) {
			String comp=aut.substring(0, s.length());//comp guarda un prefijo de mi frase de la misma mida que el string que entra
			if(s.equals(comp)) {
				return true;
			}
		}
		return false;
	}

	//Devuelve una lista de los autores que contiene el prefijo pref
	public ArrayList<String> busqueda_por_prefijo(String pref) throws IOException{
		ArrayList<String> autores= new ArrayList<String>();
		for(String clave1 : por_autor.keySet())
			if (es_prefijo(clave1,pref)) {
				autores.add(clave1);
			}
		return autores;
	}


	//Devuelve la frecuencia de una palabra p en el total de conjunto de documentos
	public double frecuencia_glob_palabra(String p) {
		return frecuencias.valor_global(p);
	}

	//Devuelve el numero de documentos en la que aparece la palabra p
	public int apariencias_cjtdoc_palabra(String p) {
		return frecuencias_globales.apariencias_doc_palabra(p);
	}

	//Devuelve la lista de documentos que contiene la palabra s 
	public Map<String,ArrayList<String>> list_doc_palabra(String s) {
		return frecuencias.frecdocumentos(s);
	}

	//Devuelve la frecuencia de una palabra en el documento d
	public double frecuenciadoc_palabra(Documento d, String p) throws IOException {
		return frecuencias.valor_documento(p, d);
	}
	
	//Devuelve el numero de documentos dados de alta
	public int get_cjt_size(){
		return cjt_size;
	}

	//Devuelve un mapa de los documentos por titulo
	public Map<String, Map<String,Documento>> get_por_titulo(){
		return por_titulo;
	}

}

