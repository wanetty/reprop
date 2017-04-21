package Prop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class frecuencias_globales implements java.io.Serializable  {


	private static final long serialVersionUID = -41389441609818123L;
	private Map <String,Double> global=new HashMap<String,Double>();
	private Map <String,Map<String,Map<String,Double>>> frecdoc=new HashMap <String,Map<String,Map<String,Double>>>();
	private  Map <String,Integer> numdoc= new HashMap<String,Integer>();
	
	public frecuencias_globales() {}
	
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
		auttitfreq.put(sauxa, titulofreq); 
		if (frecdoc.containsKey(s)) {
			if (frecdoc.get(s).containsKey(sauxa) && frecdoc.get(s).get(sauxa).containsKey(sauxt)) { 
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
					int n=numdoc.get(s);
					++n;
					numdoc.remove(s);
					numdoc.put(s,n);
			}
		}
		else {
			frecdoc.put(s, auttitfreq);
			numdoc.put(s,1);
		}
	}
	
	public void borrar_frecuencias(Documento d) throws IOException {
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
	
	public void borrar_frecuencias(String s, Documento d) throws IOException {
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
			}
			else {
				global.remove(s);
				if (frecdoc.get(s).containsKey(aut) && frecdoc.get(s).get(aut).containsKey(tit)) frecdoc.remove(s);
				if (numdoc.containsKey(s)) {
					int n=numdoc.get(s);
					--n;
					numdoc.put(s,n);
				}
				else numdoc.remove(s);
			}
		}
	}
	
	//Consultoras
	
	public double valor_global(String p) {
		p=p.toLowerCase();
		if (global.containsKey(p)) return global.get(p);
		else return 0;
	}
	
	public Map<String,Map<String,Double>> frecdocumentos(String s) {
		s=s.toLowerCase();
		if (frecdoc.containsKey(s)) return frecdoc.get(s);
		else {
			Map<String,Map<String,Double>> vacio= new HashMap<String,Map<String,Double>>();
			return vacio;
		}
	}
	
	public double valor_documento(String p, Documento d) throws IOException {
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
	
	public double valor_documento(String p, String a, String t) {
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