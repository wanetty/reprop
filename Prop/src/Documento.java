package Prop;
//Creador: Gerard Heredia
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Documento implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8239632605956944790L;
	private Frase titulo = new Frase(); 
	
	private Frase autor = new Frase();
	private Frase tema = new Frase();
	private Date fecha = new Date();
	private ArrayList<Frase> contenido = new ArrayList<Frase>();
	private Map<String, Double> pesos = new HashMap<String, Double>();//constructoras
	private String contoriginal;

	public Documento(){
		titulo=null;
		autor=null;
		tema=null;
		contoriginal="";
	}
	
	public Frase getTitulo() {
		return titulo;
	}

	public void setTitulo(Frase titulo) {
		this.titulo = titulo;
	}

	public Frase getAutor() {
		return autor;
	}

	public void setAutor(Frase autor) {
		this.autor = autor;
	}

	public Frase getTema() {
		return tema;
	}

	public void setTema(Frase tema) {
		this.tema = tema;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Frase> getContenido() {
		return contenido;
	}

	public void setContenido(ArrayList<Frase> contenido) {
		this.contenido = contenido;
	}

	public Map<String, Double> getPesos() {
		return pesos;
	}

	public void setPesos(Map<String, Double> pesos) {
		this.pesos = pesos;
	}
	
	public void setContorg(String contoriginal) {
		this.contoriginal=contoriginal;
	}
	
	public String getContorg() {
		return contoriginal;
	}


	private void string_to_arraylist(ArrayList<Frase> c, String s) throws IOException {
		String delimitadores= "[.;?!]";
		String[] frasesseparadas = s.split(delimitadores);
		for (int i=0; i<frasesseparadas.length; ++i) {
			Frase aux=new Frase(frasesseparadas[i]);
			c.add(aux);
		}
	}

	public void guardar_documento(String text) throws IOException {
		String del="\\n";
		String[] aux = text.split(del);//aux contiene parrafos
		Frase ti = new Frase(aux[0]);
		Frase a = new Frase(aux[1]);
		Frase te= new Frase(aux[2]);
		ArrayList<Frase> c= new ArrayList<Frase>();
		string_to_arraylist(c,aux[3]);
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
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
	public String get_fecha(){
		int anyoaux=fecha.getYear();
		int mesaux=fecha.getMonth();
		int diaaux=fecha.getDate();
		String anyo = null, mes=null, dia=null;
		anyo=String.valueOf(anyoaux);
		mes=String.valueOf(mesaux);
		dia=String.valueOf(diaaux);
		String nuevo;
		if (mes.equals("12")) nuevo=dia+"/01/"+anyo.substring(1, 3);
		else nuevo=dia+'/'+mes.charAt(0)+(char)(mes.charAt(1)+1)+'/'+anyo.substring(1, 3);
		return nuevo;
	}

	public int get_num_frases(){
		return contenido.size();
	}
	public ArrayList<Frase> get_contenido(){
		return contenido;
	}

	public Frase get_frase(int i){
		if(i > contenido.size() || i < 0) return null; 
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

	public Set<Integer> apariencia_num_frase(String p) {
		Set<Integer> res=new HashSet<Integer>();
		String fraseaux;
		for(int i=0; i<contenido.size(); ++i) {
			fraseaux=contenido.get(i).toString();
			if (fraseaux.contains(p)) res.add(i);
		}
		return res;
	}
	

	public String contenido_toString() {
		String ret="";
		String aux;
		char fl;
		for(int i=0; i<contenido.size(); ++i) {
			aux=contenido.get(i).toString_consigno();
			if (!aux.isEmpty()){
			fl=(char) ('A' + (aux.charAt(0) - 'a'));
			ret+=fl+aux.substring(1, aux.length());
			ret += ".";
			}
			else ret += ".\n";
		}
		return ret;
	}
	
	public ArrayList<String> Doc_to_string() throws IOException {
		ArrayList<String> res = new ArrayList<String>();
		res.add(0, autor.toString_consigno());
		res.add(1, titulo.toString_consigno());
		if(!(tema.midafrase()== 0)) {
			res.add(2, tema.toString_consigno());
		}
		if (contenido.isEmpty()) res.add(3, null);
		else {
			//String aux = contenido_toString();
			res.add(3, contoriginal);
		}
		return res;
		
	}
	
}