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
	
	public Documento(Frase ti, Frase a, Frase te, ArrayList<Frase> c) throws IOException{
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
		construirPesos();
	}
	//Construye un mapa <string, double> que contiene palabras con su peso
	private void construirPesos() throws IOException{
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
	
	//Modificaciones
	//de momento suponemos que desde el progama principal le pasara la posicion de la palabra en la frase
	//nota: hay que anyadir en el caso de usos que el usuario tendria que indicar el numero de la frase a la que pertenece la palabra que se quiere borrar y la palabra borrada.
	//0<=numfras<=numero de frases en total del documento
	public void borrar_palabra(int numfras, Palabra pborr) throws IOException{
		contenido.get(numfras).borrarpalabra(pborr);
		if (!pborr.esfuncional()) {
			String termino = pborr.palabra().toLowerCase();
			Double frec = pesos.get(termino);
			--frec;
			if (frec == 0) pesos.remove(termino);
			else {
				pesos.put(termino, frec);
			}
		}
		
		
	}
	public void anyadir_palabra(int numfras, Palabra panyad) throws IOException{
		contenido.get(numfras).anyadirpalabra(panyad, contenido.get(numfras).midafrase());
		if (!panyad.esfuncional()){
			String termino = panyad.palabra().toLowerCase();
			Double frec;
			if (pesos.containsKey(termino)) {
				frec = pesos.get(termino);
				++frec;
				pesos.put(termino, frec);
			}
			else pesos.put(termino, (double) 1);
		}
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
	public ArrayList<Frase> get_contenido(){
		return contenido;
	}
	
	public Frase get_frase(int i){
		return contenido.get(i);
	}
	
	public Map<String, Double> get_pesos(){
		return pesos;
	}
	
	public double get_total_words() throws IOException{
		double res = 0;
		Frase aux = new Frase();
		for (int i = 0; i < contenido.size(); ++i){
			aux = contenido.get(i);
			res = res + aux.midafrase_significativa();
		}
		return res;
	}
	
	
	//modificadoras
	
	public void borrar_frase(int i) throws IOException{
		
		if (i < contenido.size()) {/*contenido.remove(i);*/
			Frase f = new Frase();
			int n = f.midafrase();
			for (int j = 0; j < n; ++j) {
				Palabra actual = f.posfrase(j);
				borrar_palabra(i, actual);	
			}
		}
	}
	
	public void set_frase(int i, Frase f) throws IOException{
		contenido.add(i, f);
		Palabra actual = new Palabra();
		for (int j = 0; j < f.midafrase(); ++j){
			actual = f.posfrase(j);
			String palKey = actual.palabra().toLowerCase();
			if (pesos.containsKey(palKey)) {
				Double frec = pesos.get(palKey);
				++frec;
				pesos.put(palKey, frec);
			}
			else pesos.put(palKey, (double) 1);	
		}
	}
	
	public void pintar_documento() {
		System.out.println(titulo.toString());
		System.out.println(autor.toString());
		System.out.println(tema.toString());
		for (int i=0; i<contenido.size(); ++i) {
			System.out.print(contenido.get(i).toString());
			System.out.println(".");
		}
	}
}