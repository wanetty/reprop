package Prop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Busquedas implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5148622133913761858L;

	public ArrayList<Documento> por_similitud(Cjt_documentos c, String aut, String tit, int k, int metd) throws IOException {
		aut=aut.toLowerCase();
		tit=tit.toLowerCase();
		ArrayList<Documento> a=new ArrayList<Documento>();
		Similitud s=new Similitud();
		if (c.existe_combinacion(aut, tit)) {
			Documento d=c.busqueda_por_auttit(aut,tit);
			if (k >= 1) {
				if (metd == 1 || metd == 2) {
					s.similitud_n(d,k,c,metd);
					a = s.get_resultado();
					if (k >= c.get_cjt_size()) k = c.get_cjt_size()-1;
				}
			}
		}
		return a;
	}
		 
	private Map<Documento,Set<Integer>> juntar_documentos(Cjt_documentos c, Nodo nact) {
		Map<Documento,Set<Integer>> eret=new HashMap<Documento,Set<Integer>>();
		if (nact != null) {
			Map<Documento,Set<Integer>> eiz=new HashMap<Documento,Set<Integer>>();
			Map<Documento,Set<Integer>> ede=new HashMap<Documento,Set<Integer>>();
			eiz=juntar_documentos(c, nact.getNodoIz());
			ede=juntar_documentos(c, nact.getNodoDer());
			String act=nact.getValor();
			if(act.equals("&")) {
				for(Documento diz:eiz.keySet()) {
					Set<Integer> si=new HashSet<Integer>();
					if (ede.containsKey(diz)) {
						si.addAll(eiz.get(diz));
						si.addAll(ede.get(diz));
						si.retainAll(eiz.get(diz));
						si.retainAll(ede.get(diz));
						if (!si.isEmpty()) eret.put(diz, si);
					}
				}
			}
			else if (act.equals("|")) {
				eret=eiz;
				for(Documento dde:ede.keySet()) {
					Set<Integer> si=ede.get(dde);
					if (eret.containsKey(dde)) eret.get(dde).addAll(si);
					else eret.put(dde,si);
				}
			}
			else if (act.equals("!")) {
				Map<String, Map<String,Documento>> docs=c.get_por_titulo();
				for(String clave1:docs.keySet()) {
					for(String clave2:docs.get(clave1).keySet()) {
						Set<Integer> si=new HashSet<Integer>();
						Documento d=docs.get(clave1).get(clave2);
						for(int i=0; i<d.get_num_frases(); ++i) si.add(i);
						if (eiz.containsKey(d)) si.removeAll(eiz.get(d));
						if (ede.containsKey(d)) si.removeAll(ede.get(d));
						eret.put(d, si);
					}
				}
			}
			else if (act.charAt(0) == '"') {
				act=act.substring(1, act.length()-1);
				act=act.toLowerCase();
				String delimitadores= "[( ,.;?!)\'\"\\[\\]]";
				String[] pseparadas = act.split(delimitadores);
				Set<Documento> saux=c.list_doc_palabra(pseparadas[0]);
				for(Documento doc:saux) {
					Set<Integer> apaaux=doc.apariencia_num_frase(pseparadas[0]);
					Set<Integer> apa=new HashSet<Integer>();
					for(Integer i:apaaux) {
						if (doc.get_frase(i).toString_consigno().contains(act)){
							apa.add(i);
						}
					}
					if (!apa.isEmpty()) eret.put(doc,apa);
				}
			}
			else {
				act=act.toLowerCase();
				Set<Documento> saux=c.list_doc_palabra(act);
				for(Documento doc:saux) {
					Set<Integer> apa=doc.apariencia_num_frase(act);
					eret.put(doc, apa);
				}
			}
		}
		return eret;
	}
	
	public Set<Documento> por_booleano(Cjt_documentos c, String expresion) throws Exception {
		try {
		Bool_expresion b=new Bool_expresion(expresion);
		Map<Documento,Set<Integer>> res=juntar_documentos(c,b.getraiz());
		return res.keySet();
		}catch(Exception e) {
			throw e;
		}
	}
	
	public Documento por_auttit(Cjt_documentos c, String aut, String tit) {
		aut=aut.toLowerCase();
		tit=tit.toLowerCase();
		Documento d=null;
		if (c.existe_combinacion(aut, tit)) {
			d=c.busqueda_por_auttit(aut, tit);	
		}
		return d;
	}
	
	public ArrayList<Documento> por_autor(Cjt_documentos c, String aut) {
		aut=aut.toLowerCase();
		ArrayList<Documento> d=new ArrayList<Documento>();
		if (c.existe_autor(aut)) {
			Map<String,Documento> aux=c.busqueda_por_autor(aut);
			for (String clave : aux.keySet()) d.add(aux.get(clave));
		}
		return d;
	}
	
	public ArrayList<Documento> por_titulo(Cjt_documentos c, String tit) {
		tit=tit.toLowerCase();
		ArrayList<Documento> d=new ArrayList<Documento>();
		if (c.existe_titulo(tit)) {
			Map<String,Documento> aux=c.busqueda_por_titulo(tit);
			for (String clave : aux.keySet()) d.add(aux.get(clave));
		}
		return d;
	}
	
	public ArrayList<Documento> por_tema(Cjt_documentos c, String tem) {
		tem=tem.toLowerCase();
		ArrayList<Documento> d = new ArrayList<Documento>();
		if (c.existe_tema(tem)) {
			Map<String,Map<String,Documento>> aux=c.busqueda_por_tema(tem);
			for (String clave1 : aux.keySet()) {
				for (String clave2 : aux.get(clave1).keySet())
				d.add(aux.get(clave1).get(clave2));
			}
		}
		return d;
	}
	
	public ArrayList<Documento> por_fecha(Cjt_documentos c, String fec) {
		ArrayList<Documento> d = new ArrayList<Documento>();
		if (c.existe_fecha(fec)) {
			Map<String,Map<String,Documento>> aux=c.busqueda_por_fecha(fec);
			for (String clave1 : aux.keySet()) {
				for (String clave2 : aux.get(clave1).keySet())
				d.add(aux.get(clave1).get(clave2));
			}
		}
		return d;
	}
	
	public Set<String> por_prefijo(Cjt_documentos c,String pref) throws IOException {
		TreeMap<String,Map<String,Documento>> t=c.autores_ordenados();
		if (!pref.isEmpty()) {
			char l=pref.charAt(pref.length()-1);
			l=(char) (l+1);
			String end=pref.substring(0, pref.length()-1)+l;
			Map<String, Map<String,Documento>> k=t.subMap(pref, end);
			return k.keySet();
		}
		return t.keySet();
	}

	
}