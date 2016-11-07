import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cjt_documentos {
	private ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();//arraylist que guarda los documentos resultados de una busqueda
	private Map<String, Map<String,Documento>> por_titulo = new HashMap<String,Map<String,Documento>>();//guarda todos los documentos por titulo y los autores que lo tienen y su documento
    private Map<String,Map<String,Documento>> por_autor = new HashMap<String,Map<String,Documento>>();//guarda todos 	los documentos de un autor
    private Map<String, Map<String, Map<String,Documento>>> por_tema = new HashMap<String, Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un tema concreto
	private Map<Calendar, Map<String, Map<String,Documento>>> por_fecha = new HashMap<Calendar,Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un dia concreto
	private frecuencias_globales frecuencias= new frecuencias_globales();

	//Constructora
	public Cjt_documentos() {}
	
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
	       autdoc.put(aux[1], d);
	       por_titulo.put(aux[0], autdoc);
	   }
	   else {//si titulo ya existe anyadir el autor (un mismo titulo no puede tener el mismo autor por eso el documento no sustituira a uno antiguo )
	       por_titulo.get(aux[0]).put(aux[1], d);
	   }
	   
	   Map<String,Documento> titulodoc = new HashMap<String,Documento>();
       titulodoc.put(aux[0],d);
       Map<String,Map<String,Documento>> auttitdoc = new HashMap<String,Map<String,Documento>>();
       auttitdoc.put(aux[1], titulodoc);
       
	   if (!por_autor.containsKey(aux[1])) por_autor.put(aux[1], titulodoc); //si el autor es nuevo,anyade una lista nueva de autor, titulo con su documento
	   else por_autor.get(aux[1]).put(aux[0],d); //si autor ya existe,anyade un titulo junto a su documento
	   
	   if (!por_tema.containsKey(aux[2])) por_tema.put(aux[2],auttitdoc); //si el tema es nuevo, anyado una lista nueva de documentos para este tema
 	   else {//si el tema ya existia, tengo que mirar si los documentos de este tema contiene este autor
 		   if (!por_tema.get(aux[2]).containsKey(aux[1])) por_tema.get(aux[2]).put(aux[1],titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
 		   else por_tema.get(aux[2]).get(aux[1]).put(aux[0], d); //si lo contiene, anyado solo el titulo y el documento
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
		   if (!por_fecha.get(nuevo).containsKey(aux[1])) por_fecha.get(nuevo).put(aux[1],titulodoc); //si no contiene este autor, anyado el autor y el titulo junto al documento
 		   else por_fecha.get(nuevo).get(aux[1]).put(aux[0], d); //si lo contiene, anyado solo el titulo y el documento
	   }
	   
	   //actualizo frecuencias
	   frecuencias.actualizar_frecuencias(d);
	   
	}
	
	//creo que no hace falta porque es lo mismo para nosotros que el usuario importe un fichero o que escriba por si solo, hasta que no confirma lo que escribe no guardamos lo que ha escrito
	//la diferencia estaria en la interficie, cuando se importa un fichero se lee el contenido con un metodo y cuando lo escribe el usuario se guarda con un string directamente
	public void alta_sin_fichero(){}
	
	//Cuando sea una alta convencional n=0, cuando sea multiple n>0;
	public void alta_multiple(String text, int n, frecuencias_globales f) throws IOException {
			//for (int i=0; i<n; ++i) f.alta(text);
	}
	
	//Modificaciones en Documento
	
	//Bajas
	
	//Da de baja un documento en el resultado dado su posicion
	public void baja_individual(Integer i){
		conjDocumento_res.remove(i);
	}
	//Da de baja un documento
	public void baja_individual(Documento d) throws IOException{
		String aut=d.get_autor().frase_to_string();
		String tit=d.get_titulo().frase_to_string();
		String tem=d.get_tema().frase_to_string();
		Date fec=d.get_fecha(); 
		por_titulo.get(tit).remove(aut);
		if (por_titulo.get(tit).isEmpty()) por_titulo.remove(tit);
		por_autor.get(aut).remove(tit);
		if (por_autor.get(aut).isEmpty()) por_autor.remove(aut);
		frecuencias.borrar_frecuencia(d);
	}
	//Da de baja todos los documentos del autor aut
	public void baja_multiple(String aut){
		
	}
	
	//Busquedas
	
	public Documento busqueda_por_auttit(String aut, String tit) {
		return por_autor.get(aut).get(tit);
	}
	
	public ArrayList<Documento> busqueda_por_titulo(String t){
		conjDocumento_res.clear();
	   Iterator it=por_titulo.get(t).entrySet().iterator();
	   while (it.hasNext()) {
	       Integer k= (Integer) it.next();
	       conjDocumento_res.add(por_titulo.get(t).get(k));
	   }
	   return conjDocumento_res;
	}
	public ArrayList<Documento> busqueda_por_tema(String tem){
		conjDocumento_res.clear();
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
		conjDocumento_res.clear();
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
		conjDocumento_res.clear();
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
	//Devuelve la frecuencia de una palabra p en el total de conjunto de documentos
	public double frecuenciaglob_palabra(String p) {
		return frecuencias.valor_global(p);
	}
	
	//devuelve la frecuencia de una palabra en el documento d
	public double frecuenciadoc_palabra(Documento d, String p) throws IOException {
		return frecuencias.valor_documento(p, d);
	}
	
	//devuelve la lista de documentos resultados de una busqueda
	public ArrayList<Documento> devolver_documentos() {
		return conjDocumento_res;
	}
}

