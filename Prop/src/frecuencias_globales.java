import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class frecuencias_globales {

	private Map <String,Double> global=new HashMap<String,Double>();
	private Map <String,Map<String,Map<String,Double>>> frecdoc=new HashMap <String,Map<String,Map<String,Double>>>();
	//String1:palabra,String2:autor,String3:tiutlo,Integer:veces que aparece la palabra en el documento
	private static Map <String,Integer> numdoc= new HashMap<String,Integer>();
	
	public frecuencias_globales() {}
	
	//actualiza la frecuencia de todas las palabras del documento
	public void anyadir_frecuencias(Documento d) throws IOException {
		int sizeDoc = d.get_contenido().size();
		Frase fraseActual = new Frase();
		for (int i = 0; i < sizeDoc; ++i) {
			fraseActual = d.get_contenido().get(i);
			int sizeFrase = fraseActual.midafrase();
			for (int j = 0; j < sizeFrase; ++j){
				Palabra palActual = fraseActual.posfrase(j);
				String palKey = palActual.palabra();
				palKey = palKey.toLowerCase();
				if (!palActual.esfuncional()) anyadir_frecuencias(palKey,d);
			}
		}
	}
	
	//actualiza la frecuencia de la palabra s en los documentos
	public void anyadir_frecuencias(String s, Documento d) throws IOException {
		if (global.containsKey(s)) {
			double pes = global.get(s);
			//global.remove(palKey);
			pes = pes + 1;
			global.put(s, pes);
		}
		else {
			global.put(s, (double) 1);
		}
		Map <String,Double> titulofreq=new HashMap<String,Double>();
		Frase aux=d.get_titulo();
		String sauxt=aux.frase_to_string();
		titulofreq.put(sauxt, (double) 1);
		aux=d.get_autor();
		String sauxa=aux.frase_to_string();
		Map <String,Map<String,Double>> auttitfreq=new HashMap<String,Map<String,Double>>();
		auttitfreq.put(sauxa, titulofreq); //auttitfreq indica el autor, titulo a la que pertenece y que ha aparecido por primera vez la palabra
		if (frecdoc.containsKey(s)) {
			if (frecdoc.get(s).containsKey(sauxa) && frecdoc.get(s).get(sauxa).containsKey(sauxt)) { //comprueba si la palabra pertenece a un documento existente
				double pes = frecdoc.get(s).get(sauxa).get(sauxt);
				++pes;
				frecdoc.get(s).get(sauxa).put(s,pes);
			}
			else {
				frecdoc.put(s, auttitfreq);
				if (numdoc.containsKey(s)) {
					int n=numdoc.get(s);
					++n;
					numdoc.put(s,n);
				}
				else numdoc.put(s,1);
			}
		}
		else frecdoc.put(s, auttitfreq);
	}
	
	public void borrar_frecuencias(Documento d) throws IOException {//resta la frecuencia de las palabras del documento d
		int sizeDoc = d.get_contenido().size();
		Frase fraseActual = new Frase();
		for (int i = 0; i < sizeDoc; ++i) {
			fraseActual = d.get_contenido().get(i);
			int sizeFrase = fraseActual.midafrase();
			for (int j = 0; j < sizeFrase; ++j){
				Palabra palActual = fraseActual.posfrase(j);
				String palKey = palActual.palabra();
				palKey = palKey.toLowerCase();
				if (!palActual.esfuncional()) borrar_frecuencias(palKey,d);
			}
		}
	}
	
	public void borrar_frecuencias(String s, Documento d) throws IOException {//resta la frecuencia de la palabra p en el documento d
		String aut=d.get_autor().frase_to_string();
		String tit=d.get_titulo().frase_to_string();
		double pes=global.get(s);
		if (pes > 1) {
			--pes;
			global.put(s,pes);
			if (frecdoc.get(s).containsKey(aut) && frecdoc.get(s).get(aut).containsKey(tit)) {
				pes=frecdoc.get(s).get(aut).get(tit);
				--pes;
				frecdoc.get(s).get(aut).put(tit,pes);
			}
			//si la palabra no existe de momento no devuelve nada
		}
		else {
			global.remove(s);
			frecdoc.remove(s);
			if (numdoc.containsKey(s)) {
				int n=numdoc.get(s);
				--n;
				numdoc.put(s,n);
			}
			else numdoc.remove(s);
		}
	}
	
	public double valor_global(String p) {//devuelve la frecuencia global de la palabra p
		return global.get(p);
	}
	
	//devuelve el mapa de frecuencia globales
	public Map<String,Double> frecglobal() {
		return global;
	}
	
	//devuelve una lista de los documentos identificados por el autor y el titulo a la que pertenece la palabra s
	public Map<String,ArrayList<String>> frecdocumentos(String s) {
		Map<String,ArrayList<String>> res=new HashMap<String,ArrayList<String>>();
		Iterator it= frecdoc.get(s).entrySet().iterator();
		while (it.hasNext()) {
			String k=(String) it.next();
			Iterator it2=frecdoc.get(s).get(k).entrySet().iterator();
			ArrayList<String> titaux=new ArrayList<String>(); 
			while(it2.hasNext()) {
				String q=(String) it2.next();
				titaux.add(q);
			}
			res.put(k,titaux);
		}
		return res;
	}
	
	public double valor_documento(String p, Documento d) throws IOException {//devuelve la frecuencia de la palabra en el documento
		String a, t;
		a=d.get_autor().frase_to_string();
		t=d.get_titulo().frase_to_string();
		return frecdoc.get(p).get(a).get(t);
	}
	
	public double valor_documento(String p, String a, String t) {//devuelve la frecuencia de la palabra en el documento que tiene como autor a y titulo t
		return frecdoc.get(p).get(a).get(t);
	}
	
	public static int apariencias_doc_palabra(String p) {
		if (numdoc.containsKey(p)) return numdoc.get(p);
		else return 0;
	}
	
}