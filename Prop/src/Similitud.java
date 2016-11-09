import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
public class Similitud {
		
	
	public ArrayList<Documento> similitud_n(Documento d, int n, Cjt_documentos cjt, int metodo) throws IOException {
		Map<Double, ArrayList<Documento>> res = new TreeMap<Double, ArrayList<Documento>>();
		Map<Double, ArrayList<Documento>> res_ordenado = new TreeMap(Collections.reverseOrder());
		double simi;
		for (String clave1 : cjt.get_por_titulo().keySet()) {
			for (String clave2 : cjt.get_por_titulo().get(clave1).keySet()){
				simi = calculaSimilitud(d, cjt.get_por_titulo().get(clave1).get(clave2), cjt, metodo);
				if (!res.containsKey(simi)) {
					ArrayList<Documento> docs = new ArrayList<Documento>();
					docs.add(cjt.get_por_titulo().get(clave1).get(clave2));
					res.put(simi, docs);
				}
				else {
					ArrayList<Documento> docs2 = res.get(simi);
					docs2.add(cjt.get_por_titulo().get(clave1).get(clave2));
					res.put(simi, docs2);
				}
			}
		}
		res_ordenado.putAll(res);
		ArrayList<Documento> docs = new ArrayList<Documento>();
		similitud_docs(n,res_ordenado,docs);
		return docs;
	}
	
	private void similitud_docs(int n, Map<Double, ArrayList<Documento>> a, ArrayList<Documento> res ) {
		int fin = 0;
		if (n > 0) {
			for (Double sim : a.keySet()){
				for (int i = 0; i < a.get(sim).size(); ++i) {
					res.add(a.get(sim).get(i));
					++fin;
					if (fin == n) break;
				}
				if (fin == n) break;
			}
		}
	}
	
	private double calculaSimilitud(Documento a, Documento b, Cjt_documentos cjt, int metodo) throws IOException{
		Map<String, Double> mapA = new HashMap<String, Double>();
		Map<String, Double> mapB = new HashMap<String, Double>();
		llenar_map(mapA, a);
		llenar_map(mapB, b);
		//mapA = a.get_pesos();
		//mapB = b.get_pesos();
		//double lengthA = a.length();
		//double lengthB = b.length();
		double nA = a.get_total_words();
		double nB =a.get_total_words();
		tf(mapA,nA);
		tf(mapB,nB);
		globalizar(mapA,cjt,metodo);
		globalizar(mapB,cjt,metodo);
		System.out.println(mapA);
		System.out. println(mapB);
		Set<String> inter = new HashSet<String>();
		inter = getIntersection(mapA,mapB);
		double producto = producto(mapA,mapB,inter);
		double lengthA = getLength(mapA);
		double lengthB = getLength(mapB);
		double similitudCos;
		if (lengthA <= 0.0 || lengthB <= 0.0){
			similitudCos = 0.0;
		}
		//La fórmula del coseno
		else {
			similitudCos = (producto / (Math.sqrt(lengthA)*Math.sqrt(lengthB)));
		}
		return similitudCos;
	}
	
	private void llenar_map(Map<String,Double> mapA, Documento a){
		for(String clave : a.get_pesos().keySet()) {
			Double frec = a.get_pesos().get(clave);
			mapA.put(clave, frec);
		}
	}
	
	private void tf(Map<String,Double> a, double n) {
		for (String clave : a.keySet()){
			double frec = a.get(clave);
			a.put(clave, frec/n);
		}
	}
	
	//calcula el length de los vectores. La suma del cuadrado de los pesos.
	
	private double getLength(Map<String,Double> a){
		double res = 0.0d;
		for (Double valor : a.values()){
			res += Math.pow(valor, 2);
		}
		return res;
	}
	
	//Guarda la intersección de los dos vectores en un set
	
	private Set<String> getIntersection(Map<String,Double> a, Map<String,Double> b){
		Set<String> intersection = new HashSet<String>(a.keySet());
		intersection.retainAll(b.keySet());
		return intersection;
	}
	
	//Calcula el dot product de los dos vectores
	
	private double producto(Map<String,Double> a, Map<String,Double> b, Set<String> intersection){
		double prod = 0;
		for (String clave : intersection) {
			prod += a.get(clave)*b.get(clave);
		}
		return prod;
	}
	
	private double idf(String termino, Cjt_documentos cjt, int metodo){
		int n = cjt.apariencias_cjtdoc_palabra(termino); //frecuencia global del termino
		if (metodo == 1) return Math.log(cjt.get_cjt_size() / (1+n)); //n puede ser 0 asi que sumamos 1
		else return Math.log((cjt.get_cjt_size() - n) / n);
	}
	
	private void globalizar (Map<String,Double> a, Cjt_documentos cjt, int metodo) {
		for (String clave : a.keySet()){
			double frec = a.get(clave);
			double frec_idf = idf(clave, cjt, metodo);
			a.put(clave, frec*frec_idf);
		}
	}
}