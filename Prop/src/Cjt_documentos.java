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
import java.util.TreeMap;

public class Cjt_documentos {
	private Map<String, Map<String,Documento>> por_titulo = new HashMap<String,Map<String,Documento>>();//guarda todos los documentos por titulo y los autores que lo tienen y su documento
	private Map<String, Map<String,Documento>> por_autor = new HashMap<String,Map<String,Documento>>();//guarda todos 	los documentos de un autor
	private Map<String, Map<String, Map<String,Documento>>> por_tema = new HashMap<String, Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un tema concreto
	private Map<String, Map<String, Map<String,Documento>>> por_fecha = new HashMap<String,Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un dia concreto
	private frecuencias_globales frecuencias= new frecuencias_globales();
	private int cjt_size;
	
	/*Constructora*/
	public Cjt_documentos() {
		cjt_size=0;
	}

	/*Altas*/
	
	private void llenar_estructuras(Documento d) {
		String ti=d.get_titulo().toString_consigno();
		String a=d.get_autor().toString_consigno();
		String te=d.get_tema().toString_consigno();
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
		if (!por_tema.containsKey(te)) por_tema.put(te,auttitdoctem); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
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
		String nuevo;
		if (mes.equals("12")) nuevo=dia+"/01/"+anyo.substring(1, 3);
		else nuevo=dia+'/'+mes.charAt(0)+(char)(mes.charAt(1)+1)+'/'+anyo.substring(1, 3);
		Map<String,Documento> titulodocdat = new HashMap<String,Documento>();
		titulodocdat.put(ti,d);
		Map<String,Map<String,Documento>> auttitdocdat = new HashMap<String,Map<String,Documento>>();
		auttitdocdat.put(a, titulodocdat);
		if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdocdat); //si la fecha es nueva, anyado una lista nueva de documentos para esta fecha	
		else {//si la fecha ya existia, tengo que mirar si los documentos de esta fecha contiene este autor
			if (!por_fecha.get(nuevo).containsKey(a)) por_fecha.get(nuevo).put(a,titulodocdat); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_fecha.get(nuevo).get(a).put(ti, d); //si lo contiene, anyado solo el titulo y el documento
		}
	}
	
	public void alta_doc(String raiz) throws IOException{
		Documento d= new Documento(raiz);
		llenar_estructuras(d);
		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
		++cjt_size;
	}
	public void alta_doc(Documento d) throws IOException{
		llenar_estructuras(d);
		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
		++cjt_size;
	}
	public void alta_doc(ArrayList<Documento> d) throws IOException{
		for(int i = 0;i < d.size();++i){
			alta_doc(d.get(i));
		}
	}
	@SuppressWarnings("deprecation")
	public void alta_sin_fichero(String text) throws IOException{
		Documento d= new Documento();
		d.guardar_documento(text);
		llenar_estructuras(d);
		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
	}
	public void alta_multiple(ArrayList<String> docs) throws IOException {
		for (int i=0; i<docs.size(); ++i) alta_doc(docs.get(i));
	}
	
	/*Existencias*/
	public boolean existe_combinacion(String aut, String tit) {
		if (por_autor.containsKey(aut) && por_autor.get(aut).containsKey(tit)) return true;
		return false;
	}
	
	public boolean existe_autor(String aut) {
		if (por_autor.containsKey(aut)) return true;
		return false;
	}
	
	public boolean existe_titulo(String tit) {
		if (por_titulo.containsKey(tit)) return true;
		return false;
	}
	
	public boolean existe_tema(String tem) {
		if (por_tema.containsKey(tem)) return true;
		return false;
	}
	
	public boolean existe_fecha(String fec) {
			if (por_fecha.containsKey(fec)) return true;
			return false;
		}
	
	/*Bajas*/
	//Da de baja un documento
	public void baja_individual_doc(Documento d) throws IOException{
		if (d!=null) {
			String aut=d.get_autor().toString_consigno();
			String tit=d.get_titulo().toString_consigno();
			String tem = d.get_tema().toString_consigno();
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
				String tem = por_autor.get(aut).get(clave1).get_tema().toString_consigno();
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


	/*Modificaciones*/

	public void modificar_autor(String aut, String tit, String autmod) throws IOException {
		Documento dmod=por_autor.get(aut).get(tit);
		baja_individual_doc(dmod);
		dmod.modificar_autordoc(autmod);
		llenar_estructuras(dmod);
		frecuencias.anyadir_frecuencias(dmod);
	}
	
	public void modificar_titulo(String aut, String tit, String titmod) throws IOException {
		Documento dmod=por_autor.get(aut).get(tit);
		baja_individual_doc(dmod);
		dmod.modificar_autordoc(titmod);
		llenar_estructuras(dmod);
		frecuencias.anyadir_frecuencias(dmod);
	}
	
	public void borrar_palabra(Documento d, int numfras, Palabra pborr) throws IOException{
		d.borrar_palabra(numfras, pborr);
		frecuencias.borrar_frecuencias(pborr.palabra(), d);
	}
	
	public void anyadir_palabra(Documento d, int numfras, Palabra panyad) throws IOException{
		d.anyadir_palabra(numfras, panyad);
		frecuencias.anyadir_frecuencias(panyad.palabra(), d);
	}

	/*Busquedas*/
	//Pre:existe algun documento con aut i tit
	public Documento busqueda_por_auttit(String aut, String tit) {
		return por_autor.get(aut).get(tit);
	}
	
	//Pre:existe algun documento con autor aut
	public Map<String,Documento> busqueda_por_autor(String aut) {
		return por_autor.get(aut);
	}
	
	//Pre:existe algun documento con titulo tit
	public Map<String,Documento> busqueda_por_titulo(String tit){
		return por_titulo.get(tit);
	}
	
	//Pre:existe algun documento con tema tem
	public Map<String, Map<String,Documento>> busqueda_por_tema(String tem){
		return por_tema.get(tem);
	}

	//Pre:existe algun documento con fecha fec
	public Map<String, Map<String,Documento>> busqueda_por_fecha(String fec){
		return por_fecha.get(fec);
	}

	//Devuelve TreeMap de autores ordenados por orden alfabetico
	public TreeMap autores_ordenados() throws IOException{
		TreeMap t = new TreeMap();
		t.putAll(por_autor);
		return t;
	}

	/*Frecuencias*/
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