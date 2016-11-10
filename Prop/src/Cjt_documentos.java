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
	//private ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();//arraylist que guarda los documentos resultados de una busqueda
	private Map<String, Map<String,Documento>> por_titulo = new HashMap<String,Map<String,Documento>>();//guarda todos los documentos por titulo y los autores que lo tienen y su documento
	private Map<String,Map<String,Documento>> por_autor = new HashMap<String,Map<String,Documento>>();//guarda todos 	los documentos de un autor
	private Map<String, Map<String, Map<String,Documento>>> por_tema = new HashMap<String, Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un tema concreto
	private Map<String, Map<String, Map<String,Documento>>> por_fecha = new HashMap<String,Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un dia concreto
	private frecuencias_globales frecuencias= new frecuencias_globales();
	private int cjt_size;
	//Constructora
	public Cjt_documentos() {
		cjt_size=0;
	}

	//Altas
	private void string_to_arraylist(ArrayList<Frase> c, String s) throws IOException {
		String delimitadores= "[.;?!]";//faltan los puntos suspensivos
		String[] frasesseparadas = s.split(delimitadores);
		for (int i=0; i<frasesseparadas.length; ++i) {
			Frase aux=new Frase(frasesseparadas[i]);
			c.add(aux);
		}
	}
	private void alta(String text) throws IOException {
		/*//1.Crear los parametros para crear un documento
		String del="\\n";
		String[] aux = text.split(del);//aux contiene parrafos
		//Frase ti = new Frase(a.toString());
		//Frase a = new Frase(toString());
		//Frase te= new Frase(te.toString());
		ArrayList<Frase> c= new ArrayList<Frase>();
		for (int i=3; i<aux.length; ++i) {//el for es porque en aux a partir de la posicion 3 tenemos el contenido separado por parrafos
			string_to_arraylist(c,aux[i]);
		}
		Documento d=new Documento(ti,a,te,c);//no hay que pasar data

		//2.Rellenar las estructuras por_autor,por_titulo,por_tema,por_fecha
		if (!por_titulo.containsKey(aux[0])){ //si titulo no exsite crear un titulo con el autor correspondiente
			Map<String,Documento> autdoc = new HashMap<String,Documento>();
			autdoc.put(toString(), d);
			por_titulo.put(aux[0], autdoc);
		}
		else {//si titulo ya existe anyadir el autor (un mismo titulo no puede tener el mismo autor por eso el documento no sustituira a uno antiguo )
			por_titulo.get(aux[0]).put(toString(), d);
		}
		++cjt_size;

		Map<String,Documento> titulodoc = new HashMap<String,Documento>();
		titulodoc.put(aux[0],d);
		Map<String,Map<String,Documento>> auttitdoc = new HashMap<String,Map<String,Documento>>();
		auttitdoc.put(toString(), titulodoc);

		if (!por_autor.containsKey(toString())) por_autor.put(toString(), titulodoc); //si el autor es nuevo,anyade una lista nueva de autor, titulo con su documento
		else por_autor.get(toString()).put(aux[0],d); //si autor ya existe,anyade un titulo junto a su documento

		if (!por_tema.containsKey(te.toString())) por_tema.put(te.toString(),auttitdoc); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
		else {//si el tema ya existia, tengo que mirar si los documentos de este tema contiene este autor
			if (!por_tema.get(te.toString()).containsKey(toString())) por_tema.get(te.toString()).put(toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_tema.get(te.toString()).get(toString()).put(aux[0], d); //si lo contiene, anyado solo el titulo y el documento
		}

		//creo un nuevo calendario para guardar solo la fecha y no las horas
		Calendar caux = null;
		Date f=caux.getTime();
		int anyoaux=f.getYear();
		int mesaux=f.getMonth();
		int diaaux=f.getDate();
		Calendar nuevo = null;
		nuevo.set(anyoaux, mesaux, diaaux);
		if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdoc); //si la fecha es nueva, anyado una lista nueva de documentos para esta fecha	
		else {//si la fecha ya existia, tengo que mirar si los documentos de esta fecha contiene este autor
			if (!por_fecha.get(nuevo).containsKey(toString())) por_fecha.get(nuevo).put(toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_fecha.get(nuevo).get(toString()).put(aux[0], d); //si lo contiene, anyado solo el titulo y el documento
		}

		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
*/
	}

	//creo que no hace falta porque es lo mismo para nosotros que el usuario importe un fichero o que escriba por si solo, hasta que no confirma lo que escribe no guardamos lo que ha escrito
	//la diferencia estaria en la interficie, cuando se importa un fichero se lee el contenido con un metodo y cuando lo escribe el usuario se guarda con un string directamente
	public void alta_sin_fichero(String text) throws IOException{
		//1.Crear los parametros para crear un documento
		String del="\\n";
		String[] aux = text.split(del);//aux contiene parrafos
		Frase ti = new Frase(aux[0]);
		Frase a = new Frase(aux[1]);
		Frase te= new Frase(aux[2]);
		ArrayList<Frase> c= new ArrayList<Frase>();
		for (int i=3; i<aux.length; ++i) {//el for es porque en aux a partir de la posicion 3 tenemos el contenido separado por parrafos
			string_to_arraylist(c,aux[i]);
		}
		Documento d=new Documento(ti,a,te,c);//no hay que pasar data

		//2.Rellenar las estructuras por_autor,por_titulo,por_tema,por_fecha
		if (!por_titulo.containsKey(aux[0])){ //si titulo no exsite crear un titulo con el autor correspondiente
			Map<String,Documento> autdoc = new HashMap<String,Documento>();
			autdoc.put(toString(), d);
			por_titulo.put(aux[0], autdoc);
		}
		else {//si titulo ya existe anyadir el autor (un mismo titulo no puede tener el mismo autor por eso el documento no sustituira a uno antiguo )
			por_titulo.get(aux[0]).put(toString(), d);
		}
		++cjt_size;

		Map<String,Documento> titulodoc = new HashMap<String,Documento>();
		titulodoc.put(aux[0],d);
		Map<String,Map<String,Documento>> auttitdoc = new HashMap<String,Map<String,Documento>>();
		auttitdoc.put(toString(), titulodoc);

		if (!por_autor.containsKey(toString())) por_autor.put(toString(), titulodoc); //si el autor es nuevo,anyade una lista nueva de autor, titulo con su documento
		else por_autor.get(toString()).put(aux[0],d); //si autor ya existe,anyade un titulo junto a su documento

		if (!por_tema.containsKey(te.toString())) por_tema.put(te.toString(),auttitdoc); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
		else {//si el tema ya existia, tengo que mirar si los documentos de este tema contiene este autor
			if (!por_tema.get(te.toString()).containsKey(toString())) por_tema.get(te.toString()).put(toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_tema.get(te.toString()).get(toString()).put(aux[0], d); //si lo contiene, anyado solo el titulo y el documento
		}

		//creo un nuevo calendario para guardar solo la fecha y no las horas
		Calendar caux = null;
		Date f=caux.getTime();
		int anyoaux=f.getYear();
		int mesaux=f.getMonth();
		int diaaux=f.getDate();
		String anyo = null, mes=null, dia=null;
		anyo=anyo.valueOf(anyoaux);
		mes=mes.valueOf(mesaux);
		dia=dia.valueOf(diaaux);
		String nuevo=anyo+mes+dia;
		if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdoc); //si la fecha es nueva, anyado una lista nueva de documentos para esta fecha	
		else {//si la fecha ya existia, tengo que mirar si los documentos de esta fecha contiene este autor
			if (!por_fecha.get(nuevo).containsKey(toString())) por_fecha.get(nuevo).put(toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
			else por_fecha.get(nuevo).get(toString()).put(aux[0], d); //si lo contiene, anyado solo el titulo y el documento
		}

		//actualizo frecuencias
		frecuencias.anyadir_frecuencias(d);
	}

	//Cuando sea una alta convencional n=0, cuando sea multiple n>0;
	public void alta_multiple(String text, int n) throws IOException {
		for (int i=0; i<n; ++i) alta(text);
	}

	public void alta_doc(String raiz) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(raiz));
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
						//System.out.println(funcional);
					}
					funcional = in.readLine();
				} 
				in.close();
				Documento d=new Documento(ti,a,te,c);
				//alta(todo,frecuencias);
				

				//2.Rellenar las estructuras por_autor,por_titulo,por_tema,por_fecha
				if (!por_titulo.containsKey(ti.toString())){ //si titulo no exsite crear un titulo con el autor correspondiente
					Map<String,Documento> autdoc = new HashMap<String,Documento>();
					autdoc.put(a.toString(), d);
					por_titulo.put(ti.toString(), autdoc);
				}
				else {//si titulo ya existe anyadir el autor (un mismo titulo no puede tener el mismo autor por eso el documento no sustituira a uno antiguo )
					por_titulo.get(ti.toString()).put(toString(), d);
				}

				Map<String,Documento> titulodoc = new HashMap<String,Documento>();
				titulodoc.put(ti.toString(),d);
				Map<String,Map<String,Documento>> auttitdoc = new HashMap<String,Map<String,Documento>>();
				auttitdoc.put(a.toString(), titulodoc);

				if (!por_autor.containsKey(a.toString())) por_autor.put(a.toString(), titulodoc); //si el autor es nuevo,anyade una lista nueva de autor, titulo con su documento
				else por_autor.get(a.toString()).put(ti.toString(),d); //si autor ya existe,anyade un titulo junto a su documento

				if (!por_tema.containsKey(te.toString())) por_tema.put(te.toString(),auttitdoc); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
				else {//si el tema ya existia, tengo que mirar si los documentos de este tema contiene este autor
					if (!por_tema.get(te.toString()).containsKey(a.toString())) por_tema.get(te.toString()).put(a.toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
					else por_tema.get(te.toString()).get(a.toString()).put(ti.toString(), d); //si lo contiene, anyado solo el titulo y el documento
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
				if (!por_fecha.containsKey(nuevo)) por_fecha.put(nuevo,auttitdoc); //si la fecha es nueva, anyado una lista nueva de documentos para esta fecha	
				else {//si la fecha ya existia, tengo que mirar si los documentos de esta fecha contiene este autor
					if (!por_fecha.get(nuevo).containsKey(a.toString())) por_fecha.get(nuevo).put(toString(),titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
					else por_fecha.get(nuevo).get(a.toString()).put(ti.toString(), d); //si lo contiene, anyado solo el titulo y el documento
				}

				//actualizo frecuencias
				frecuencias.anyadir_frecuencias(d);
				++cjt_size;
				
			}
			else System.out.println("caca"); 
		}
		else System.out.println("caca"); 
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

	//Bajas
	/*
	//Da de baja un documento en el resultado dado su posicion
	public void baja_individual(Integer i){
		conjDocumento_res.remove(i);
	}*/

	//Da de baja un documento
	public void baja_individual_doc(Documento d) throws IOException{
		String aut=d.get_autor().toString();
		String tit=d.get_titulo().toString();
		String tem=d.get_tema().toString();
		String fec=d.get_fecha(); 
		if (por_titulo.containsKey(tit)) {
			if (por_titulo.get(tit).containsKey(aut)) {
				--cjt_size;
				por_titulo.get(tit).remove(aut);
				if (por_titulo.get(tit).isEmpty()) por_titulo.remove(tit);
				
				por_autor.get(aut).remove(tit);
				if (por_autor.get(aut).isEmpty()) por_autor.remove(aut);
				
				por_tema.get(tem).get(aut).remove(tit);
				if (por_tema.get(tem).get(aut).isEmpty()) por_tema.get(tem).remove(aut);
				if (por_tema.get(tem).isEmpty()) por_tema.remove(tem);
				
				por_fecha.get(fec).get(aut).remove(tit);
				if (por_fecha.get(fec).get(aut).isEmpty()) por_fecha.get(fec).remove(aut);
				if (por_fecha.get(fec).isEmpty()) por_fecha.remove(fec);
				
				frecuencias.borrar_frecuencias(d);
			}
			else {
				System.out.println("no existe el documento que se quiere dar de baja");
			}
			
		}
		else {
			System.out.println("no existe el documento que se quiere dar de baja");
		}
		
	}

	//Da de baja todos los documentos del autor aut
	public void baja_multiple(String aut) throws IOException{
		Iterator it=por_autor.get(aut).entrySet().iterator();
		while (it.hasNext()) {
			String k=(String) it.next();
			baja_individual_doc(por_autor.get(aut).get(k));
		}
	}

	//Busquedas

	public Documento busqueda_por_auttit(String aut, String tit) {
		aut = aut.toLowerCase();
		tit = tit.toLowerCase();
		if (por_autor.containsKey(aut)) {
			if (por_autor.get(aut).containsKey(tit)) return por_autor.get(aut).get(tit); 
		}
		System.out.println("combinacion de titulo o autor inexistente");
		Documento nada= new Documento();
		return nada;
	}

	public ArrayList<Documento> busqueda_por_titulo(String t){
		ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();
		Iterator it=por_titulo.get(t).entrySet().iterator();
		while (it.hasNext()) {
			Integer k= (Integer) it.next();
			conjDocumento_res.add(por_titulo.get(t).get(k));
		}
		return conjDocumento_res;
	}
	public ArrayList<Documento> busqueda_por_tema(String tem){
		ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();
		Iterator it=por_tema.get(tem).entrySet().iterator();
		while (it.hasNext()) {
			String k=(String) it.next();
			Iterator it2=por_tema.get(tem).get(k).entrySet().iterator();
			while (it2.hasNext()) {
				String q=(String) it2.next();
				conjDocumento_res.add(por_tema.get(tem).get(k).get(q));
			}
		}
		return conjDocumento_res;
	}

	public ArrayList<Documento> busqueda_por_fecha(Date d){
		ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();
		int anyoaux=d.getYear();
		int mesaux=d.getMonth();
		int diaaux=d.getDate();
		Calendar nuevo = null;
		nuevo.set(anyoaux, mesaux, diaaux);
		Iterator it=por_fecha.get(nuevo).entrySet().iterator();
		while (it.hasNext()) {
			Integer k=(Integer) it.next();
			Iterator it2=por_fecha.get(nuevo).get(k).entrySet().iterator();
			while (it2.hasNext()) {
				Integer q=(Integer) it2.next();
				conjDocumento_res.add(por_fecha.get(nuevo).get(k).get(q));
			}
		}
		return conjDocumento_res;
	}

	//Devuelve si un prefijo s es prefijo de aut;
	private boolean es_prefijo(String aut, String s) throws IOException {
		String comp=aut.substring(0, s.length());//comp guarda un prefijo de mi frase de la misma mida que el string que entra
		if(s.equals(comp)) {
			return true;
		}
		return false;
	}

	public ArrayList<String> busqueda_por_prefijo(String pref) throws IOException{
		ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();
		Iterator<String> it=por_autor.keySet().iterator();
		String k;
		ArrayList<String> autores= new ArrayList<String>();
		while(it.hasNext()) {
			k=it.next();
			if (es_prefijo(k,pref)) {
				autores.add(k);
			}
		}
		return autores;
	}


	//Consultoras
	//Devuelve todas las frecuencias globales de todas las palabras del conjunto
	public frecuencias_globales frec_glob_tot() {
		return frecuencias;
	}

	//Devuelve la frecuencia de una palabra p en el total de conjunto de documentos
	public double frecuencia_glob_palabra(String p) {
		return frecuencias.valor_global(p);
	}

	//Devuelve el numeor de documentos en la que aparece la palabra p
	public int apariencias_cjtdoc_palabra(String p) {
		return frecuencias_globales.apariencias_doc_palabra(p);
	}

	//Devuelve la lista de documentos que contiene la palabra s 
	public Map<String,ArrayList<String>> list_doc_palabra(String s) {
		return frecuencias.frecdocumentos(s);
	}

	//devuelve la frecuencia de una palabra en el documento d
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

