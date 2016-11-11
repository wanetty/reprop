import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
public class Similitud {
		
	private ArrayList<Documento> resultado;
	
	public Similitud(){
		resultado = null;
	};
	
	public void similitud_n(Documento d, int n, Cjt_documentos cjt, int metodo) throws IOException {
		//if(!resultado.isEmpty()) for (int i = 0; i < resultado.size();++i) resultado.remove(i); //vacia el resultado
		Map<Double, ArrayList<Documento>> res = new TreeMap<Double, ArrayList<Documento>>();
		Map<Double, ArrayList<Documento>> res_ordenado = new TreeMap(Collections.reverseOrder());
		String ti = d.get_titulo().toString();
		String au = d.get_autor().toString();
		
		double simi;
		for (String clave1 : cjt.get_por_titulo().keySet()) {
			for (String clave2 : cjt.get_por_titulo().get(clave1).keySet()){
				if (!(clave1 == d.get_titulo().toString() && clave2 == d.get_autor().toString())){
					simi = calculaSimilitud(d, cjt.get_por_titulo().get(clave1).get(clave2), cjt, metodo);
					if (!res.containsKey(simi) /*&& ((!clave1.equals(ti)) && (!clave2.equals(au)))*/) {
						ArrayList<Documento> docs = new ArrayList<Documento>();
						docs.add(cjt.get_por_titulo().get(clave1).get(clave2));
						res.put(simi, docs);
					}
					else if (res.containsKey(simi) /*&& ((!clave1.equals(ti)) && (!clave2.equals(au)))*/) {
						ArrayList<Documento> docs2 = res.get(simi);
						docs2.add(cjt.get_por_titulo().get(clave1).get(clave2));
						res.put(simi, docs2);
					}
				}
			}
		}
		res_ordenado.putAll(res);
		resultado = similitud_docs(n,res_ordenado);
	}
	
	public ArrayList<Documento> similitud_docs(int n, Map<Double, ArrayList<Documento>> a) {
		ArrayList<Documento> res = new ArrayList<Documento>();
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
		return res;
	}
	
	private double calculaSimilitud(Documento a, Documento b, Cjt_documentos cjt, int metodo) throws IOException{
		Map<String, Double> mapA = new HashMap<String, Double>();
		Map<String, Double> mapB = new HashMap<String, Double>();
		llenar_map(mapA, a);
		llenar_map(mapB, b);
		double nA = a.get_total_words();
		double nB =a.get_total_words();
		tf(mapA,nA);
		tf(mapB,nB);
		
		globalizar(mapA,cjt,metodo);
		globalizar(mapB,cjt,metodo);
		
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
		int n = cjt.apariencias_cjtdoc_palabra(termino); 
		if (metodo == 1) return Math.log(1+ (cjt.get_cjt_size() / n)); 
		else return Math.log((1+ (cjt.get_cjt_size() - n) / n));
	}
	
	private void globalizar (Map<String,Double> a, Cjt_documentos cjt, int metodo) {
		for (String clave : a.keySet()){
			double frec = a.get(clave);
			
			double frec_idf = idf(clave, cjt, metodo);
			a.put(clave, frec*frec_idf);
		}
	}
	
	public ArrayList<Documento> get_resultado() {
		return resultado;
	}
}