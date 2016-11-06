import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Similitud {
		
	public double calculaSimilitud(Documento a, Documento b){
		Map<String, Double> mapA = new HashMap<String, Double>();
		Map<String, Double> mapB = new HashMap<String, Double>();
		mapA = a.get_pesos();
		mapB = b.get_pesos();
		//double lengthA = a.length();
		//double lengthB = b.length();
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
	//FUNCIONES QUE AUN NO EXISTEN DENTRO
	public double idf(Map<String, Double> global, String termino, Cjt_documentos cjt){
		double n = global.get(termino); //frecuencia global del termino
		return Math.log(cjt.get_cjt_size() / n);
	}
	
	public void globalizar (Map<String,Double> a, Cjt_documentos cjt) {
		for (String clave : a){
			double frec = a.get(clave);
			double frec_idf = 
		}
	}
	
}