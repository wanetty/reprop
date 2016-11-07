import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class Similitud {
		
	public double calculaSimilitud(Documento a, Documento b/*, Cjt_documentos cjt*/) throws IOException{
		Map<String, Double> mapA = new HashMap<String, Double>();
		Map<String, Double> mapB = new HashMap<String, Double>();
		llenar_map(mapA, a);
		
	
		
		Map<Double,ArrayList<Documento>>mapG = new HashMap<Double, ArrayList<Documento>>();
		llenar_map(mapB, b);
		//mapA = a.get_pesos();
		//mapB = b.get_pesos();
		//double lengthA = a.length();
		//double lengthB = b.length();
		double nA = a.get_total_words();
		double nB =a.get_total_words();
		tf(mapA,nA);
		tf(mapB,nB);
		//globalizar(mapA,cjt);
		//globalizar(mapB,cjt);
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
	
	public void llenar_map(Map<String,Double> mapA, Documento a){
		for(String clave : a.get_pesos().keySet()) {
			Double frec = a.get_pesos().get(clave);
			mapA.put(clave, frec);
		}
	}
	
	public void tf(Map<String,Double> a, double n) {
		for (String clave : a.keySet()){
			double frec = a.get(clave);
			a.put(clave, frec/n);
		}
	}
	
	//calcula el length de los vectores. La suma del cuadrado de los pesos.
	
	public double getLength(Map<String,Double> a){
		double res = 0.0d;
		for (Double valor : a.values()){
			res += Math.pow(valor, 2);
		}
		return res;
	}
	
	//Guarda la intersección de los dos vectores en un set
	
	public Set<String> getIntersection(Map<String,Double> a, Map<String,Double> b){
		Set<String> intersection = new HashSet<String>(a.keySet());
		intersection.retainAll(b.keySet());
		return intersection;
	}
	
	//Calcula el dot product de los dos vectores
	
	public double producto(Map<String,Double> a, Map<String,Double> b, Set<String> intersection){
		double prod = 0;
		for (String clave : intersection) {
			prod += a.get(clave)*b.get(clave);
		}
		return prod;
	}
	
	/*
	//FUNCIONES QUE AUN NO EXISTEN DENTRO
	public double idf(Map<String, Double> global, String termino, Cjt_documentos cjt){
		double n = global.get(termino); //frecuencia global del termino
		return Math.log(cjt.get_cjt_size() / 1+n); //n puede ser 0 asi que sumamos 1
	}
	
	public void globalizar (Map<String,Double> a, Cjt_documentos cjt) {
		Map<String, Double> global = new HashMap<String,Double>();
		global = cjt.get_global_map();
		for (String clave : a.keySet()){
			double frec = a.get(clave);
			double frec_idf = global.get(clave);
			a.put(clave, frec*frec_idf);
		}
	}
	*/
	
}