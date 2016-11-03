import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class Documento {

	private Frase titulo = new Frase();
	private Frase autor = new Frase();
	private Frase tema = new Frase();
	private Date fecha = new Date();
	private ArrayList<Frase> contenido = new ArrayList<Frase>();
	private Map<String, Double> pesos = new HashMap<String, Double>();//constructoras
	public Documento(){}
	
	public Documento(Frase ti, Frase a, Frase te, ArrayList<Frase> c, Date f) throws IOException{
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
		fecha=f;
		construirPesos();
	}
	//Construye un mapa <string, double> que contiene palabras con su peso
	public void construirPesos() throws IOException{
		int sizeDoc = contenido.size();
		Frase fraseActual = new Frase();
		
		for (int i = 0; i < sizeDoc; ++i) {
			fraseActual = contenido.get(i);
			int sizeFrase = fraseActual.midafrase();
			for (int j = 0; j < sizeFrase; ++j){
				Palabra palActual = fraseActual.posfrase(j);
				String palKey = palActual.palabra();
				palKey = palKey.toLowerCase();
				//palabra repetida
				if (!palActual.esfuncional()){
					if (pesos.containsKey(palKey)) {
						double pes = pesos.get(palKey);
						pesos.remove(palKey);
						pes = pes + 1;
						pesos.put(palKey, pes);
					}
					else {
						pesos.put(palKey, (double) 1);
					}
				}
			}
		}
	}
	
	//modificadora
	
	public void set_titulo(Frase t){
		titulo = t;
	}
	
	public void set_autor(Frase a){
		autor = a;
	}
	
	public void set_tema(Frase t){
		tema = t;
	}
	
	//consultoras
	
	public Frase get_titulo() {
		return titulo;
	}
	
	public Frase get_autor() {
		return autor;
	}
	
	public Frase get_tema() {
		return tema;
	}
	public Date get_fecha(){
		return fecha;
	}
	public int get_num_frases(){
		return contenido.size();
	}
	
	public Frase get_frase(int i){
		return contenido.get(i);
	}
	
	public Map<String, Double> get_pesos(){
		return pesos;
	}
	
	//modificadoras
	
	public void borrar_frase(int i){
		if (i < contenido.size()) contenido.remove(i);
	}
	
	public void set_frase(int i, Frase f){
		if (i < contenido.size()) contenido.add(i, f);
		else if (i == contenido.size()) contenido.add(f);
	}
}
joder pero que conyo quieres tio
