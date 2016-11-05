import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class frecuencias_globales {

	private Map <String,Double> global=new HashMap<String,Double>();
	private Map <String,Map<String,Map<String,Double>>> frecdoc=new HashMap <String,Map<String,Map<String,Double>>>();
	//String1:palabra,String2:autor,String3:tiutlo,Integer:veces que aparece la palabra en el documento
	
	public frecuencias_globales() {}
	
	private String frase_to_string(Frase f) throws IOException {///transforma una frase en string
		String res = null;
		for(int i=0; i<f.midafrase(); ++i) {
			Palabra p=f.posfrase(i);
			res.concat(p.palabra());
			if (i != f.midafrase()-1) res.concat(" ");
		}
		return res;
	}
	
	public void actualizar_frecuencias(Documento d) throws IOException {
		int sizeDoc = d.get_contenido().size();
		Frase fraseActual = new Frase();
		
		for (int i = 0; i < sizeDoc; ++i) {
			fraseActual = d.get_contenido().get(i);
			int sizeFrase = fraseActual.midafrase();
			for (int j = 0; j < sizeFrase; ++j){
				Palabra palActual = fraseActual.posfrase(j);
				String palKey = palActual.palabra();
				palKey = palKey.toLowerCase();
				//palabra repetida
				if (!palActual.esfuncional()){
					if (global.containsKey(palKey)) {
						double pes = global.get(palKey);
						//global.remove(palKey);
						pes = pes + 1;
						global.put(palKey, pes);
					}
					else {
						global.put(palKey, (double) 1);
					}
					Map <String,Double> titulofreq=new HashMap<String,Double>();
					Frase aux=d.get_titulo();
					String sauxt=frase_to_string(aux);
					titulofreq.put(sauxt, (double) 1);
					aux=d.get_autor();
					String sauxa=frase_to_string(aux);
					Map <String,Map<String,Double>> auttitfreq=new HashMap<String,Map<String,Double>>();
					auttitfreq.put(sauxa, titulofreq); //auttitfreq indica el autro, titulo a la que pertenece y que ha aparecido por primera vez la palabra
					if (frecdoc.containsKey(palKey)) {
						if (frecdoc.get(palKey).containsKey(sauxa) && frecdoc.get(palKey).get(sauxa).containsKey(sauxt)) { //comprueba si la palabra pertenece a un documento existente
							double pes = frecdoc.get(palKey).get(sauxa).get(sauxt);
							pes = pes + 1;
							frecdoc.get(palKey).get(sauxa).put(palKey,pes);
						}
						else frecdoc.put(palKey, auttitfreq);
					}
					else frecdoc.put(palKey, auttitfreq);
				}
			}
		}
		
	}
	
	public double valor_global(String p) {//devuelve la frecuencia global de la palabra p
		return global.get(p);
	}
	
	public double valor_documento(String p, Documento d) throws IOException {//devuelve la frecuencia de la palabra en el documento
		String a, t;
		a=frase_to_string(d.get_autor());
		t=frase_to_string(d.get_titulo());
		return frecdoc.get(p).get(a).get(t);
	}
	
	public double valor_documento(String p, String a, String t) {//devuelve la frecuencia de la palabra en el documento que tiene como autor a y titulo t
		return frecdoc.get(p).get(a).get(t);
	}
	
	public void borrar_frecuencia(String p) {//resta la frecuencai de la palabra p
		double pes=global.get(p);
		if (pes > 1) {
			pes=pes-1;
			global.put(p,pes);
			
			Iterator it=frecdoc.get(p).entrySet().iterator();
			while (it.hasNext()) {
				String k=(String) it.next();
				Iterator it2=frecdoc.get(p).get(k).entrySet().iterator();
				while (it2.hasNext()) {
					String q=(String) it2.next();
					double pesdoc=frecdoc.get(p).get(k).get(q);
					if (pesdoc > 1) {
						pesdoc=pesdoc-1;
						frecdoc.get(p).get(k).put(q, pesdoc);
					}
					else frecdoc.remove(p);
				}
			}
		}
		else global.remove(p);
	}
	
}
