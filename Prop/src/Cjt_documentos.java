package Prop;
import java.util.Date;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Cjt_documentos implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1527375506289726741L;
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

	public frecuencias_globales getFrecuencias() {
		return frecuencias;
	}

	public void setFrecuencias(frecuencias_globales frecuencias) {
		this.frecuencias = frecuencias;
	}

	
	
	/*Altas*/
	@SuppressWarnings({ "static-access", "deprecation" })
	private void llenar_estructuras(Documento d) {
		String ti=d.get_titulo().toString_consigno();
		String a=d.get_autor().toString_consigno();
		String te=d.get_tema().toString_consigno();
		if (!por_titulo.containsKey(ti)){ 
			Map<String,Documento> autdoc = new HashMap<String,Documento>();
			autdoc.put(a, d);
			por_titulo.put(ti, autdoc);
		}
		if (!por_titulo.get(ti).containsKey(a)) por_titulo.get(ti).put(a, d);
		if (!por_autor.containsKey(a)) {
			Map<String,Documento> titulodocaut = new HashMap<String,Documento>();
			titulodocaut.put(ti,d);
			Map<String,Map<String,Documento>> auttitdocaut = new HashMap<String,Map<String,Documento>>();
			auttitdocaut.put(a, titulodocaut);
			por_autor.put(a, titulodocaut); 
		}
		else por_autor.get(a).put(ti,d); 

		Map<String,Documento> titulodoctem = new HashMap<String,Documento>();
		titulodoctem.put(ti,d);
		Map<String,Map<String,Documento>> auttitdoctem = new HashMap<String,Map<String,Documento>>();
		auttitdoctem.put(a, titulodoctem);
		if (!por_tema.containsKey(te)) por_tema.put(te,auttitdoctem);
		else {
			if (!por_tema.get(te).containsKey(a)) por_tema.get(te).put(a,titulodoctem); 
			else por_tema.get(te).get(a).put(ti, d);
		}

		Date f=new Date();
		int anyoaux=f.getYear();
		int mesaux=f.getMonth();
		int diaaux=f.getDate();
		String anyo = "", mes="", dia="";
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
		if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdocdat);	
		else {
			if (!por_fecha.get(nuevo).containsKey(a)) por_fecha.get(nuevo).put(a,titulodocdat);
			else por_fecha.get(nuevo).get(a).put(ti, d); 
		}
	}

	public void alta_doc(Documento d) throws IOException{
		llenar_estructuras(d);
		frecuencias.anyadir_frecuencias(d);
		++cjt_size;
	}
		
	@SuppressWarnings("deprecation")
	public void alta_sin_fichero(String text) throws IOException{
		Documento d= new Documento();
		d.guardar_documento(text);
		llenar_estructuras(d);
		frecuencias.anyadir_frecuencias(d);
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
	public void baja_individual_doc(Documento d) throws IOException{
			String aut=d.get_autor().toString_consigno();
			String tit=d.get_titulo().toString_consigno();
			String tem = d.get_tema().toString_consigno();
			String fec=d.get_fecha(); 
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
	}

	/*Busquedas*/
	public Documento busqueda_por_auttit(String aut, String tit) {
		//Tiene que existir algun documento con aut i tit(domain)
		return por_autor.get(aut).get(tit);
	}
	
	public Map<String,Documento> busqueda_por_autor(String aut) {
		//Tiene que existir  algun documento con autor aut(domain)
		return por_autor.get(aut);
	}
	
	public Map<String,Documento> busqueda_por_titulo(String tit){
		//Tiene que existir algun documento con titulo tit(domain)
		return por_titulo.get(tit);
	}
	
	public Map<String, Map<String,Documento>> busqueda_por_tema(String tem){
		//Tiene que existir algun documento con tema tem(domain)
		return por_tema.get(tem);
	}

	public Map<String, Map<String,Documento>> busqueda_por_fecha(String fec){
		//Tiene que existir algun documento con fecha fec(domain)
		return por_fecha.get(fec);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TreeMap autores_ordenados() throws IOException{
		TreeMap t = new TreeMap();
		t.putAll(por_autor);
		return t;
	}

	/*Frecuencias*/
	public double frecuencia_glob_palabra(String p) {
		return frecuencias.valor_global(p);
	}

	public int apariencias_cjtdoc_palabra(String p) {
		return frecuencias.apariencias_doc_palabra(p);
	}

	public Set<Documento> list_doc_palabra(String s) {
		Set<Documento> res=new HashSet<Documento>();
		Map<String,Map<String,Double>> maux=frecuencias.frecdocumentos(s);
		for(String clave1 : maux.keySet()) {
			for(String clave2 : maux.get(clave1).keySet()) {
				res.add(por_autor.get(clave1).get(clave2));
			}
		}
		return res;
	}

	public double frecuenciadoc_palabra(Documento d, String p) throws IOException {
		return frecuencias.valor_documento(p, d);
	}
	
	public int get_cjt_size(){
		return cjt_size;
	}

	public Map<String, Map<String,Documento>> get_por_titulo(){
		return por_titulo;
	}
	
	
	
	
	
	
}