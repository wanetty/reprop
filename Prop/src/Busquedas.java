import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Busquedas {

	//ArrayList<Documento> por_similitud() {}
	
	//ArrayList<Documento> por_booleano() {}
	
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
	
	
}
