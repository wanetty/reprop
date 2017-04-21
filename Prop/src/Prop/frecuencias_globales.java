/*Herny Qiu*/
package Prop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class frecuencias_globales implements java.io.Serializable  {


	private static final long serialVersionUID = -41389441609818123L;
	private Map <String,Double> global=new HashMap<String,Double>();
	private Map <String,Map<String,Map<String,Double>>> frecdoc=new HashMap <String,Map<String,Map<String,Double>>>();
	//String1:palabra,String2:autor,String3:tiutlo,Integer:veces que aparece la palabra en el documento
	private  Map <String,Integer> numdoc= new HashMap<String,Integer>();
	
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
				palKey=palKey.toLowerCase();
				if (!palActual.esfuncional()) {
					anyadir_frecuencias(palKey,d);
				}
			}
		}
	}
	
	//actualiza la frecuencia de la palabra s en los documentos
	public void anyadir_frecuencias(String s, Documento d) throws IOException {
		if (global.containsKey(s)) {
			double pes = global.get(s);
			global.remove(s);
			pes = pes + 1;
			global.put(s, pes);
		}
		else {
			global.put(s, (double) 1);
		}
		Map <String,Double> titulofreq=new HashMap<String,Double>();
		Frase aux=d.get_titulo();
		String sauxt=aux.toString_consigno();
		titulofreq.put(sauxt, (double) 1);
		aux=d.get_autor();
		String sauxa=aux.toString_consigno();
		Map <String,Map<String,Double>> auttitfreq=new HashMap<String,Map<String,Double>>();
		auttitfreq.put(sauxa, titulofreq); //auttitfreq indica el autor, titulo a la que pertenece y que ha aparecido por primera vez la palabra
		if (frecdoc.containsKey(s)) {
			if (frecdoc.get(s).containsKey(sauxa) && frecdoc.get(s).get(sauxa).containsKey(sauxt)) { //comprueba si la palabra pertenece a un documento existente
				double pes = frecdoc.get(s).get(sauxa).get(sauxt);
				++pes;
				frecdoc.get(s).get(sauxa).remove(sauxt);
				frecdoc.get(s).get(sauxa).put(sauxt,pes);
			}
			else if (frecdoc.get(s).containsKey(sauxa)) {
				int n=numdoc.get(s);
				++n;
				numdoc.remove(s);
				numdoc.put(s,n);
				frecdoc.get(s).get(sauxa).put(sauxt,(double)1);
			}
			else {
				frecdoc.get(s).put(sauxa, titulofreq);
				//if (numdoc.containsKey(s)) {
					int n=numdoc.get(s);
					++n;
					numdoc.remove(s);
					numdoc.put(s,n);
				//}
				//else numdoc.put(s,1);
			}
		}
		else {
			frecdoc.put(s, auttitfreq);
			numdoc.put(s,1);
		}
		//System.out.println(frecdoc);
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
				if (!palActual.esfuncional()) {
					borrar_frecuencias(palKey,d);
				}
			}
		}
	}
	
	public void borrar_frecuencias(String s, Documento d) throws IOException {//resta la frecuencia de la palabra p en el documento d
		String aut=d.get_autor().toString_consigno();
		String tit=d.get_titulo().toString_consigno();
		if (global.containsKey(s)) {
			double pes=global.get(s);
			if (pes > 1) {
				pes=pes-1;
				global.put(s,pes);
				if (frecdoc.get(s).containsKey(aut) && frecdoc.get(s).get(aut).containsKey(tit)) {
					if (frecdoc.get(s).get(aut).get(tit)>1) {
						pes=frecdoc.get(s).get(aut).get(tit);
						--pes;
						frecdoc.get(s).get(aut).remove(tit);
						frecdoc.get(s).get(aut).put(tit,pes);
					}
					else {
						frecdoc.get(s).get(aut).remove(tit);
						int n=numdoc.get(s);
						--n;
						numdoc.put(s,n);
					}
					if (frecdoc.get(s).get(aut).isEmpty()) frecdoc.get(s).remove(aut);
					if (frecdoc.get(s).isEmpty()) frecdoc.remove(s);
				}
				else {
					System.out.print("error borrar la palabra no aparece enel documento");
				}
			}
			else {
				global.remove(s);
				if (frecdoc.get(s).containsKey(aut) && frecdoc.get(s).get(aut).containsKey(tit)) frecdoc.remove(s);
				else System.out.print("error borrar la palabra no aparece enel documento");
				if (numdoc.containsKey(s)) {
					int n=numdoc.get(s);
					--n;
					numdoc.put(s,n);
				}
				else numdoc.remove(s);
			}
		}
		else {
			System.out.println("la palabra no existe en el documento");
		}
	}
	
	//Consultoras
	
	public double valor_global(String p) {//devuelve la frecuencia global de la palabra p
		p=p.toLowerCase();
		if (global.containsKey(p)) return global.get(p);
		else return 0;
	}
	
	//devuelve una lista de los documentos identificados por el autor y el titulo a la que pertenece la palabra s
	public Map<String,Map<String,Double>> frecdocumentos(String s) {
		s=s.toLowerCase();
		if (frecdoc.containsKey(s)) return frecdoc.get(s);
		else {//excepcion: la palabra no existe en ningun documento
			Map<String,Map<String,Double>> vacio= new HashMap<String,Map<String,Double>>();
			return vacio;
		}
	}
	
	public double valor_documento(String p, Documento d) throws IOException {//devuelve la frecuencia de la palabra en el documento
		p=p.toLowerCase();
		String a, t;
		a=d.get_autor().toString_consigno();
		t=d.get_titulo().toString_consigno();
		if (frecdoc.containsKey(p)) {
			if (frecdoc.get(p).containsKey(a)) {
				if(frecdoc.get(p).get(a).containsKey(t)) return frecdoc.get(p).get(a).get(t); 
			}
		}
		return 0;
	}
	
	public double valor_documento(String p, String a, String t) {//devuelve la frecuencia de la palabra en el documento que tiene como autor a y titulo t
		p=p.toLowerCase();
		if (frecdoc.containsKey(p)) {
			if (frecdoc.get(p).containsKey(a)) {
				if(frecdoc.get(p).get(a).containsKey(t)) return frecdoc.get(p).get(a).get(t); 
			}
		}
		return 0;
	}
	
	public int apariencias_doc_palabra(String p) {
		p=p.toLowerCase();
		if (numdoc.containsKey(p)) return numdoc.get(p);
		return -1;
	}
	
}