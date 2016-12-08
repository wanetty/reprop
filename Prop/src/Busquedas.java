import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Busquedas implements java.io.Serializable  {

	ArrayList<Documento> por_similitud(Cjt_documentos c, String aut, String tit, int k, int metd) throws IOException {
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
					print_resultado(d, k, a,s);//se quita
				}
				//else excepcion System.out.println("No existe este método.");
			}
			//else excepcion System.out.println("Numero invalido");
		}
		//else excepcion System.out.println("Combinacion de titulo o autor inexistente");
		return a;
	}
	
	//post: devuelve los documentos que cumplen la expresion que tiene como raiz del subarbol el nodo nact
	Set<Documento> juntar_documentos(Cjt_documentos c, Nodo nact) {
		Set<Documento> sret=new HashSet<Documento>();
		if (nact != null) {
			Set<Documento> sauxiz=new HashSet<Documento>();
			Set<Documento> sauxde=new HashSet<Documento>();
			sauxiz=juntar_documentos(c, nact.getNodoIz());
			sauxde=juntar_documentos(c, nact.getNodoDer());
			String act=nact.getValor();
			if(act == "&") {
				sret.addAll(sauxiz);
				sret.addAll(sauxde);
				sret.retainAll(sauxiz);
				sret.retainAll(sauxde);
			}
			else if (act == "|") {
				sret.addAll(sauxiz);
				sret.addAll(sauxde);
			}
			else if (act == "!") {
				sret.addAll(sauxiz);
				sret.addAll(sauxde);
			}
			else if(act.charAt(0) == '"') {
				act=act.substring(1, act.length()-1);
				//comprobar en cada documento que existe act e ir anyadiendo a sret
			}
			else {
				sret=c.list_doc_palabra(nact.getValor());
			}
		}
		return sret;
	}
	
	Set<Documento> por_booleano(Cjt_documentos c, String expresion) throws IOException {
		Set<Documento> d=new HashSet<Documento>();
		Bool_expresion b=new Bool_expresion(expresion);
		ArrayList<String> p=b.PostOrden();
		Set<Documento> docs= new HashSet<Documento>(); 
		for(int i=0; i<p.size(); ++i) {
			Nodo naux=b.devuelve_expresion();
			juntar_documentos(c,naux);
			
			if(!p.get(i).equals('&') && !p.get(i).equals('|') && !p.get(i).equals('!')) {
				docs=c.list_doc_palabra(p.get(i));
			}
			d.addAll(docs);
		}
		return d;
	}
	
	Documento por_auttit(Cjt_documentos c, String aut, String tit) {
		aut=aut.toLowerCase();
		tit=tit.toLowerCase();
		Documento d=null;
		if (c.existe_combinacion(aut, tit)) {
			d=c.busqueda_por_auttit(aut, tit);	
		}
		//else excepcion System.out.println("Combinacion de titulo o autor inexistente");
		return d;
	}
	
	ArrayList<Documento> por_autor(Cjt_documentos c, String aut) {
		aut=aut.toLowerCase();
		ArrayList<Documento> d=new ArrayList<Documento>();
		if (c.existe_autor(aut)) {
			Map<String,Documento> aux=c.busqueda_por_autor(aut);
			for (String clave : aux.keySet()) d.add(aux.get(clave));
		}
		//else excepcion System.out.println("No existen documentos con el autor introducido");
		return d;
	}
	
	ArrayList<Documento> por_titulo(Cjt_documentos c, String tit) {
		tit=tit.toLowerCase();
		ArrayList<Documento> d=new ArrayList<Documento>();
		if (c.existe_titulo(tit)) {
			Map<String,Documento> aux=c.busqueda_por_titulo(tit);
			for (String clave : aux.keySet()) d.add(aux.get(clave));
		}
		//else excepcion System.out.println("No existen documentos con el titulo introducido");
		return d;
	}
	
	ArrayList<Documento> por_tema(Cjt_documentos c, String tem) {
		tem=tem.toLowerCase();
		ArrayList<Documento> d = new ArrayList<Documento>();
		if (c.existe_tema(tem)) {
			Map<String,Map<String,Documento>> aux=c.busqueda_por_tema(tem);
			for (String clave1 : aux.keySet()) {
				for (String clave2 : aux.get(clave1).keySet())
				d.add(aux.get(clave1).get(clave2));
			}
		}
		//else excepcion System.out.println("No existen documentos con el tema introducido");
		return d;
	}
	
	ArrayList<Documento> por_fecha(Cjt_documentos c, String fec) {
		ArrayList<Documento> d = new ArrayList<Documento>();
		if (c.existe_fecha(fec)) {
			Map<String,Map<String,Documento>> aux=c.busqueda_por_fecha(fec);
			for (String clave1 : aux.keySet()) {
				for (String clave2 : aux.get(clave1).keySet())
				d.add(aux.get(clave1).get(clave2));
			}
		}
		//else excepcion System.out.println("No existen documentos con la fecha introducida");
		return d;
	}
	
	Set<String> por_prefijo(Cjt_documentos c,String pref) throws IOException {
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

	public static void print_resultado(Documento T, int k, ArrayList<Documento> res, Similitud sim) throws IOException {

		if (k > 1){
			System.out.print("Los " + k +" documentos más parecidos a ");
		}
		else {
			System.out.print("El documento más parecido a ");
		}
		System.out.print("\"" + T.get_titulo().toString_consigno()+ "\"");
		System.out.print(" de ");
		System.out.print("\""+T.get_autor().toString_consigno()+"\"");
		if (k > 1) {
			System.out.println(" son:");
		}
		else System.out.println(" es:");

		for (int i = 0; i < sim.get_resultado().size(); ++i) {
			System.out.println("\"" +sim.get_resultado().get(i).get_titulo()+"\"" + " de " + "\"" +sim.get_resultado().get(i).get_autor()+"\"");
		}
	}
	
}
