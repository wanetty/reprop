//Creador: Gerard Heredia

import java.io.BufferedReader;
import java.io.FileReader;
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

	public void borrar_palabra(int numfras, Palabra pborr) throws IOException{
		contenido.get(numfras).borrarpalabra(contenido.get(numfras).posfrase(pborr));
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

	public Frase get_titulo() {
		return titulo;
	}

	public Frase get_autor() {
		return autor;
	}

	public Frase get_tema() {
		return tema;
	}
	public String get_fecha(){
		int anyoaux=fecha.getYear();
		int mesaux=fecha.getMonth();
		int diaaux=fecha.getDate();
		String anyo = null, mes=null, dia=null;
		anyo=anyo.valueOf(anyoaux);
		mes=mes.valueOf(mesaux);
		dia=dia.valueOf(diaaux);
		String nuevo=anyo+mes+dia;
		return nuevo;
	}

	public Date get_fecha_date() {
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

	public void borrar_frase(int i) throws IOException{

		if (i < contenido.size()) {
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

	public Documento(String raiz) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(raiz));
		String funcional = in.readLine();
		if (!funcional.isEmpty()) {
			autor=new Frase(funcional);
			funcional=in.readLine();
			if (!funcional.isEmpty()) {
				titulo=new Frase(funcional);
				funcional=in.readLine();
				tema=new Frase(funcional);
				funcional=in.readLine();
				String delimitadores= "[.;?!:]";
				while (funcional != null){
					String[] frasesseparadas = funcional.split(delimitadores);
					for (int i=0; i<frasesseparadas.length; ++i) {
						funcional=frasesseparadas[i];
						contenido.add(new Frase(funcional));
					}
					funcional = in.readLine();
				}
				construirPesos();
				System.out.println("Documento leido correctamente");
			}
			else System.out.println("Formato de documento incorrecto: autor no puede ser vacio");
		}
		else System.out.println("Formato de documento incorrecto: titulo no puede ser vacio");
		in.close();
	}

	private void string_to_arraylist(ArrayList<Frase> c, String s) throws IOException {
		String delimitadores= "[.;?!]";//faltan los puntos suspensivos
		String[] frasesseparadas = s.split(delimitadores);
		for (int i=0; i<frasesseparadas.length; ++i) {
			Frase aux=new Frase(frasesseparadas[i]);
			c.add(aux);
		}
	}

	public void guardar_documento(String text) throws IOException {
		String del="\\n";
		String[] aux = text.split(del);
		Frase ti = new Frase(aux[1]);
		Frase a = new Frase(aux[0]);
		Frase te= new Frase(aux[2]);
		ArrayList<Frase> c= new ArrayList<Frase>();
		for (int i=3; i<aux.length; ++i) {
			string_to_arraylist(c,aux[i]);
		}
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
		construirPesos();
	}
}